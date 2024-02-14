CREATE TABLE player AS SELECT * FROM CSVREAD('classpath:sources/player.csv');
-- INSERT INTO player (SELECT * FROM CSVREAD('classpath:sources/player.csv'));