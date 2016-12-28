(defproject jaredbowie-2016 "0.1.0-SNAPSHOT"
  :description "Generates personal static site from text files."
  :url "http://jaredbowie.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.5"]
                 [me.raynes/fs "1.4.6"]
                 [clj-time "0.12.2"]
                 ]
  :main ^:skip-aot jaredbowie-2016.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
