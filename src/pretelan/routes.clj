(ns pretelan.routes
  (:require [compojure.core :refer :all]
						[noir.response :as resp]
						[noir.session :as sess]
						[noir.cookies :as cook]
						[pretelan.backoffice.ctrl :as bo]
						[pretelan.site.ctrl :as home]))

(defn valid-admin?
	"Check whether the certain user is valid for admin access"
	([admin password]
	 (and (= admin "zenius")
				(= password "zenimomos")))
	([admin]
	 (= admin "zenius")))

(def home
  (context "/" request
					 (GET "/" request
								"You ain't nothing to see here!!!")))

(def backoffice
	(context "/backoffice" request
					 (GET "/" request
								(if-let [admin (sess/get :admin)]
									(resp/redirect "/home")
									(resp/redirect "/login")))
					 (GET "/login" request
								(bo/login "Login dulu bro!"))
					 (POST "/login-act" request
								 (let [{:keys [admin password]} (:params request)]
									 (if (valid-admin? admin password)
										 (do (sess/put! :admin admin)
												 (resp/redirect "/home"))
										 (bo/login "Your credential is questionable"))))
					 (GET "/home" request
								(if (valid-admin? (sess/get :admin))
									(bo/home "Get your ass to work!!")
									(bo/login "fook dude, you've got no valid username")))))








