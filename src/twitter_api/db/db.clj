(ns twitter-api.db.db
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.next-jdbc :as next-adapter])
  (:gen-class))

(def db
  {:subname "//db:5432/postgres"
   :host "localhost"
   :port "5432"
   :dbname "postgres"
   :subprotocol "postgres"
   :dbtype "postgres"
   :user "postgres"
   :password "twitter"})

(hugsql/def-db-fns
  "twitter_api/db/sql/queries.sql")

(hugsql/def-sqlvec-fns
  "twitter_api/db/sql/queries.sql")
