(ns pretelan.routes
	(:require [compojure.core :refer :all]
						[noir.response :as resp]
						[noir.session :as sess]
						[noir.cookies :as cook]
						[pretelan.backoffice.ctrl :as boctrl]
						[pretelan.backoffice.views :as bopage]
						[pretelan.site.views :as page]
						[pretelan.site.user :as user]))

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
								(if (sess/get :email)
									(page/home (sess/get :username))
									(if-let [email (cook/get :email)]
										(let [{:keys [username email]}
													(user/get-user email)]
											(do (sess/put! :email email)
													(sess/put! :username username)
													(page/home username)))
										(page/home))))
					 (GET "/login" request
								(page/login))
					 (GET "/signup" request
								(page/sign-up))
					 (GET "/logout" request
								(do (sess/clear!)
										(resp/redirect "/")))
					 (GET "/account" request
								(page/account (sess/get :username)))
					 (POST "/login-act" request
								 (let [{:keys [email password]}
											 (:params request)]
									 (if-let [{:keys [email username]}
														(user/valid email password)]
										 (do (cook/put! :email email)
												 (sess/put! :email email)
												 (sess/put! :username username)
												 (resp/edn {:status  true
																		:message "May the codes be with you..."}))
										 (resp/edn {:status false
																:message "Email doesnt exist or wrong password"}))))
					 (POST "/signup-act" request
								 (let [{:keys [username email  password nama twitter languages]}
											 (:params request)]
									 (if (user/exists? email)
										 (resp/edn {:status false :message "Email already used"})
										 (do (println (:params request))
												 (user/sign-up {:username  username
																				:email     email
																				:nama      nama
																				:twitter   twitter
																				:languages languages
																				:password  password})
												 (resp/edn {:status true :message "Welcome to Zenius League!!"})))))))

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








