
INSERT INTO role (name) VALUES ('ROLE_USER'),('ROLE_DRIVER'),('ROLE_ADMIN');

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id, note) values ('pera@gmail.com','sifra','Pera','Peric','grad','42145135',true,'slika',false,2, 'vozio pijan');
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('zima@gmail.com','sifra','Sima','Simic','grad','42145135',true,'slika',true,2);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('djura@gmail.com','sifra','Djura','Djuric','grad','42145135',true,'slika',false,2);

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani1@gmail.com','sifra','Put','Putic','grad','42145135',true,'slika',false,1);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani2@gmail.com','sifra','Isidora','Mladjic','grad','42145135',true,'slika',false,1);

insert into registered_user (id, is_busy, tokens) values (4, false, 345);
insert into registered_user (id, is_busy, tokens) values (5, false, 12);

insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id) values (true, true, 'SUV', true, true, 1);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id) values (true, true, 'SUV', true, true, 2);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id) values (true, true, 'SUV', true, true, 3);

insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Djurica', 'Djuric', 'djura@gmail.com', 'Samac','42145135', 'slika', 'SUV', true, false);
insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Simija', 'Radesic', 'zima@gmail.com', 'Samac','42145135', 'slika', 'SUV', false, false);

-- insert into route (finish_location, start_location, registered_user_email) values (5.1, 6.2, 4);

insert into route (id) values (1);
insert into ride (end_date_time, distance, expected_duration,
 price, ride_state, start_date_time, driver_id) values ('2023-01-01 18:47:52.069', 54.3,
 100, 16.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 1);

insert into ride (end_date_time, distance, expected_duration,
price, ride_state, start_date_time, driver_id) values ('2023-01-14 18:47:52.069', 54.3,
100, 16.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 1);

insert into ride (end_date_time, distance, expected_duration,
price, ride_state, start_date_time, driver_id) values ('2023-01-14 18:47:52.069', 54.3,
100, 16.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 1);

insert into ride_passengers(ride_id, registered_user_id) values (1, 4);
insert into ride_passengers(ride_id, registered_user_id) values (1, 5);
insert into ride_passengers(ride_id, registered_user_id) values (2, 4);

-- insert into ride (end_date_time, distance, expected_duration,
-- price, ride_state, start_date_time, driver_email) values ('2023-01-04 18:47:52.069', 54.3,
-- '2012-09-17 18:47:52.069', 56.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 1);
--
-- insert into ride (end_date_time, distance, expected_duration,
-- price, ride_state, start_date_time, driver_email) values ('2023-01-04 18:47:52.069', 54.3,
-- '2012-09-17 18:47:52.069', 56.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 1);


INSERT INTO COORD (latitude, longitude) values (4.5, 5.5);
INSERT INTO REVIEW (comment) values ('Kojmentar');
INSERT INTO MESSAGE (text, sender, receiver) values ('Hey, Zima, how are you?','pera@gmail.com', 'zima@gmail.com' );
INSERT INTO MESSAGE (text, sender, receiver) values ('Happy Monday Laura! I have good news, the shoes you inquired about are back in stock in your size. I set a pair aside for you to come pick up this week. ', 'zima@gmail.com',  'pera@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Ovo je Djurina poruka', 'djura@gmail.com',  'zima@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Ovo je poruka koju Djura pise zimi', 'djura@gmail.com',  'pera@gmail.com');
