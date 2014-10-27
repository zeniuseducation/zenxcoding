(ns pretelan.site.user
  (:require [pretelan.dbase :as db :refer [get-zenid make-couch]]
            [noir.util.crypt :as crip]
            [pretelan.util :refer [now]]
            [com.ashafa.clutch :as cl]))

(def ^:private cdb (make-couch :cloudant-production))

(defn all-users
  "Retrieve all users in database with their complete information"
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

(defn selected-keys
  "Invoke a pre-defined couch views for user design document"
  [target]
  (map :value (cl/get-view cdb "user" target)))

(defn ranks
  "Generate user's ranks"
  []
  (map #(assoc %1 :rank %2)
       (reverse (sort-by :score (selected-keys "forRank")))
       (iterate inc 1)))

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

(defn total-users
  "Returns the theoretical number of users in database"
  []
  (->> {:key "user"}
       (cl/get-view cdb "zenid" "byZtype")
       first
       :value
       :counter))

(defn sign-up
  "The act of adding user into database"
  [user-map]
  (->> {:user-id (get-zenid cdb "user")
        :password (crip/encrypt (:password user-map))
        :score 0
        :solved 0
        :type "user"
        :problems []
        :date (now)}
       (merge user-map)
       (cl/put-document cdb)))

(defn update-user
  "Update the user's data"
  [user-map]
  (let [email (:email user-map)
        old-data (get-user email)
        final-data (assoc user-map
                     :password
                     (crip/encrypt (:password user-map)))]
    (cl/put-document cdb
                     (merge old-data
                            user-map))))

(defn exists?
  "Check whether an email of a user exists in db"
  [email]
  (not (empty? (get-user email))))








