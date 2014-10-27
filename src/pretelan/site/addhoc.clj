(ns pretelan.site.addhoc
  (:require [pretelan.dbase :refer [make-couch all-zenids]]
            [pretelan.site.problem :as problem]
            [pretelan.site.tutorial :as tutorial]
            [pretelan.site.user :as user]
            [com.ashafa.clutch :as cl]
            [pretelan.util :refer [now cslurp]]))


(def ^:private cdb (make-couch :cloudant-production))

(defn update-user
  []
  (let [old (:value (first (cl/get-view cdb "zenid" "byZtype" {:key "user"})))]
    (cl/put-document cdb (assoc old :counter 40))))

(defn all-docs
  []
  (cl/all-documents cdb {:include_docs true}))

(defn delete-zenid
  []
  (doseq [doc (map :value (cl/get-view cdb "zenid" "byZtype"))]
    (cl/delete-document cdb doc)))

(defn add-zenids
  []
  (let [basic {:type "zenid"
               :counter 0}
        data (map #(assoc basic :ztype %)
                  ["user" "problem" "tutorial" "course"])]
    (cl/bulk-update cdb data)))

