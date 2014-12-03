(ns liberator-tutorial.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [liberator.dev :refer [wrap-trace]]
            [compojure.core :refer [defroutes ANY]]))

(defn now-html []
  (let [now (System/currentTimeMillis)]
    (format "<html>It's %d milliseconds since the beginning of the epoch." now)
    )
  )

(defroutes app
           (ANY "/foo" []
                (resource :available-media-types ["text/html"]
                                    :handle-ok (fn [_]
                                                 (now-html))))
           (ANY "/babel" []
                (resource :available-media-types ["text/plain" "text/html"
                                                  "application/json" "application/clojure;q=0.9"]
                          :handle-ok
                          #(let [media-type
                                 (get-in % [:representation :media-type])]
                            (condp = media-type
                              "text/plain" "You requested plain text"
                              "text/html" "<html><h1>You requested HTML</h1></html>"
                              {:message "You requested a media type"
                               :media-type media-type}))
                          :handle-not-acceptable "Uh, Oh, I cannot speak those languages!")))

(def handler
  (-> app
      wrap-params))