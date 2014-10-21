(ns pretelan.site.ctrl
	(:require [pretelan.dbase :as db]
						[pretelan.site.views :as page]
						[noir.util.crypt :as crip]
						[com.ashafa.clutch :as cl]))

(def cdb (db/make-couch :cloudant-production))

(defn compare-user
	[username password]
	(let [user-data (:value (first (cl/get-view cdb "user" "byUsername" {:key username})))]
		(crip/compare password (:password user-data))))

(defn valid-user
	([username password]
	 (compare-user username password))
	([user]
	 (if (:value (first (cl/get-view cdb "user" "byUsername" {:key user})))
		 user
		 nil))
	([] nil))