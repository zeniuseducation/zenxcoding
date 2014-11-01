(ns pretelan.routes
  (:require [compojure.core :refer :all]
            [noir.response :as resp]
            [noir.session :as sess]
            [pretelan.backoffice.ctrl :as boctrl]
            [pretelan.backoffice.views :as bopage]
            [pretelan.site.views :as page]
            [pretelan.site.user :as user]
            [pretelan.site.problem :as problem]
            [pretelan.site.addhoc :as addhoc]))

"Some stuffs"

(defn valid-admin?
  "Check whether the certa in user is valid for admin access"
  ([admin password]
     (and (= admin "zenius")
          (= password "zenius")))
  ([admin]
     (= admin "zenius")))

(def home
  (context "" request
           (GET "/" request
                (if (sess/get :email)
                  (page/home (sess/get :username))
                  (page/home)))
           (GET "/account" request
                (if (sess/get :email)
                  (page/account (sess/get :username)
                                (sess/get :email))
                  (resp/redirect "/login")))
           (GET "/login" request
                (page/login))
           (GET "/logout" request
                (do (sess/clear!)
                    (resp/redirect "/")))
           (GET "/ranks" request
                (do (println "Ini ga error kok")
                    (page/ranks)))
           (GET "/request-user" request
                (let [email (sess/get :email)
                      user (user/get-user email)]
                  (resp/edn (dissoc user :password))))
           (GET "/signup" request
                (page/sign-up))
           (POST "/account-act" request
                 (do (user/update-user (:email (:params request))
                                       (:params request))
                     (resp/edn {:status true :message "sukses"})))
           (POST "/login-act" request
                 (let [{:keys [email password]}
                       (:params request)]
                   (if-let [{:keys [email username]}
                            (user/valid email password)]
                     (do (sess/put! :email email)
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
                         (resp/edn {:status true
                                    :message "Welcome to Zenius League!!"})))))))

(def problems
  (context "/problems" request
           (GET "/" request
                (page/problems (sess/get :email)))
           (GET "/problem/:nomer" [nomer]
                (page/problem nomer))
           (POST "/answer-act" request
                 (let [user-email (sess/get :email)
                       {:keys [answer no]} (:params request)]
                   (if (problem/check-answer? (read-string no)
                                              (read-string answer)
                                              user-email)
                     (let [{:keys [score solved]}
                           (user/get-user user-email)]
                       (resp/edn {:status true
                                  :message "Gokil!"
                                  :score score
                                  :solved solved}))
                     (resp/edn {:status false :message (str "Jawaban no "
                                                            no
                                                            " masih salah "
                                                            (sess/get :username))}))))))

(def tutorials
  (context "/tutorials" request
           (GET "/" request
                (page/courses))
           (GET "/course/:courseid/:tutorialid" [courseid tutorialid]
                (page/course (read-string courseid)
                             (read-string tutorialid)))))

(def playground
  (context "/playground" request
           (GET "/problem/:problem" [problem]
                (addhoc/ambil-problem problem))
           (GET "/tutorial/:tutorial" [tutorial]
                (addhoc/ambil-tutorial tutorial))))

(def backoffice
  (context "/backoffice" request
           (GET "/" request
                (if (sess/get :admin)
                  (resp/redirect "/backoffice/home")
                  (resp/redirect "/backoffice/login")))
           (GET "/login" request
                (bopage/login "Login dulu bro"))
           (GET "/users" request
                (bopage/users))
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









