(ns pretelan.site.views
	(:require [pretelan.layout :as view]
						[pretelan.util :refer [md->html]]
						[pretelan.soal.ctrl :refer [soal]]))

(def dir "mainpages/")

"this is a test"

(defn res [fname] (str dir fname))

(defn home
	([] (view/render (res "home.html")
									 {:title "Welcome to zencoding"
									  :headline "Welcome to zencoding"
									  :nav-links [{:url "/login" :title "Lojeen | "}
																{:url "/signup" :title "Sign-up"}]}))
	([user] (view/render (res "home.html")
											 {:title (str "Welcome " user " to zencoding")
											  :headline (str "Welcome " user " to zencoding")
											 :nav-links [{:url "/logout" :title "Logout | "}
																	 {:url "/problems" :title "Problems"}]})))

(defn login
	([]
	 (view/render (res "login.html")
								{:title "Login dulu dong"
								 :message "Attempting login in..."}))
	([message]
	 (view/render (res "login.html")
								{:title "Login pluuisss"
								 :message message})))

(defn testmd
	[]
	(view/render (res "home.html")
							 {:soal (soal "one.html")
							 :mereka "welldome"}))

