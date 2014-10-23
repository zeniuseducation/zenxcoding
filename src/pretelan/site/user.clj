(ns pretelan.site.user
	(:require [pretelan.dbase :as db]
						[pretelan.site.views :as page]
						[noir.util.crypt :as crip]
						[com.ashafa.clutch :as cl]))

(def ^:private cdb (db/make-couch :cloudant-production))

(defn get-user
	"When called with one arg, it returns a user with a unique email, when called with two arguments it takes n (or
	fewer) users with the same username"
	([email]
	 (->> (cl/get-view cdb "user" "byEmail" {:key email})
				(first)
				(:value)))
	([n username]
	 (->> (cl/get-view cdb "user" "byUsername" {:key username})
				(take n)
				(map :value))))

(defn- compare-password
	"The act of comparing user password against the content in the db"
	[email password]
	(let [user-data (get-user email)]
		(if (empty? user-data)
			false
			(if (crip/compare password (:password user-data))
				(select-keys user-data [:email :username])
				false))))

(defn valid
	"Check whether a user is a valid user"
	([] nil)
	([email] (let [user-data (get-user email)]
						 (if (empty? user-data)
							 false
							 (select-keys user-data [:email :username]))))
	([email password] (compare-password email password)))

(defn sign-up
	[user-map]
	(->> {:user-id (get-zenid-id cdb :user)}
			 (merge user-map)
			 (cl/put-document cdb)))

(defn exists?
	[email]
	(empty? (get-user email)))
