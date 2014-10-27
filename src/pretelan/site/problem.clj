(ns pretelan.site.problem
  (:require [pretelan.dbase :refer [make-couch get-zenid create-type]]
            [com.ashafa.clutch :as cl]
            [pretelan.util :refer [now cslurp]]
            [pretelan.site.user :as user :refer [total-users]]))

(def cdb (make-couch :cloudant-production))

;; When adding a problem you need to put :title :file and :answer
;; (string). The files for problems should use numbers that indicates
;; its # and input without the html extension, and put the file in
;; problem-dir 

;; The directory in which the problems files are resided
(def problem-dir "resources/problem/")

(defn problem-content
  [fname]
  (slurp (str problem-dir fname ".html")))

(defn elem
  [n ls]
  (if (some #(= % n) ls)
    true
    false))

(defn user-problems
  "Returns the list of problems solved by a user"
  [user-email]
  (let [user-probs (->> {:key user-email}
                        (cl/get-view cdb "user" "byEmail")
                        (first)
                        (:value)
                        (:problems))]
    (map :no user-probs)))

(defn all-problems
  []
  (map :value (cl/get-view cdb "problem" "byNo")))

(defn problems
  "Returns a list of problems for a particular use, with the status
  for that user"
  [user-email]
  (let [problems (->> (cl/get-view cdb "problem" "byNo")
                      (map :value))
        user-probs (user-problems user-email)]
    (map #(assoc %
            :status
            (if (elem (:no %) user-probs)
              "Solved!"
              ""))
         problems)))

(defn add-problem
  "Add a problem to the database"
  [problem-no]
  (let [zenid (get-zenid cdb "problem")
        problem-map (cslurp (str problem-dir "meta" problem-no ".edn"))]
    (->> {:no zenid
          :solvers 0
          :type "problem"
          :date (now)
          :link (str "problems/problem/" zenid)
          :content (slurp (str problem-dir problem-no ".html"))}
         (merge problem-map)
         (cl/put-document cdb))))

(defn update-problem
  [zenid problem-no]
  (let [old-data (->> {:key zenid}
                      (cl/get-view cdb "problem" "byNo")
                      first :value)
        problem-map (cslurp (str problem-dir "meta" problem-no ".edn"))]
    (->> (slurp (str problem-dir problem-no ".html"))
         (assoc problem-map :content)
         (merge old-data)
         (cl/put-document cdb))))

(defn problem
  "Returns a problem with id no"
  [no]
  (->> (cl/get-view cdb "problem" "byNo" {:key no})
       (first)
       (:value)))

(defn problem-answered
  "When problem is answered, there are things needed to updated, this
  is the function to do so"
  [no]
  (let [old-data (problem no)
        solvers (inc (:solvers old-data))
        total-usrs (total-users)]
    (cl/put-document cdb
                     (assoc old-data
                       :solvers solvers))))

(defn answered-before?
  "Check whether a user has answered a particular problem before"
  [email no]
  (elem no (user-problems email)))

(defn user-answered
  "When use answers a problem, there are few updated need to be made
  to his/her account, this is the function to do so"
  [email no maxpoint]
  (let [old-data (user/get-user email)
        problems (conj (:problems old-data)
                       {:no no :date (now)})
        score (+ maxpoint (:score old-data))
        solved (inc (:solved old-data))]
    (cl/put-document cdb
                     (merge old-data
                            {:problems problems
                             :score score
                             :solved solved}))))

(defn check-answer?
  "Returns true if user answer correctly and false otherwise,
  becareful, this function has a lot of side effects which is updating
  the data of a problem and user's own information in database"
  [no answer user-email]
  (let [problem-data (->> {:key no}
                          (cl/get-view cdb "problem" "byNo")
                          (first)
                          :value)
        correct? (= answer
                    (read-string (:answer problem-data)))
        total-usrs (total-users)]
    (if correct?
      (if (answered-before? user-email no)
        correct?
        (do (problem-answered no)
            (user-answered user-email
                           no
                           (int (* 100
                                   (/ (- total-usrs
                                         (:solvers problem-data))
                                      total-usrs))))
            correct?))
      correct?)))






