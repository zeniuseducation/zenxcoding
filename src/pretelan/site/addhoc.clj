(ns pretelan.site.addhoc
  (:require [pretelan.dbase :refer [make-couch all-zenids]]
            [pretelan.site.problem :as problem]
            [pretelan.site.tutorial :as tutorial]
            [pretelan.site.user :as user]
            [com.ashafa.clutch :as cl]
            [pretelan.util :refer [now cslurp]]
            [pretelan.layout :as view]))


(def ^:private cdb (make-couch :cloudant-production))

(defn update-user
  []
  (let [old (:value (first (cl/get-view cdb "zenid" "byZtype" {:key "user"})))]
    (cl/put-document cdb (assoc old :counter 40))))

(defn all-docs
  []
  (cl/all-documents cdb {:include_docs true}))

(defn delete-zenid
  []
  (doseq [doc (map :value (cl/get-view cdb "zenid" "byZtype"))]
    (cl/delete-document cdb doc)))

(defn add-zenids
  []
  (let [basic {:type "zenid"
               :counter 0}
        data (map #(assoc basic :ztype %)
                  ["user" "problem" "tutorial" "course"])]
    (cl/bulk-update cdb data)))

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

(defn ambil-problem
  [fname]
  (view/render (res "problem.html")
               {:title "Solve it for the wind... :P"
                :content (problem/problem-content fname)
                :no fname
                :page "problem"
                :message (str "You are logged-in as " )
                :links member-links}))

(defn ambil-tutorial
  [fname]
  (view/render (res "course.html")
               {:title "Tutorial brother"
                :tutorial {:content (tutorial/tutorial-content fname)}
                :no fname
                :page "tutorial"
                :message (str "You are logged-in as " )
                :links member-links}))







