SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS players (
  id int PRIMARY KEY auto_increment,
  firstname VARCHAR,
  lastname VARCHAR,
  playernumber INT,
  position VARCHAR
);

CREATE TABLE IF NOT EXISTS games (
  id int PRIMARY KEY auto_increment,
  gamedate VARCHAR,
  opposingteam VARCHAR,
  location VARCHAR,
  played BOOLEAN,
  leafsscore INT,
  opponentscore INT
);

CREATE TABLE IF NOT EXISTS games_players (
  id int PRIMARY KEY auto_increment,
  gameId VARCHAR,
  playerId VARCHAR
);
