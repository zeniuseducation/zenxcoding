(ns pretelan.site.problem
  (:require [pretelan.dbase :refer [make-couch get-zenid create-type]]
            [com.ashafa.clutch :as cl]
            [pretelan.util :refer [now]]))

(def cdb (make-couch :local-couch))

(def problem-dir "resources/problem/")

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
            (if (some (:no %) user-probs)
              "Solved!"
              ""))
         problems)))

(defn add-problem
  [problem-map]
  (let [zenid (get-zenid cdb "problem")]
    (->> {:no zenid
          :solvers 0
          :type "problem"
          :maxpoints 100
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

(defn check-answer?
  [no answer user-email]
  (let [db-answer (->> {:key no}
                       (cl/get-view cdb "problem" "byNo")
                       (first)
                       :value
                       :answer)
        correct? (= answer (read-string db-answer))]
    (if correct?
      (do ())
      correct?)))






