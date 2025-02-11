(ns twitter-api.core
  (:require
   [compojure.core :refer :all]
   [org.httpkit.server :as server]
   [ring.middleware.defaults :refer [api-defaults, wrap-defaults]]
   [ring.middleware.json :as mj]
   [twitter-api.handlers.tweethandler :as tweetH]
   [twitter-api.handlers.notfound :as notfound])
  (:gen-class))

(defroutes app-routes
  (context "/tweets" []
    (GET "/count" [] tweetH/count-all-tweets-handler)
    (POST "/" [] (mj/wrap-json-body tweetH/post-twitter-handler {:keywords? true :bigdecimals? true}))
    (GET "/" [] tweetH/all-tweets-handler)

    (context "/:user" [user]
      (GET "/posts" [] (tweetH/get-twitter-handler user))))
  (GET "/*" [] notfound/generic-not-found))

(defn -main
  "I don't do a whole lot ... yet."
  [& _args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults)  {:port port})
    (println (str "Server started at port " port))))
