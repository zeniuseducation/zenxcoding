(ns pretelan.core
	(:require [reagent.core :as reagent :refer [atom render-component]]
						[ajax.core :refer [GET POST]]))

(defn selid
	[id]
	(.getElementById js/document id))

(defn get-page
	[]
	(str (.-className (selid "body"))))

(defn login-callback
	[response]
	(.alert js/window (:message response)))

(defn login-act
	[email password]
	(POST "/login-act"
				{:params  {:email email :password password}
				 :handler login-callback}))

(defn login-form
	[]
	(let [email (atom "")
				password (atom "")]
		(fn []
			[:fieldset.zpanel3
			 [:legend "Login bukan lojin"]
			 [:br]
			 [:input {:type        "text"
								:value       @email
								:id          "user-email"
								:on-change   #(reset! email (-> % .-target .-value))
								:placeholder "email as unique identifier"}]
			 [:input {:type        "password"
								:value       @password
								:id          "user-password"
								:on-change   #(reset! password (-> % .-target .-value))
								:placeholder "password"}]
			 [:button {:class    "small right radius"
								 :id       "login-button"
								 :on-click #(login-act @email @password)}
				"Login"]])))

(defn signup-callback
	[response]
	(if (:status response)
		(.alert js/window (:message response))
		(.alert js/window (:message response))))

(defn signup-act
	[user-map]
	(POST "/signup-act"
				{:params user-map
				 :handler signup-callback}))

(defn error-message
	[]
	[:p "Something wrong here!"])

(defn signup-form
	[]
	(let [email (atom "")
				email-confirmation (atom "")
				password (atom "")
				password-confirmation (atom "")
				username (atom "")
				nama (atom "")
				twitter (atom "")
				languages (atom "")]
		(fn []
			[:fieldset.zpanel3
			 [:legend "Sign-up form"]
			 [:br]
			 [:div.medium-9.medium-centered.columns
				[:input {:type        "text"
								 :value       @email
								 :id          "email"
								 :on-change   #(reset! email (-> % .-target .-value))
								 :placeholder "email"}]
				[:input {:type        "text"
								 :value       @email-confirmation
								 :id          "email-confirmation"
								 :on-change   #(reset! email-confirmation (-> % .-target .-value))
								 :placeholder "masukin email lagi (konfirmasi)"}]
				[:input {:type        "password"
								 :value       @password
								 :id          "password"
								 :on-change   #(reset! password (-> % .-target .-value))
								 :placeholder "password"}]
				[:input {:type        "password"
								 :value       @password-confirmation
								 :id          "password-confirmation"
								 :on-change   #(reset! password-confirmation (-> % .-target .-value))
								 :placeholder "Konfirmasi password"}]
				[:input {:type        "text"
								 :value       @username
								 :id          "username"
								 :on-change   #(reset! username (-> % .-target .-value))
								 :placeholder "Username/Nickname"}]
				[:input {:type        "text"
								 :value       @nama
								 :id          "nama"
								 :on-change   #(reset! nama (-> % .-target .-value))
								 :placeholder "Nama beneran"}]
				[:input {:type        "text"
								 :value       @twitter
								 :id          "twitter"
								 :on-change   #(reset! twitter (-> % .-target .-value))
								 :placeholder "twitter account (pake @)"}]
				[:input {:type        "text"
								 :value       @languages
								 :id          "languages"
								 :on-change   #(reset! languages (-> % .-target .-value))
								 :placeholder "Your programming languages (boleh > 1)"}]
				[:button {:class    "small right radius"
									:id       "signup-button"
									:on-click #(if (and (= @email @email-confirmation)
																			(= @password @password-confirmation))
															(signup-act {:username  @username
																					 :email     @email
																					 :nama      @nama
																					 :twitter   @twitter
																					 :languages @languages
																					 :password  @password})
															(render-component [error-message]
																								(selid "message-holder")))}
				 "Sign-up"]]])))

(defn start
	[page]
	(condp = page
		"login" (render-component [login-form]
															(selid "login-form"))
		"signup" (render-component [signup-form]
															 (selid "signup-form"))))

(start (get-page))


