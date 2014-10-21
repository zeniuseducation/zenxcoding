(ns pretelan.soal.ctrl)

(defn soal
	[fname]
	(slurp (str "resources/soal/" fname)))
