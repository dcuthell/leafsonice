SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS players (
  id int PRIMARY KEY auto_increment,
  firstName VARCHAR,
  lastName VARCHAR,
  playerNumber INT,
  handed VARCHAR,
  position VARCHAR
);

CREATE TABLE IF NOT EXISTS games (
  id int PRIMARY KEY auto_increment,
  gameDate VARCHAR,
  opposingTeam VARCHAR,
  location VARCHAR,
  played BOOLEAN,
  leafsScore INT,
  opponentScore INT
);

CREATE TABLE IF NOT EXISTS games_players (
  id int PRIMARY KEY auto_increment,
  gameId INT,
  playerId INT
);
