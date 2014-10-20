(ns pretelan.site.views
	(:require [pretelan.layout :as view]))

(def dir "/mainpages/")

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
	[]
	(view/render (res "login.html")))

