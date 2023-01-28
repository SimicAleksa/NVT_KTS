
INSERT INTO role (name) VALUES ('ROLE_USER'),('ROLE_DRIVER'),('ROLE_ADMIN');

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id, note) values ('pera@gmail.com','sifra','Pera','Peric','grad','42145135',true,'slika',false,2, 'vozio pijan');
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('zima@gmail.com','sifra','Sima','Simic','grad','42145135',true,'slika',true,2);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('djura@gmail.com','sifra','Djura','Djuric','grad','42145135',true,'slika',false,2);

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani1@gmail.com','sifra','Put','Putic','grad','42145135',true,'slika',false,1);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('registrovani2@gmail.com','sifra','Isidora','Mladjic','grad','42145135',true,'slika',false,1);

insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id) values ('so@gmail.com','sssifra','So','Soic','grad','42145135',true,'slika',false,2);

insert into registered_user (id, is_busy, tokens) values (4, false, 345);
insert into registered_user (id, is_busy, tokens) values (5, false, 12);


INSERT INTO COORD (latitude, longitude) values (45.244533, 19.817549);
INSERT INTO COORD (latitude, longitude) values (45.241604, 19.813741);
INSERT INTO COORD (latitude, longitude) values (45.236420, 19.820529);


--
INSERT INTO COORD (latitude, longitude) values (45.223481, 19.847990);
INSERT INTO COORD (latitude, longitude) values (45.242219, 19.826641);
INSERT INTO COORD (latitude, longitude) values (45.247309, 19.796717);

INSERT INTO COORD (latitude, longitude) values (45.245904, 19.8312905);
INSERT INTO COORD (latitude, longitude) values (45.240808, 19.8448008);

-- 45.245904, 19.8312905 --puskinova 6
-- 45.240808, 19.8448008 --dragise brasovana 10


-- 45.244533, 19.817549 cvecareska
-- 45.241604, 19.813741 bogdana suputa
-- 45.236420, 19.820529 heroja pinkija


insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id, current_coords_id) values (true, true, 'SUV', true, false, 1,3);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id, current_coords_id) values (false, false, 'SEDAN', true, true, 2,6);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id, current_coords_id) values (false, true, 'COUPE', true, true, 3,5);
insert into driver (active, baby_allowed, car_type, is_driver_free, pet_allowed, id, current_coords_id) values (false, true, 'COUPE', true, true, 6,5);

insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Djurica', 'Djuric', 'djura@gmail.com', 'Samac','42145135', 'slika', 'SUV', true, false);
insert into change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed) values ('Simija', 'Radesic', 'zima@gmail.com', 'Samac','42145135', 'slika', 'SUV', false, false);

