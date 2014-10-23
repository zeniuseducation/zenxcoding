(ns pretelan.backoffice.views
  (:require [pretelan.layout :as view]
            [hiccup.core :refer [html]]
            [hiccup.def :refer [defhtml]]
            [pretelan.backoffice.ctrl :as ctrl]))

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

(defhtml users-page []
  [:html
   [:head
    [:title "to the title tlo"]]
   [:body
    [:center
     [:h1 "Daftar users"]
     (repeat 3 [:br])
     [:table
      (vec
       (let [all-users (ctrl/all-users)
             i (atom 0)]
         (for [{:keys [username email]} all-users]
           [:tr
            [:td (str (swap! i inc))]
            [:td username]
            [:td email]])))]]]])

(defn users [] (users-page))


