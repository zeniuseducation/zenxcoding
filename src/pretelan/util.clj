(ns pretelan.util
  (:require [noir.io :as io]
            [markdown.core :as md]
            [clj-time.core :as t]))

(defn md->html
  "reads a markdown file from public/md and returns an HTML string"
  [filename]
  (md/md-to-html-string (slurp filename)))

(defn now
  "Returns the current date in a string format"
  []
  (subs (str (t/now)) 0 10))

(defn cslurp
  "Read-string slurp filename"
  [filename]
  ((comp read-string slurp) filename))