insert into route (start_location_id, end_location_id, routejson) values (2, 1, JSON '{"code":"Ok","routes":[{"legs":[{"steps":[{"geometry":{"coordinates":[[19.813661,45.241581],[19.813364,45.24209]],"type":"LineString"},"maneuver":{"bearing_after":338,"bearing_before":0,"location":[19.813661,45.241581],"modifier":"right","type":"depart"},"mode":"driving","driving_side":"right","name":"Богдана Шупута","intersections":[{"out":0,"entry":[true],"bearings":[338],"location":[19.813661,45.241581]}],"weight":10.9,"duration":10.9,"distance":61.2},{"geometry":{"coordinates":[[19.813364,45.24209],[19.814338,45.242378],[19.815268,45.242656]],"type":"LineString"},"maneuver":{"bearing_after":66,"bearing_before":337,"location":[19.813364,45.24209],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Ади Ендреа","intersections":[{"out":0,"in":1,"entry":[true,false,true,true],"bearings":[60,165,240,345],"location":[19.813364,45.24209]},{"out":0,"in":1,"entry":[true,false,true],"bearings":[60,240,345],"location":[19.814338,45.242378]}],"weight":28,"duration":28,"distance":162.2},{"geometry":{"coordinates":[[19.815268,45.242656],[19.815231,45.242739],[19.814788,45.243586]],"type":"LineString"},"maneuver":{"bearing_after":341,"bearing_before":66,"location":[19.815268,45.242656],"modifier":"left","type":"end of road"},"mode":"driving","driving_side":"right","name":"Суботичка","intersections":[{"out":2,"in":1,"entry":[true,false,true],"bearings":[165,240,345],"location":[19.815268,45.242656]},{"out":2,"in":1,"entry":[true,false,true],"bearings":[60,165,345],"location":[19.815231,45.242739]}],"weight":17.5,"duration":17.5,"distance":110},{"geometry":{"coordinates":[[19.814788,45.243586],[19.817565,45.244508]],"type":"LineString"},"maneuver":{"bearing_after":64,"bearing_before":338,"location":[19.814788,45.243586],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Цвећарска","intersections":[{"out":0,"in":1,"entry":[true,false,true],"bearings":[60,165,345],"location":[19.814788,45.243586]}],"weight":34.7,"duration":34.7,"distance":241},{"geometry":{"coordinates":[[19.817565,45.244508],[19.817565,45.244508]],"type":"LineString"},"maneuver":{"bearing_after":0,"bearing_before":65,"location":[19.817565,45.244508],"type":"arrive"},"mode":"driving","driving_side":"right","name":"Цвећарска","intersections":[{"in":0,"entry":[true],"bearings":[245],"location":[19.817565,45.244508]}],"weight":0,"duration":0,"distance":0}],"summary":"Ади Ендреа, Цвећарска","weight":91.1,"duration":91.1,"distance":574.4}],"weight_name":"routability","weight":91.1,"duration":91.1,"distance":574.4}],"waypoints":[{"hint":"PRvmhW-vi4haAAAAWQAAAAAAAAAAAAAA3lh7QkTBdEIAAAAAAAAAAFoAAABZAAAAAAAAAAAAAADn9AAAHVUuAe1UsgJtVS4BBFWyAgAAHxXOuOqC","distance":6.783069622,"name":"Богдана Шупута","location":[19.813661,45.241581]},{"hint":"n7dFgLi4RYCNAAAAWwEAAAAAAAAAAAAAJlTEQnL4cEMAAAAAAAAAAI0AAABbAQAAAAAAAAAAAADn9AAAXWQuAVxgsgJNZC4BdWCyAgAATw_OuOqC","distance":3.04935726,"name":"Цвећарска","location":[19.817565,45.244508]}]}');

insert into route (start_location_id, end_location_id, routejson) values (4, 5, JSON '{"code":"Ok","routes":[{"legs":[{"steps":[{"geometry":{"coordinates":[[45.208008,19.87561],[45.224736,19.883899],[45.231566,19.887324]],"type":"LineString"},"maneuver":{"bearing_after":62,"bearing_before":0,"location":[45.208008,19.87561],"type":"depart"},"mode":"driving","driving_side":"right","name":"","intersections":[{"out":0,"entry":[true],"bearings":[62],"location":[45.208008,19.87561]}],"weight":182.4,"duration":182.4,"distance":2787.3},{"geometry":{"coordinates":[[45.231566,19.887324],[45.231566,19.887324]],"type":"LineString"},"maneuver":{"bearing_after":0,"bearing_before":62,"location":[45.231566,19.887324],"type":"arrive"},"mode":"driving","driving_side":"right","name":"","intersections":[{"in":0,"entry":[true],"bearings":[242],"location":[45.231566,19.887324]}],"weight":0,"duration":0,"distance":0}],"summary":"","weight":182.4,"duration":182.4,"distance":2787.3}],"weight_name":"routability","weight":182.4,"duration":182.4,"distance":2787.3}],"waypoints":[{"hint":"-3magTN9moEpAwAADwUAAF4WAAAZdgAAVJeaRMc190TyrghGWm00RykDAAAPBQAAXhYAABl2AAAVGwAAyNGxAhpHLwE5DrICNtsuAQMA7wMjlaa1","distance":3460.480704854,"name":"","location":[45.208008,19.87561]},{"hint":"-3magTN9moERAgAA1QMAAJYeAAAzcAAAA2VKRBA7u0SV6DpG62krRxECAADVAwAAlh4AADNwAAAVGwAAzi2yAtx0LwFhi7ICz88uAQQA7wMjlaa1","distance":5307.849225642,"name":"","location":[45.231566,19.887324]}]}');

