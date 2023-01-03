(ns twitter-api.handlers.notfound
  (:require
   [clojure.data.json :as json]
   [compojure.core :refer :all]
   [ring.middleware.defaults :refer :all])
  (:gen-class))

(defn generic-not-found
  "Generic Not Found Result"
  [req]

  (let [uri (str (:uri req))]
    {:status 404
     :headers {"Content-Type" "application/json"}
     :body (json/write-str {:data {:message "Endpoint not found", :uri uri}})}))


