ALTER TABLE journeys
  ADD COLUMN `price` BIGINT NULL
  AFTER places;
ALTER TABLE users
  ADD COLUMN `email` VARCHAR(255) NOT NULL
  AFTER surname;
