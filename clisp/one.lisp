(load "clojure.lisp")

(defparameter *cds* '(1 (:a 1 :b 2 :c 3 :d 4)))

(defparameter cdb '())
(defparameter *current-id* 0)

(defun create-cd (title description year)
  (list :id (incf *current-id*) :title title :description description :year year))

(defun add-cd (cd)
  (push cd cdb))



