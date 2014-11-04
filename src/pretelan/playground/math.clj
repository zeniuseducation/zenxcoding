(ns pretelan.playground.math)

(defn div? [a b] (zero? (rem a b)))

(defn prime?
  [n]
  (let [primes (atom [2 3 5 7])]
    (loop [[p & ps] @primes]
      (if (> p (Math/sqrt n))
        (do (swap! primes conj n)
            true)
        (if (div? n p)
          false
          (recur ps))))))


