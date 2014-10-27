(ns pretelan.site.tutorial
  (:require [pretelan.dbase :refer [make-couch get-zenid]]
            [pretelan.util :refer [now cslurp]]
            [com.ashafa.clutch :as cl]))

(def cdb (make-couch :cloudant-production))

(defn course-meta
  [course-no]
  (cslurp (str "resources/tutorials/course/meta" course-no ".edn")))

;; in the course meta you should put at least :title & :description

(defn tutorial-meta
  [tutorial-no]
  (cslurp (str "resources/tutorials/tutorial/meta" tutorial-no ".edn")))

;; in the tutorial you should put at least :title :course 

(defn tutorial-content
  [tutorial-no]
  (slurp (str "resources/tutorials/tutorial/" tutorial-no ".html")))

;; When adding a course, use course meta to make things easier
(defn add-course
  "Add a new course"
  [course-no]
  (let [course-map (course-meta course-no)
        zenid (get-zenid cdb "course")]
    (->> {:zenid zenid
          :type "course"
          :date (now)
          :order (* 10 zenid)}
         (merge course-map)
         (cl/put-document cdb))))

(defn update-course
  [zenid course-no]
  (let [old-data (->> {:key zenid}
                      (cl/get-view cdb "course" "byZenid")
                      first :value)]
    (->> (course-meta course-no)
         (merge old-data)
         (cl/put-document cdb))))

(defn add-tutorial
  "Add a new course tutorial"
  [tutorial-no]
  (let [tutorial-map (tutorial-meta tutorial-no)
        tutorial-content (tutorial-content tutorial-no)
        zenid (get-zenid cdb "tutorial")]
    (->> {:zenid zenid
          :type "tutorial"
          :date (now)
          :content tutorial-content}
         (merge tutorial-map)
         (cl/put-document cdb))))

(defn update-tutorial
  [zenid tutorial-no]
  (let [old-data (->> {:key zenid}
                      (cl/get-view cdb "tutorial" "byZenid")
                      first :value)]
    (->> (tutorial-content tutorial-no)
         (assoc (tutorial-meta tutorial-no) :content)
         (merge old-data)
         (cl/put-document cdb))))

(defn all-courses
  "Raw listing of courses"
  []
  (map :value (cl/get-view cdb "course" "byZenid")))

(defn courses
  "List of courses to be used for listing in a page"
  []
  (map :value (cl/get-view cdb "course" "byZenid")))

(defn course
  "Returns the course info and the list of tutorials in this course"
  [zenid]
  (let [course-info (->> {:key zenid}
                         (cl/get-view cdb "course" "byZenid")
                         first :value)
        dtutorials (->> {:key zenid}
                        (cl/get-view cdb "tutorial" "byCourse")
                        (map :value)
                        (sort-by :order))]
    (merge course-info
           {:tutorials dtutorials})))

(defn first-tutorial-id
  [zenid]
  (->> {:key zenid :limit 1}
       (cl/get-view cdb "tutorial" "byCourse")
       first :value :zenid))

(defn all-tutorials
  "Returns all available tutorials"
  []
  (map :value (cl/get-view cdb "tutorial" "byZenid")))

(defn tutorial
  "Returns the complete detail of tutorials"
  [zenid]
  (->> {:key zenid}
       (cl/get-view cdb "tutorial" "byZenid")
       first :value))





