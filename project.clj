(defproject liberator-tutorial "0.1.0-SNAPSHOT"
            :description "Liberator based prototype"
            :plugins [[lein-ring "0.8.13"]]
            :ring {:handler liberator-tutorial.core/handler}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [liberator "0.12.0"]
                           [compojure "1.2.2"]
                           [ring/ring-core "1.3.2"]])

