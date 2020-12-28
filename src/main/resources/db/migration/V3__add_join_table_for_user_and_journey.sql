CREATE TABLE user_journeys (
  place bigint NOT NULL,
  journey bigint NOT NULL,
  user bigint NOT NULL,
  PRIMARY KEY (place,journey,user)
  );

alter table user_journeys add foreign key(user) references users(id);
alter table user_journeys add foreign key(journey) references journeys(id);