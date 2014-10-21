(ns pretelan.routes
	(:require [compojure.core :refer :all]
						[noir.response :as resp]
						[noir.session :as sess]
						[noir.cookies :as cook]
						[pretelan.backoffice.ctrl :as boctrl]
						[pretelan.backoffice.views :as bopage]
						[pretelan.site.views :as page]
						[pretelan.site.ctrl :as ctrl]))

(defn valid-admin?
	"Check whether the certain user is valid for admin access"
	([admin password]
	 (and (= admin "zenius")
				(= password "zenimomos")))
	([admin]
	 (= admin "zenius")))

(def home
	(context "" request
					 (GET "/" request
								(if-let [user (ctrl/valid-user (sess/get :user))]
									(page/home user)
									(page/home)))
					 (GET "/login" request
								(page/login))
					 (POST "/login-act" request
								 (let [{:keys [username password]} (:params request)]
									 (str "Welcome " username "!")))
					 (POST "/loginac" request
								 (let [{:keys [username password]} (:params request)]
									 (if (ctrl/valid-user username password)
										 (do (sess/put! :user username)
												 (resp/redirect "/account"))
										 (page/login "Wow sorry bro, username and password ngaco ;)"))))
					 (GET "/account" request
								(page/home))))

(def backoffice
	(context "/backoffice" request
					 (GET "/" request
								(if (sess/get :admin)
									(resp/redirect "/backoffice/home")
									(resp/redirect "/backoffice/login")))
					 (GET "/login" request
								(bopage/login "Login dulu bro!"))
					 (POST "/login-act" request
								 (let [{:keys [admin password]} (:params request)]
									 (if (valid-admin? admin password)
										 (do (sess/put! :admin admin)
												 (resp/redirect "/backoffice/home"))
										 (bopage/login "Your credential is questionable"))))
					 (GET "/logout" request
								(do (sess/clear!)
										(resp/redirect "/backoffice")))
					 (GET "/home" request
								(if (valid-admin? (sess/get :admin))
									(bopage/home "Get your ass to work!!")
									(bopage/login "fook dude, you've got no valid username")))))








