(ns credit.core)

(seq (char-array "123456"))

(defn digits [n]
  (->> n (map (comp read-string str))))

(defn add-digits
  [x]
  (if (< x 10)
    x
    (- x 9)))

(defn get-checksum
  [numbers]
  (let [digits-parsed (digits numbers)
        numbers-padded (if (even? (count digits-parsed))
                         digits-parsed
                         (conj digits-parsed 0))
        partition-numbers (partition 2 (reverse numbers-padded))
        mapped-numbers (map #(second %) partition-numbers)
        mapped-numbers-not-added (reduce + (map #(first %) partition-numbers))]
    (println partition-numbers)
    (println mapped-numbers)
    (println mapped-numbers-not-added)
    (+ mapped-numbers-not-added (reduce + (map #(add-digits (* 2 %)) mapped-numbers)))))


(get-checksum "4000000760000002")
;; => (0 9 7 5 3 1 8 6 4 2)


(defn is-valid?
  [credit]
  (zero? (mod (get-checksum credit) 10)))

(is-valid? "378282246310005")

(conj '(1 2 3 4) 5)
