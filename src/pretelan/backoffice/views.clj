(ns pretelan.backoffice.views
	(:require [pretelan.layout :as view]))

(def dir "/backoffice/")

(defn res
	[fname]
	(str dir fname))

(defn login
	[msg]
	(view/render (res "login.html")
							 {:title "This is the login page"
								:headline msg}))

(defn home
	[msg]
	(view/render (res "home.html")
							 {:title "This is the home of backoffice"
								:headline msg}))



