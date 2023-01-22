
INSERT INTO role (name) VALUES ('ROLE_USER'),('ROLE_DRIVER'),('ROLE_ADMIN');

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id, note) values ('pera@gmail.com','sifra','Pera','Peric','grad','42145135',true,'slika',false,2, 'vozio pijan');
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('zima@gmail.com','sifra','Sima','Simic','grad','42145135',true,'slika',true,2);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('djura@gmail.com','sifra','Djura','Djuric','grad','42145135',true,'slika',false,2);

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani1@gmail.com','sifra','Put','Putic','grad','42145135',true,'slika',false,1);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani2@gmail.com','sifra','Isidora','Mladjic','grad','42145135',true,'slika',false,1);

insert into registered_user (id, is_busy, tokens) values (4, false, 345);
insert into registered_user (id, is_busy, tokens) values (5, false, 12);


INSERT INTO COORD (latitude, longitude) values (45.244533, 19.817549);
INSERT INTO COORD (latitude, longitude) values (45.241604, 19.813741);
INSERT INTO COORD (latitude, longitude) values (45.236420, 19.820529);


--
INSERT INTO COORD (latitude, longitude) values (45.223481, 19.847990);
INSERT INTO COORD (latitude, longitude) values (45.255521, 19.845071);
INSERT INTO COORD (latitude, longitude) values (45.247309, 19.796717);


-- 45.244533, 19.817549 cvecareska
-- 45.241604, 19.813741 bogdana suputa
-- 45.236420, 19.820529 heroja pinkija


insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id, current_coords_id) values (true, true, 'SUV', true, true, 1,3);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id, current_coords_id) values (true, true, 'SUV', true, true, 2,6);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id, current_coords_id) values (true, true, 'SUV', true, true, 3,5);

insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Djurica', 'Djuric', 'djura@gmail.com', 'Samac','42145135', 'slika', 'SUV', true, false);
insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Simija', 'Radesic', 'zima@gmail.com', 'Samac','42145135', 'slika', 'SUV', false, false);

-- promeni na stvarnu debuiilu glupi !!!!!!!!!!!
insert into route (start_location_id, end_location_id, routejson) values (2, 1, JSON '{"code":"Ok","routes":[{"legs":[{"steps":[{"geometry":{"coordinates":[[19.813661,45.241581],[19.813364,45.24209]],"type":"LineString"},"maneuver":{"bearing_after":338,"bearing_before":0,"location":[19.813661,45.241581],"modifier":"right","type":"depart"},"mode":"driving","driving_side":"right","name":"Богдана Шупута","intersections":[{"out":0,"entry":[true],"bearings":[338],"location":[19.813661,45.241581]}],"weight":10.9,"duration":10.9,"distance":61.2},{"geometry":{"coordinates":[[19.813364,45.24209],[19.814338,45.242378],[19.815268,45.242656]],"type":"LineString"},"maneuver":{"bearing_after":66,"bearing_before":337,"location":[19.813364,45.24209],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Ади Ендреа","intersections":[{"out":0,"in":1,"entry":[true,false,true,true],"bearings":[60,165,240,345],"location":[19.813364,45.24209]},{"out":0,"in":1,"entry":[true,false,true],"bearings":[60,240,345],"location":[19.814338,45.242378]}],"weight":28,"duration":28,"distance":162.2},{"geometry":{"coordinates":[[19.815268,45.242656],[19.815231,45.242739],[19.814788,45.243586]],"type":"LineString"},"maneuver":{"bearing_after":341,"bearing_before":66,"location":[19.815268,45.242656],"modifier":"left","type":"end of road"},"mode":"driving","driving_side":"right","name":"Суботичка","intersections":[{"out":2,"in":1,"entry":[true,false,true],"bearings":[165,240,345],"location":[19.815268,45.242656]},{"out":2,"in":1,"entry":[true,false,true],"bearings":[60,165,345],"location":[19.815231,45.242739]}],"weight":17.5,"duration":17.5,"distance":110},{"geometry":{"coordinates":[[19.814788,45.243586],[19.817565,45.244508]],"type":"LineString"},"maneuver":{"bearing_after":64,"bearing_before":338,"location":[19.814788,45.243586],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Цвећарска","intersections":[{"out":0,"in":1,"entry":[true,false,true],"bearings":[60,165,345],"location":[19.814788,45.243586]}],"weight":34.7,"duration":34.7,"distance":241},{"geometry":{"coordinates":[[19.817565,45.244508],[19.817565,45.244508]],"type":"LineString"},"maneuver":{"bearing_after":0,"bearing_before":65,"location":[19.817565,45.244508],"type":"arrive"},"mode":"driving","driving_side":"right","name":"Цвећарска","intersections":[{"in":0,"entry":[true],"bearings":[245],"location":[19.817565,45.244508]}],"weight":0,"duration":0,"distance":0}],"summary":"Ади Ендреа, Цвећарска","weight":91.1,"duration":91.1,"distance":574.4}],"weight_name":"routability","weight":91.1,"duration":91.1,"distance":574.4}],"waypoints":[{"hint":"PRvmhW-vi4haAAAAWQAAAAAAAAAAAAAA3lh7QkTBdEIAAAAAAAAAAFoAAABZAAAAAAAAAAAAAADn9AAAHVUuAe1UsgJtVS4BBFWyAgAAHxXOuOqC","distance":6.783069622,"name":"Богдана Шупута","location":[19.813661,45.241581]},{"hint":"n7dFgLi4RYCNAAAAWwEAAAAAAAAAAAAAJlTEQnL4cEMAAAAAAAAAAI0AAABbAQAAAAAAAAAAAADn9AAAXWQuAVxgsgJNZC4BdWCyAgAATw_OuOqC","distance":3.04935726,"name":"Цвећарска","location":[19.817565,45.244508]}]}');

