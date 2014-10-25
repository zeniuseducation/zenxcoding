(ns pretelan.core
  (:require [reagent.core :as reagent :refer [atom render-component]]
            [ajax.core :refer [GET POST]]))

(def current-user (atom {}))

(defn selid
  "selector by id"
  [id]
  (.getElementById js/document id))

(defn get-page
  "Returns the classname of a body to identify which page are we in"
  []
  (str (.-className (selid "body"))))

(defn problem-no
  "Returns the problem no.."
  []
  (str (.-className (selid "problem-no"))))

(defn post-login
  []
  [:div.medium-12.medium-centered.columns
   [:h3 "You're logged-in, may the codes be with you..."]
   [:br]
   [:h4 "Go ahead, starts navigating the "
    [:a {:href "/problems"}
     "problems."]]])

(defn login-callback
  "the callback function for login."
  [response]
  (if (:status response)
    (render-component [post-login]
                      (selid "main-login"))
    (.alert js/window (:message response))))

(defn login-error-callback
  [response]
  (.alert js/window "Tampaknya error, bisa aja salah password"))

(defn login-act
  "The act of posting email password when login through ajax, using
  edn"
  [email password]
  (POST "/login-act"
        {:params  {:email email :password password}
         :handler login-callback
         :error-handler login-error-callback}))

(defn login-form
  "Login-form component with logic to submit the form through ajax"
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

(defn post-signup
  []
  [:div.medium-12.medium-centered.columns
   [:h3 "Now that you're signed-up, please try to login "
    [:a {:href "/login"} "here!"]]])

(defn signup-callback
  "Callback function for signup"
  [response]
  (if (:status response)
    (render-component [post-signup]
                      (selid "signup-form"))
    (.alert js/window (:message response))))

(defn signup-act
  "The act of posting the user-map through ajax for registration"
  [user-map]
  (POST "/signup-act"
        {:params user-map
         :handler signup-callback}))

(defn error-message
  []
  [:p "Something wrong here!"])

(defn signup-form
  "The signup form component with the logic embedded"
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

(defn post-answer
  []
  [:div.medium-10.medium-centered.columns
   [:div.medium-4.columns
    [:img {:src "/img/jempol.jpg"}]]
   [:div.medium-8.columns
    [:h1 "Congratulations!"]
    [:h2 "You've Solved this one!!"]
    [:h4 (str "You've now solved " (:solved @current-user) " problem(s)")]
    [:h4 (str "and your score is now " (:score @current-user))]]])

(defn answer-callback
  "the callback function for login."
  [response]
  (if (:status response)
    (do (swap! current-user merge
               {:solved (:solved response)
                :score (:score response)})
        (render-component [post-answer]
                          (selid "answer-form")))
    (.alert js/window (:message response))))

(defn answer-act
  "The act of posting email password when login through ajax, using
  edn"
  [answer no]
  (POST "/problems/answer-act"
        {:params  {:answer answer :no no}
         :handler answer-callback}))

(defn answer-form
  "Login-form component with logic to submit the form through ajax"
  []
  (let [answer (atom "")]
    (fn []
      [:fieldset.zpanel3.medium-8.medium-centered.columns
       [:legend "Submit your answer here"]
       [:br]
       [:input {:type        "text"
                :value       @answer
                :id          "answer"
                :on-change   #(reset! answer (-> % .-target .-value))}]
       [:button {:class    "small right radius"
                 :id       "submit"
                 :on-click #(answer-act @answer (problem-no))}
        "Submit"]])))

(defn post-account
  []
  [:div.medium-12.medium-centered.columns
   [:h3 "Your account has been updated"]])

(defn account-callback
  "Callback function for signup"
  [response]
  (if (:status response)
    (render-component [post-account]
                      (selid "account-form"))
    (.alert js/window (:message response))))

(defn account-act
  "The act of posting the user-map through ajax for registration"
  [user-map]
  (POST "/account-act"
        {:params user-map
         :handler account-callback}))

(defn error-message
  []
  [:p "Something wrong here!"])


(defn account-form
  "The signup form component with the logic embedded"
  []
  (let [{:keys [email username nama twitter languages]} @current-user
        email (atom email)
        email-confirmation (atom @email)
        password (atom "")
        password-confirmation (atom "")
        username (atom username)
        nama (atom nama)
        twitter (atom twitter)
        languages (atom languages)]
    (fn []
      [:fieldset.zpanel3
       [:legend "Edit account"]
       [:br]
       [:div.medium-10.medium-centered.columns
        [:h5 "Email"]
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
                 :placeholder "PASTIIN ISI PASSWORD"}]
        [:h5 "Username"]
        [:input {:type        "text"
                 :value       @username
                 :id          "username"
                 :on-change   #(reset! username (-> % .-target .-value))
                 :placeholder "Username/Nickname"}]
        [:h5 "Nama beneran"]
        [:input {:type        "text"
                 :value       @nama
                 :id          "nama"
                 :on-change   #(reset! nama (-> % .-target .-value))
                 :placeholder "Nama beneran"}]
        [:h5 "Twitter account"]
        [:input {:type        "text"
                 :value       @twitter
                 :id          "twitter"
                 :on-change   #(reset! twitter (-> % .-target .-value))
                 :placeholder "twitter account (pake @)"}]
        [:h5 "Chosen languages"]
        [:input {:type        "text"
                 :value       @languages
                 :id          "languages"
                 :on-change   #(reset! languages (-> % .-target .-value))
                 :placeholder "Your programming languages (boleh > 1)"}]
        [:button {:class    "small right radius"
                  :id       "account-button"
                  :on-click #(if (and (= @email @email-confirmation)
                                      (= @password @password-confirmation))
                               (account-act {:username  @username
                                             :email     @email
                                             :nama      @nama
                                             :twitter   @twitter
                                             :languages @languages
                                             :password  @password})
                               (render-component [error-message]
                                                 (selid "message-holder")))}
         "Edit account"]]])))

(defn handle-user
  [resp]
  (do (reset! current-user resp)
      (render-component [account-form]
                        (selid "account-form"))))

(defn request-user
  []
  (GET "/request-user"
       {:handler handle-user}))

(defn start
  "The act of mounting necessary components based on the page"
  [page]
  (condp = page
    "login" (render-component [login-form]
                              (selid "login-form"))
    "signup" (render-component [signup-form]
                               (selid "signup-form"))
    "problem" (render-component [answer-form]
                                (selid "answer-form"))
    "account" (request-user)
    "ranks" nil
    "problems" nil))

(start (get-page))



















