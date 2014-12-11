;; Example Clojure main program, using both Java and Clojure library code
;;
;;
(ns skel.main
  (:gen-class)
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as str]
            [clojure.tools.nrepl.server :as nrepl-server]
            [cider.nrepl :refer (cider-nrepl-handler)])
  (:import [skel Example]))

;; ----------------------------------------------------------------------
;; CLI argument parsing

(def cli-options
  [[nil "--cider" "run cider-nrepl on 7888 and wait"]
   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :assoc-fn (fn [m k _] (update-in m [k] inc))]
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["Example Clojure Gradle program."
        ""
        "Usage: program-name [options] action"
        ""
        "Options:"
        options-summary]
       (str/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (str/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

;; ----------------------------------------------------------------------
;; cider-nrepl server for Emacs

(defn run-cider-nrepl
  []
  (println "running CIDER nrepl on localhost:7888...")
  (nrepl-server/start-server :port 7888 :handler cider-nrepl-handler))

;; ----------------------------------------------------------------------
;; main program

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    ;; Handle help and error conditions
    (cond
     (:help options) (exit 0 (usage summary))
     (:cider options) (run-cider-nrepl)
      errors (exit 1 (error-msg errors)))
    (println "Main in Clojure, calling Java:" Example/foo)))
