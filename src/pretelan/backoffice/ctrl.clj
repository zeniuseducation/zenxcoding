(ns pretelan.backoffice.ctrl
  (:require [com.ashafa.clutch :as cl]
            [pretelan.dbase :as db :refer [get-zenid make-couch]]))

(def ^:private cdb (make-couch :cloudant-production))

(defn all-users
  []
  (map :value (cl/get-view cdb "user" "byEmail")))


