(ns pretelan.site.views
  (:require [pretelan.layout :as view]
            [pretelan.site.user :as user]
            [pretelan.site.problem :as prob]))

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
                :problems (prob/problems user)
                :page "problems"
                :message (str "You are logged-in as " user)
                :links member-links}))

(defn problem
  [no]
  (let [{:keys [content no]}
        (prob/problem (read-string no))]
    (view/render (res "problem.html")
                 {:title "These are quests... my padawan/padawati"
                  :content content
                  :no no
                  :page "problem"
                  :message (str "You are logged-in as " )
                  :links member-links})))

(defn account
  [username email]
  (view/render (res "account.html")
               {:page "account"
                :user (let [data (user/get-user email)]
                        (assoc data
                          :problems (sort-by :date (:problems data))))
                :title "Update your account"
                :links member-links}))

(defn login
  []
  (view/render (res "login.html")
               {:title   "Login dulu dong"
                :page    "login"
                :message "Attempting login in..."
                :links   guest-links}))

(defn ranks
  []
  (view/render (res "ranks.html")
               {:title "Ranks of the rulers"
                :page "ranks"
                :message "Test y'alll"
                :users (user/ranks)
                :links member-links}))


(defn sign-up
  []
  (view/render (res "signup.html")
               {:title "Signup men"
                :page "signup"
                :message "Me attempting  signup"
                :links guest-links}))











