
INSERT INTO role (name) VALUES ('ROLE_USER'),('ROLE_DRIVER'),('ROLE_ADMIN');

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('pera@gmail.com','sifra','Pera','Peric','grad','42145135',true,'slika',false,2);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('zima@gmail.com','sifra','Sima','Simic','grad','42145135',true,'slika',false,2);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('djura@gmail.com','sifra','Djura','Djuric','grad','42145135',true,'slika',false,2);

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani1@gmail.com','sifra','Put','Putic','grad','42145135',true,'slika',false,1);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani2@gmail.com','sifra','Isidora','Mladjic','grad','42145135',true,'slika',false,1);

insert into registered_user (id, is_busy) values (4, false);
insert into registered_user (id, is_busy) values (5, false);

insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id) values (true, true, 'SUV', true, true, 1);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id) values (true, true, 'SUV', true, true, 2);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id) values (true, true, 'SUV', true, true, 3);

insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Djurica', 'Djuric', 'djura@gmail.com', 'Samac','42145135', 'slika', 'SUV', true, false);
insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Simija', 'Radesic', 'zima@gmail.com', 'Samac','42145135', 'slika', 'SUV', false, false);

-- insert into route (finish_location, start_location, registered_user_email) values (5.1, 6.2, 4);
--
-- insert into ride (end_date_time, distance, expected_duration,
-- price, ride_state, start_date_time, caller_email, driver_email, route_id) values ('2023-01-01 18:47:52.069', 54.3,
-- '2012-09-17 18:47:52.069', 56.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 4, 1, 1);
--
-- insert into ride (end_date_time, distance, expected_duration,
-- price, ride_state, start_date_time, caller_email, driver_email, route_id) values ('2023-01-04 18:47:52.069', 54.3,
-- '2012-09-17 18:47:52.069', 56.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 4, 1, 1);
--
-- insert into ride (end_date_time, distance, expected_duration,
-- price, ride_state, start_date_time, caller_email, driver_email, route_id) values ('2023-01-04 18:47:52.069', 54.3,
-- '2012-09-17 18:47:52.069', 56.3, 'SCHEDULED', '2012-09-17 18:47:52.069', 4, 1, 1);


INSERT INTO COORD (latitude, longitude) values (4.5, 5.5);
INSERT INTO REVIEW (comment) values ('Kojmentar');
INSERT INTO MESSAGE (text, sender, receiver) values ('Hey','pera@gmail.com', 'zima@gmail.com' );
INSERT INTO MESSAGE (text, sender, receiver) values ('How are you??', 'zima@gmail.com',  'pera@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Djurina poruka', 'djura@gmail.com',  'zima@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Nije zimina', 'djura@gmail.com',  'pera@gmail.com');
