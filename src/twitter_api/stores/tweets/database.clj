(ns twitter-api.stores.tweets.database
  (:require
   [twitter-api.db.db :refer [db]]
   [twitter-api.db.tweetsdb :as tweetsdb]
   [twitter-api.stores.tweets.validation :as v])
  (:import
   java.util.UUID)
  (:gen-class))

(defn post-tweet
  "Post a tweet to the audience"
  [tweet]

  (let [is-valid (v/validate-tweet tweet)]
    (when is-valid
      (tweetsdb/sql-insert-tweet db (assoc tweet :id (java.util.UUID/randomUUID))))))

(defn search-tweets-by-username
  "Find tweets from a specific username"
  [username]

  (let [result (tweetsdb/sql-search-tweets-by-username db {:username (str "@" username)})]
    (map #(assoc % :id (str (:id %))) result)))

(defn get-all-tweets
  "Return a list of all tweets"
  []

  (let [result (tweetsdb/sql-get-all-tweets db)]
    (map #(assoc % :id (str (:id %))) result)))

(defn count-tweets
  "Return the total number of tweets"
  []

  (let [result (tweetsdb/sql-count-tweets db)]
    (map #(assoc % :id (str (:id %))) result))
  )
