(ns pretelan.dbase
	(:require [cemerick.url :as curl]))

(defn config
	[]
	(read-string (slurp "config.edn")))

(defn make-couch
	"Function to create an instance of a couch"
	[which-couch?]
	(let [{:keys [url dbname username password]}
				(get (config) which-couch?)]
		(assoc (curl/url url dbname)
			:username username
			:password password)))

(def quest "databasename")