insert into route (start_location_id, end_location_id, routejson)values (1,2,JSON '{"name":"Пушкинова, Булевар Цара Лазара","coordinates":[{"lat":45.24585,"lng":19.83141},{"lat":45.24553,"lng":19.83209},{"lat":45.24552,"lng":19.83212},{"lat":45.24546,"lng":19.83223},{"lat":45.24542,"lng":19.83231},{"lat":45.24539,"lng":19.83238},{"lat":45.24488,"lng":19.83347},{"lat":45.24486,"lng":19.8335},{"lat":45.24481,"lng":19.8336},{"lat":45.24477,"lng":19.83369},{"lat":45.24474,"lng":19.83374},{"lat":45.24401,"lng":19.83525},{"lat":45.244,"lng":19.83529},{"lat":45.24395,"lng":19.83538},{"lat":45.24393,"lng":19.83542},{"lat":45.24388,"lng":19.83551},{"lat":45.24374,"lng":19.83581},{"lat":45.24372,"lng":19.83584},{"lat":45.24368,"lng":19.83592},{"lat":45.24366,"lng":19.83596},{"lat":45.24363,"lng":19.83599},{"lat":45.24361,"lng":19.83602},{"lat":45.24358,"lng":19.83604},{"lat":45.24353,"lng":19.83607},{"lat":45.24347,"lng":19.8361},{"lat":45.24343,"lng":19.83613},{"lat":45.24334,"lng":19.83618},{"lat":45.24307,"lng":19.83634},{"lat":45.24286,"lng":19.83646},{"lat":45.24274,"lng":19.83653},{"lat":45.24267,"lng":19.83657},{"lat":45.24267,"lng":19.83657},{"lat":45.24256,"lng":19.83662},{"lat":45.24262,"lng":19.83684},{"lat":45.24264,"lng":19.83692},{"lat":45.24302,"lng":19.83842},{"lat":45.2432,"lng":19.83914},{"lat":45.24332,"lng":19.8396},{"lat":45.24372,"lng":19.84121},{"lat":45.24373,"lng":19.84126},{"lat":45.24374,"lng":19.84129},{"lat":45.24379,"lng":19.84146},{"lat":45.24379,"lng":19.84146},{"lat":45.24369,"lng":19.84152},{"lat":45.24367,"lng":19.84153},{"lat":45.24339,"lng":19.84168},{"lat":45.24322,"lng":19.84178},{"lat":45.24217,"lng":19.84236},{"lat":45.2418,"lng":19.84257},{"lat":45.24175,"lng":19.84259},{"lat":45.24171,"lng":19.84261},{"lat":45.24165,"lng":19.84265},{"lat":45.24165,"lng":19.84265},{"lat":45.24156,"lng":19.8427},{"lat":45.2416,"lng":19.84284},{"lat":45.24165,"lng":19.84302},{"lat":45.24167,"lng":19.84312},{"lat":45.24185,"lng":19.84378},{"lat":45.24194,"lng":19.84402},{"lat":45.24196,"lng":19.84413},{"lat":45.2421,"lng":19.84462},{"lat":45.2421,"lng":19.84462},{"lat":45.24198,"lng":19.84469},{"lat":45.24196,"lng":19.8447},{"lat":45.24156,"lng":19.84492},{"lat":45.24152,"lng":19.84494},{"lat":45.24144,"lng":19.84499},{"lat":45.24129,"lng":19.84507},{"lat":45.24121,"lng":19.84511},{"lat":45.24113,"lng":19.84516},{"lat":45.24087,"lng":19.84531},{"lat":45.24085,"lng":19.84532},{"lat":45.24083,"lng":19.84533},{"lat":45.24083,"lng":19.84533}],"instructions":[{"type":"Head","distance":553.4,"time":81.2,"road":"Пушкинова","direction":"SE","index":0,"mode":"driving","modifier":"Right","text":"Head southeast on Пушкинова"},{"type":"Left","distance":415.9,"time":47.3,"road":"Булевар Цара Лазара","direction":"S","index":31,"mode":"driving","modifier":"Left","text":"Turn left onto Булевар Цара Лазара"},{"type":"Right","distance":255.1,"time":25,"road":"Булевар ослобођења","direction":"SE","index":42,"mode":"driving","modifier":"Right","text":"Turn right onto Булевар ослобођења"},{"type":"Left","distance":173.6,"time":23.2,"road":"Народног фронта","direction":"S","index":52,"mode":"driving","modifier":"Left","text":"Turn left onto Народног фронта"},{"type":"Right","distance":152,"time":21.7,"road":"Драгише Брашована","direction":"SE","index":61,"mode":"driving","modifier":"Right","text":"Turn right onto Драгише Брашована"},{"type":"DestinationReached","distance":0,"time":0,"road":"Драгише Брашована","direction":"N","index":73,"mode":"driving","modifier":"Right","text":"You have arrived at your destination, on the right"}],"summary":{"totalDistance":1550,"totalTime":198.4},"waypointIndices":[0,73],"inputWaypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.2457403,"lng":19.8313},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.2407397,"lng":19.8450102},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"waypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.245853,"lng":19.831407},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.240828,"lng":19.845328},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"properties":{"isSimplified":true},"routesIndex":0}');


