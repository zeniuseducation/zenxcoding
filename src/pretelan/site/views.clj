(ns pretelan.site.views
  (:require [pretelan.layout :as view]
            [pretelan.site.user :as user]
            [pretelan.soal.ctrl :refer [soal]]))

(def ^:private guest-links
  [{:url "/login" :name "Lojeen"}
   {:url "/signup" :name "Sign-up"}])

(def ^:private member-links
  [{:url "/tutorials" :name "Tutorials"}
   {:url "/problems" :name "Problems"}
   {:url "/ranks" :name "Ranks"}
   {:url "/account" :name "Account"}
   {:url "/logout" :name "Logout"}])

(def ^:private dir "mainpages/")

(defn res [fname] (str dir fname))

(defn home
  ([] (view/render (res "home.html")
                   {:title "Otak booster category 5"
                    :page "home"
                    :links guest-links}))
  ([user] (view/render (res "home.html")
                       {:title (str "Welcome " user " to zencoding")
                        :page "home"
                        :message (str "Welcome " user)
                        :links member-links})))

(defn problems
  [user]
  (view/render (res "problems.html")
               {:title "These are your quests... my padawan/padawati"
                :page "problems"
                :message (str "You are logged-in as " user)
                :links member-links}))

(defn account
  [username email]
  (view/render (res "home.html")
               {:page "home"
                :links member-links}))

(defn login
  []
  (view/render (res "login.html")
               {:title   "Login dulu dong"
                :page    "login"
                :message "Attempting login in..."
                :links   guest-links}))


(defn sign-up
  []
  (view/render (res "signup.html")
               {:title "Signup men"
                :page "signup"
                :message "Me attempting  signup"
                :links guest-links}))











