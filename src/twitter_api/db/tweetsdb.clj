(ns twitter-api.db.tweetsdb
  (:require [hugsql.core :as hugsql])
  (:gen-class))

(hugsql/def-db-fns
  "twitter_api/db/sql/tweets.sql")

(hugsql/def-sqlvec-fns
  "twitter_api/db/sql/tweets.sql")
