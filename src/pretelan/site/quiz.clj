(ns pretelan.site.quiz
  (:require [pretelan.dbase :refer [make-couch get-zenid]]
            [com.ashafa.clutch :refer [put-document get-view bulk-update]]
            [pretelan.util :refer [now cslurp]]))

(def ^:private cdb (make-couch :cloudant-production))

(def ^:private dir "resources/quiz/")

(defn view [] (get-view cdb "quiz" "byZenid"))

(defn res [no] (str dir no ".edn"))

(defn add-quiz
  [no]
  (let [zenid (view)]
    (->> {:type "quiz"
          :zenid zenid
          :date (now)}
         (merge (cslurp (res no)))
         (put-document cdb))))

(defn get-quiz
  [zenid]
  (->> (view) first :value))

(defn quizzes
  []
  (->> (view) (map :value)))

(defn update-quiz
  [zenid no]
  (let [old-data (->> (view) first :value)]
    (put-document cdb (merge old-data (cslurp (res no))))))

(defn check-answer
  [zenid answer]
  (let [old-data (get-quiz zenid)]
    (if (= answer (:answer old-data))
      (do (->> (inc (:solvers old-data))
               (assoc old-data :solvers)
               (put-document cdb))
          true)
      false)))


