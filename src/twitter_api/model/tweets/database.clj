(ns twitter-api.model.tweets.database
  (:require
   [twitter-api.db.db :refer [db]]
   [twitter-api.db.tweetsdb :as tweetsdb]
   [twitter-api.model.tweets.validation :as v])
  (:import
   java.util.UUID)
  (:gen-class))

(defn- render-one
  "Renders only one object"
  [data]

  {:data data})

(defn- render-many
  "Render an Array of data."
  [data]

  {:data data})

(defn- format-tweet
  "Apply a valid format for tweets"
  [tweet]

  ;; Transform the UUID into a String.
  (->> tweet
       (map #(assoc % :id (str (:id %))))))

(defn post-tweet
  "Post a tweet to the audience"
  [tweet]

  (let [is-valid? (v/validate-tweet tweet)]
    (when is-valid?
      (tweetsdb/sql-insert-tweet db (assoc tweet :id (java.util.UUID/randomUUID))))))

(defn search-tweets-by-username
  "Find tweets from a specific username"
  [username]

  (let [result (tweetsdb/sql-search-tweets-by-username db {:username (str "@" username)})]
    (render-many (format-tweet result))))

(defn get-all-tweets
  "Return a list of all tweets"
  []

  (let [result (tweetsdb/sql-get-all-tweets db)]
    (render-many (format-tweet result))))

(defn count-tweets
  "Return the total number of tweets"
  []

  (let [result (tweetsdb/sql-count-tweets db)]
    (render-one  (first result))))
