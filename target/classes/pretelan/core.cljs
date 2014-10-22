(ns pretelan.core
	(:require [reagent.core :as reagent :refer [atom render-component]]
						[ajax.core :refer [GET POST]]))

(defn selid
	[id]
	(.getElementById js/document id))

(defn get-page
	[]
	(str (.-className (selid "body"))))

(defn answer-form
	[]
	[:div {:class "large-4 large-centered"}
	 [:input {:type "text" :id "beliga"}]
	 [:button {:on-click #(.alert js/window "Wow cool!!")} "Submit"]])

(defn login-callback
	[response]
	(.alert js/window response))

(defn login-act
	[username password]
	(POST "/login-act"
				{:params  {:username username :password password}
				 :handler login-callback}))

(defn login-form
	[]
	(let [email (atom "")
				password (atom "")]
		(fn []
			[:fieldset {:class "zpanel3"}
			 [:legend "Login bukan lojin"]
			 [:br]
			 [:input {:type        "text"
								:value       @email
								:id          "userEmail"
								:on-change   #(reset! email (-> % .-target .-value))
								:placeholder "email as unique identifier"}]
			 [:input {:type        "password"
								:value       @password
								:id          "userPassword"
								:on-change   #(reset! password (-> % .-target .-value))
								:placeholder "password"}]
			 [:button {:class    "small right"
								 :id       "loginButton"
								 :on-click #(login-act @email @password)}
				"Login"]])))

(defn start
	[page]
	(cond
		(= page "login") (render-component [login-form]
																			 (selid "loginForm"))
		(= page "home") (render-component [answer-form]
																				 (selid "main"))))

(start (get-page))


