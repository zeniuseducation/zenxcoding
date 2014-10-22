(ns pretelan.site.ctrl
	(:require [pretelan.dbase :as db]
						[pretelan.site.views :as page]
						[noir.util.crypt :as crip]
						[com.ashafa.clutch :as cl]))

(def cdb (db/make-couch :cloudant-production))

(defn compare-user
	[username password]
	(if-let [user-data (->> {:key username}
													(cl/get-view
														cdb
														"user"
														"byUsername")
													(first)
													:value)]
		(crip/compare password (:password user-data))
		false))

(defn valid-user
	([username password]
	 (compare-user username password))
	([user]
	 (if (->> {:key user}
						(cl/get-view cdb "user" "byUsername")
						(first)
						:value)
		 user
		 nil))
	([] nil))