(ns pretelan.site.views
	(:require [pretelan.layout :as view]
						[pretelan.util :refer [md->html]]
						[pretelan.soal.ctrl :refer [soal]]))

(def dir "mainpages/")

(defn res [fname] (str dir fname))

(defn home
	([] (view/render (res "home.html")
									 {:title "Otak booster category 5"
										:page "home"
										:links [{:url "/tutorials" :name "Tutorials"}
														{:url "/problems" :name "Problems"}
														{:url "/ranks" :name "Ranks"}
														{:url "/login" :name "Lojeen"}
														{:url "/signup" :name "Sign-up"}]}))
	([user] (view/render (res "home.html")
											 {:title (str "Welcome " user " to zencoding")
												:message (str "Welcome " user)
												:links [{:url "/tutorials" :name "Tutorials"}
																{:url "/problems" :name "Problems"}
																{:url "/ranks" :name "Ranks"}
																{:url "/account" :name "Account"}
																{:url "/logout" :name "Logout"}]})))

(defn login
	([]
	 (view/render (res "login.html")
								{:title   "Login dulu dong"
								 :page "login"
								 :message "Attempting  login in..."
								 :links   [{:url "/tutorials" :name "Tutorials"}
													 {:url "/problems" :name "Problems"}
													 {:url "/ranks" :name "Ranks"}
													 {:url "/login" :name "Lojeen"}
													 {:url "/signup" :name "Sign-up"}]}))
	([message]
	 (view/render (res "login.html")
								{:title "Login pluuisss"
								 :message message})))



