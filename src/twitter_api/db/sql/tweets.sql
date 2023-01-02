-- A :result value of :n below will return affected rows:
-- :name sql-insert-tweet :! :m
-- :doc Persist a tweet on datbase
insert into tweets (id, body, username, created_at)
values ((:id)::uuid , :body, :username, NOW())

-- A :result value of :n below will return affected rows:
-- :name sql-search-tweets-by-username :?
-- :doc Find tweets from a specific username
select id, body from tweets
where username = :username

-- A :result value of :n below will return affected rows:
-- :name sql-get-all-tweets :?
-- :doc Find all tweets.
select id, body, username from tweets

-- :name sql-count-tweets :?
-- :doc Return the number of Tweets, including duplicates.
SELECT COUNT(DISTINCT "id") from tweets
