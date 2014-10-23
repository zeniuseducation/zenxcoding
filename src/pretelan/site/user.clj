(ns pretelan.site.user
  (:require [pretelan.dbase :as db :refer [get-zenid make-couch]]
            [noir.util.crypt :as crip]
            [com.ashafa.clutch :as cl]))

(def ^:private cdb (make-couch :local-couch))

(defn all-users
  []
  (map :value (cl/get-view cdb "user" "byEmail")))

(defn get-user
  "When called with one arg, it returns a user with a unique email,
  when called with two arguments it takes n (or fewer) users with the
  same username"
  ([email]
     (->> (cl/get-view cdb "user" "byEmail" {:key email})
          (first)
          (:value)))
  ([n username]
     (->> (cl/get-view cdb "user" "byUsername" {:key username})
          (take n)
          (map :value))))

(defn- compare-password
  "The act of comparing user password against the content in the db"
  [email password]
  (let [user-data (get-user email)]
    (if (empty? user-data)
      false
      (if (crip/compare password (:password user-data))
        (select-keys user-data [:email :username])
        false))))

(defn valid
  "Check whether a user is a valid user, returns user-map if valid"
  ([] nil)
  ([email] (let [user-data (get-user email)]
             (if (empty? user-data)
               false
               (select-keys user-data [:email :username]))))
  ([email password] (compare-password email password)))

(defn sign-up
  "The act of adding user into database"
  [user-map]
  (->> {:user-id (get-zenid cdb "user")
        :password (crip/encrypt (:password user-map))
        :type "user"}
       (merge user-map)
       (cl/put-document cdb)))

(defn exists?
  "Check whether an email of a user exists in db"
  [email]
  (not (empty? (get-user email))))