insert into route (start_location_id, end_location_id, routejson) values (4, 5, JSON '{"code":"Ok","routes":[{"legs":[{"steps":[{"geometry":{"coordinates":[[45.208008,19.87561],[45.224736,19.883899],[45.231566,19.887324]],"type":"LineString"},"maneuver":{"bearing_after":62,"bearing_before":0,"location":[45.208008,19.87561],"type":"depart"},"mode":"driving","driving_side":"right","name":"","intersections":[{"out":0,"entry":[true],"bearings":[62],"location":[45.208008,19.87561]}],"weight":182.4,"duration":182.4,"distance":2787.3},{"geometry":{"coordinates":[[45.231566,19.887324],[45.231566,19.887324]],"type":"LineString"},"maneuver":{"bearing_after":0,"bearing_before":62,"location":[45.231566,19.887324],"type":"arrive"},"mode":"driving","driving_side":"right","name":"","intersections":[{"in":0,"entry":[true],"bearings":[242],"location":[45.231566,19.887324]}],"weight":0,"duration":0,"distance":0}],"summary":"","weight":182.4,"duration":182.4,"distance":2787.3}],"weight_name":"routability","weight":182.4,"duration":182.4,"distance":2787.3}],"waypoints":[{"hint":"-3magTN9moEpAwAADwUAAF4WAAAZdgAAVJeaRMc190TyrghGWm00RykDAAAPBQAAXhYAABl2AAAVGwAAyNGxAhpHLwE5DrICNtsuAQMA7wMjlaa1","distance":3460.480704854,"name":"","location":[45.208008,19.87561]},{"hint":"-3magTN9moERAgAA1QMAAJYeAAAzcAAAA2VKRBA7u0SV6DpG62krRxECAADVAwAAlh4AADNwAAAVGwAAzi2yAtx0LwFhi7ICz88uAQQA7wMjlaa1","distance":5307.849225642,"name":"","location":[45.231566,19.887324]}]}');

insert into ride (end_date_time, distance, expected_duration,
                  price, ride_state, start_date_time, driver_id, route_id) values ('2023-01-01 18:47:52.069', 54.3,
                                                                         100, 16.3, 'STARTED', '2012-09-17 18:47:52.069', 1,1);

insert into ride (end_date_time, distance, expected_duration,
                  price, ride_state, start_date_time, driver_id, route_id) values ('2023-01-02 18:47:52.069', 54.3,
                                                                                   100, 16.3, 'STARTED', '2012-09-17 18:47:52.069', 3,2);

insert into ride_passengers(ride_id, registered_user_id) values (1, 4);
insert into ride_passengers(ride_id, registered_user_id) values (1, 5);
insert into ride_passengers(ride_id, registered_user_id) values (2, 4);






INSERT INTO REVIEW (comment) values ('Kojmentar');
INSERT INTO MESSAGE (text, sender, receiver) values ('Hey, Zima, how are you?','pera@gmail.com', 'zima@gmail.com' );
INSERT INTO MESSAGE (text, sender, receiver) values ('Happy Monday Laura! I have good news, the shoes you inquired about are back in stock in your size. I set a pair aside for you to come pick up this week. ', 'zima@gmail.com',  'pera@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Ovo je Djurina poruka', 'djura@gmail.com',  'zima@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Ovo je poruka koju Djura pise zimi', 'djura@gmail.com',  'pera@gmail.com');
