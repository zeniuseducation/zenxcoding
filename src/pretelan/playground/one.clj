(ns pretelan.playground.one)

(defn expt
  [a m]
  (if (zero? m) 1 (*' a (expt a (dec m)))))

(def big (expt 10 10))

(defn sol [lim]
  (reduce #(rem (+ %1 (expt %2 %2)) big) (range 1 (inc lim))))

(defn sola [lim]
  (reduce + (pmap #(rem (expt % %) big) (range 1 (inc lim)))))

(defn sol6
  [lim]
  (for [i (range 1 (inc lim))]
    (+ 120 (* i 12))))