insert into ride (distance, expected_duration,
                  price, ride_state, start_date_time, driver_id, route_id) values (54.3,
                                                                         100, 16.3, 'RESERVED', '2012-09-17 18:47:52.069', 2,2);

insert into ride (distance, expected_duration,
                  price, ride_state, driver_id, route_id) values (54.3, 100, 16.3, 'STARTED', 2,2);

insert into ride (start_date_time, distance, expected_duration, price, ride_state, route_id, driver_id, approved_by)
                values ('2023-01-26 21:47:52.069', 54.3, 100, 16.3, 'IN_PROGRESS', 2, 2, '');

--insert into ride (distance, expected_duration, start_date_time,
--                  price, ride_state, route_id, approved_by) values (54.3, 100, '2023-01-26 21:47:52.069', 16.3, 'WAITING_FOR_PAYMENT', 2, '');

--insert into ride_passengers(ride_id, registered_user_id) values (1, 4);
--insert into ride_passengers(ride_id, registered_user_id) values (1, 5);
--
--insert into ride_passengers(ride_id, registered_user_id) values (2, 4);
--
insert into data_for_ride_from_from (ride_id, baby_allowed, car_types, pet_allowed, START_LATITUDE, START_LONGITUDE )
    values (1, false, 'SUV;SEDAN;COUPE', false, 45.2, 19.8);
insert into data_for_ride_from_from (ride_id, baby_allowed, car_types, pet_allowed, START_LATITUDE, START_LONGITUDE )
    values (2, false, 'NECA', false, 45.3, 19.9);
insert into data_for_ride_from_from (ride_id, baby_allowed, car_types, pet_allowed, START_LATITUDE, START_LONGITUDE )
    values (3, false, 'SUV;SEDAN;COUPE', false, 45.2, 19.8);




 insert into ride_passengers(ride_id, registered_user_id) values (1, 4);
 insert into ride_passengers(ride_id, registered_user_id) values (2, 4);
 insert into ride_passengers(ride_id, registered_user_id) values (3, 4);
-- insert into ride_passengers(ride_id, registered_user_id) values (2, 4);
-- insert into ride_passengers(ride_id, registered_user_id) values (2, 4);
-- insert into ride_passengers(ride_id, registered_user_id) values (1, 4);
-- insert into ride_passengers(ride_id, registered_user_id) values (1, 4);








INSERT INTO REVIEW (comment) values ('Kojmentar');
INSERT INTO MESSAGE (text, sender, receiver) values ('Hey, Zima, how are you?','pera@gmail.com', 'zima@gmail.com' );
INSERT INTO MESSAGE (text, sender, receiver) values ('Happy Monday Laura! I have good news, the shoes you inquired about are back in stock in your size', 'zima@gmail.com',  'pera@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Ovo je Djurina poruka', 'djura@gmail.com',  'zima@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Ovo je poruka koju Djura pise zimi', 'djura@gmail.com',  'pera@gmail.com');


insert into TIME_SPAN (start_time, end_time) values ('2023-01-23 10:47:52.069', '2023-01-23 11:47:52.069'); --1
insert into TIME_SPAN (start_time, end_time) values ('2023-01-23 12:47:52.069', '2023-01-23 13:47:52.069'); --1
insert into TIME_SPAN (start_time, end_time) values ('2023-01-23 15:47:52.069', '2023-01-23 17:47:52.069'); --1
insert into TIME_SPAN (start_time, end_time) values ('2023-01-23 18:47:52.069', '2023-01-23 18:50:52.069'); --0
insert into TIME_SPAN (start_time, end_time) values ('2023-01-23 19:47:52.069', '2023-01-23 21:47:52.069'); --2
insert into TIME_SPAN (start_time, end_time) values ('2023-01-23 23:47:52.069', '2023-01-24 01:47:52.069'); --2

insert into DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) values (3, 1);
insert into DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) values (3, 2);
insert into DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) values (3, 3);
insert into DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) values (3, 4);
insert into DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) values (3, 5);
insert into DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) values (3, 6);