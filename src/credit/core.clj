(ns credit.core)

(defn digits [n]
  (->> n (map (comp read-string str))))

(defn add-digits
  [x]
  (if (< x 10)
    x
    (- x 9)))

(defn get-checksum
  [numbers]
  (let [partition-numbers (partition 2 2 [0] (reverse numbers))
        mapped-numbers (map #(second %) partition-numbers)
        mapped-numbers-not-added (reduce + (map #(first %) partition-numbers))]
    (+ mapped-numbers-not-added (reduce + (map #(add-digits (* 2 %)) mapped-numbers)))))

(defn is-valid?
  [credit]
  (zero? (mod (get-checksum credit) 10)))

(defn what-card
  [numbers]
  (cond
    (and
     (= 15 (count numbers))
     (= 3 (first numbers))
     (.contains [4 7] (second numbers))) "American Express"
    (or
     (and
      (= 16 (count numbers))
      (= 5 (first numbers))
      (.contains [1 2 3 4 5] (second numbers)))
     (and (< 222099 (+ (* 100000 (first numbers)) (* 10000 (second numbers)) (* 1000 (nth numbers 2)) (* 100 (nth numbers 3)) (* 10 (nth numbers 4)) (nth numbers 4)))
          (> 272100 (+ (* 100000 (first numbers)) (* 10000 (second numbers)) (* 1000 (nth numbers 2)) (* 100 (nth numbers 3)) (* 10 (nth numbers 4)) (nth numbers 4))))) "MasterCard"
    (and
     (or (= 13 (count numbers))
         (= 16 (count numbers))
         (= 19 (count numbers)))
     (= 4 (first numbers))) "Visa"
    (and
     (= 14 (count numbers))
     (= 3 (first numbers))
     (= 0 (second numbers))
     (.contains [0 1 2 3 4 5] (nth numbers 2))) "Diners Club - Carte Blanche"
    (and
     (= 14 (count numbers))
     (= 3 (first numbers))
     (= 6 (second numbers))) "Diners Club - International"
    (and
     (= 16 (count numbers))
     (= 5 (first numbers))
     (= 4 (second numbers))) "Diners Club - USA & Canada"
    (and
     (= 16 (count numbers))
     (= 6 (first numbers))
     (= 3 (second numbers))
     (.contains [7 8 9] (nth numbers 2))) "InstaPayment"
    (and
     (or (= 16 (count numbers))
         (= 19 (count numbers)))
     (and (> 3527 (+ (* 1000 (first numbers)) (* 100 (second numbers)) (* 10 (nth numbers 2)) (nth numbers 3)))
          (< 3589 (+ (* 1000 (first numbers)) (* 100 (second numbers)) (* 10 (nth numbers 2)) (nth numbers 3))))) "JCB"
    (and
     (or (= 16 (count numbers))
         (= 19 (count numbers)))
     (.contains [5018 5020 5038 5893 6304 6759 6761 6762 6763] (+ (* 1000 (first numbers)) (* 100 (second numbers)) (* 10 (nth numbers 2)) (nth numbers 3)))) "Maestro"
    (or
     (.contains [4026 4508 4844 4913 4917] (+ (* 1000 (first numbers)) (* 100 (second numbers)) (* 10 (nth numbers 2)) (nth numbers 3)))
     (= 417500 (+ (* 100000 (first numbers)) (* 10000 (second numbers)) (* 1000 (nth numbers 2)) (* 100 (nth numbers 3)) (* 10 (nth numbers 4)) (nth numbers 4)))) "Visa Electron"
    :else "INVALID"))

(defn check-card
  [card-number]
  (let [card-digits (digits card-number)]
    (if (is-valid? card-digits)
      (what-card card-digits)
      "Invalid")))


;; TESTS:
(check-card "378282246310005")
;; => "American Express"

(check-card "5555555555554444")
;; => "MasterCard"

(check-card "4012888888881881")
;; => "Visa"

(check-card "5893030312341981")
;; => "Maestro"

(check-card "30569309025904")
;; => "Diners Club - Carte Blanche"

(check-card "1234")
;; => "Invalid"
