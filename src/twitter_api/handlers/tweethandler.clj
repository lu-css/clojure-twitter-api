(ns twitter-api.handlers.tweethandler
  (:require
   [clojure.data.json :as json]
   [clojure.tools.logging :as log]
   [compojure.core :refer :all]
   [ring.middleware.defaults :refer :all]
   [twitter-api.stores.tweets.database :as d])
  (:gen-class))

(defn post-twitter-handler
  [req]
  (let [tweet-json (:body req)
        saved (try
                (d/post-tweet tweet-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info tweet-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (if saved
                "Salvo com sucesso" "Erro ao salvar tweet")}))

(defn get-twitter-handler
  [username]

  (let [tweets (d/search-tweets-by-username username)]
    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str tweets)}))

(defn all-tweets-handler
  [_req]

  (let
   [tweets (d/get-all-tweets)]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/write-str tweets)}))
