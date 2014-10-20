(ns pretelan.site.ctrl
	(:require [pretelan.dbase :as db]
						[pretelan.site.views :as page]
						[noir.util.crypt :as crip]))

(def cdb (db/make-couch :cloudant-production))

(defn compare-user
	[username password]
	(let [user-data (:value (first (cl/get-view "user" "byUsername" {:key username})))]
		(crip/compare password (:password user-data))))

(defn valid-user
	([username password]
	 (compare-user username password))
	([user]
	 (if (first (cl/get-view "user" "byUsername" {:key user}))
		 user
		 nil))
	([] nil))