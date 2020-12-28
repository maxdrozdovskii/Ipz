CREATE TABLE `journeys` (
  `id` BIGINT(20) not null auto_increment primary key,
  `from_city` VARCHAR(255) NOT NULL,
  `to_city` VARCHAR(255) NOT NULL,
  `places` BIGINT(20) NOT NULL,
  `date` TIMESTAMP NOT NULL
  );
