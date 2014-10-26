(ns pretelan.site.problem
  (:require [pretelan.dbase :refer [make-couch get-zenid create-type]]
            [com.ashafa.clutch :as cl]
            [pretelan.util :refer [now]]
            [pretelan.site.user :as user :refer [total-users]]))

(def cdb (make-couch :cloudant-production))

(def problem-dir "resources/problem/")

(defn elem
  [n ls]
  (if (some #(= % n) ls)
    true
    false))

(defn user-problems
  [user-email]
  (let [user-probs (->> {:key user-email}
                        (cl/get-view cdb "user" "byEmail")
                        (first)
                        (:value)
                        (:problems))]
    (map :no user-probs)))

(defn problems
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
  [problem-map]
  (let [zenid (get-zenid cdb "problem")]
    (->> {:no zenid
          :solvers 0
          :type "problem"
          :date (now)
          :link (str "problems/problem/" zenid)
          :content (slurp (str problem-dir (:file problem-map) ".html"))}
         (merge problem-map)
         (cl/put-document cdb))))

(defn problem
  [no]
  (->> (cl/get-view cdb "problem" "byNo" {:key no})
       (first)
       (:value)))

(defn problem-answered
  [no]
  (let [old-data (problem no)
        solvers (inc (:solvers old-data))
        total-usrs (total-users)]
    (cl/put-document cdb
                     (assoc old-data
                       :solvers solvers))))

(defn answered-before?
  [email no]
  (elem no (user-problems email)))

(defn user-answered
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






