(ns pretelan.dbase
  (:require [cemerick.url :as curl]
            [com.ashafa.clutch :as cl]
            [pretelan.config :refer :all]))

(defn config
  []
  config-data)

(defn make-couch
  "Function to create an instance of a couch"
  [which-couch?]
  (let [{:keys [url dbname username password]}
        (get (config) which-couch?)]
    (assoc (curl/url url dbname)
      :username username
      :password password)))

(def quest "databasename")

(defn get-zenid
  [cdb ztype]
  (let [old-data (->> {:key ztype}
                      (cl/get-view cdb "zenid" "byZtype")
                      (first)
                      (:value))
        counter (:counter old-data)]
    (do (->> (inc counter)
             (assoc old-data :counter)
             (cl/put-document cdb))
        (inc counter))))

(defn all-zenids
  [cdb]
  (map :value (cl/get-view cdb "zenid" "byZtype")))

(defn create-type
  [cdb ztype]
  (if (empty? (cl/get-view cdb "zenid" "byZtype" {:key ztype}))
    (cl/put-document cdb {:type "zenid" :counter 0 :ztype ztype})
    (->> (cl/get-view cdb "zenid" "byZtype" {:key ztype})
         (first)
         (:value))))






