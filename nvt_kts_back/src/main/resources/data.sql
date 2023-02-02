
INSERT INTO role (name)
VALUES ('ROLE_USER'),
       ('ROLE_DRIVER'),
       ('ROLE_ADMIN');

INSERT INTO COORD (latitude, longitude)
VALUES (45.244533, 19.817549), -- 45.244533, 19.817549 cvecareska
       (45.241604, 19.813741), -- 45.241604, 19.813741 bogdana suputa
       (45.236420, 19.820529), -- 45.236420, 19.820529 heroja pinkija
       (45.223481, 19.847990),
       (45.242219, 19.826641),
       (45.247309, 19.796717),
       (45.245904, 19.8312905), -- 45.245904, 19.8312905 --puskinova 6
       (45.240808, 19.8448008); -- 45.240808, 19.8448008 --dragise brasovana 10

INSERT INTO my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id, note)
VALUES ('strahinjapopovic.evilpops@gmail.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'ime', 'prezime', 'grad', '42145135', true, 'slika', false, 1, null),
       ('djura@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Djura','Djuric','grad','42145135',true,'slika',false, 1, null),
       ('goran@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Goran','Goric','grad','42145135',true,'slika',false, 1, null),
       ('zoran@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Xoran','Xoric','grad','42145135',false,'slika',true, 1, null),
       ('anastasija@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Anastasija','Lolic','grad','42145135',true,'slika',false, 1, null),
       ('registrovani1@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Put','Putic','grad','42145135',true,'slika',false,2, null),
       ('registrovani2@gmail.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Isidora', 'Mladjic', 'grad', '42145135', true, 'slika', false, 2, null),
       ('pera@gmail.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'John', 'Doe', 'New York', '123-456-7890', true, 'picture.jpg', false, 2, null),
       ('nomoneysadge@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Money','Less','grad','42145135',true,'slika',false, 1, null),
       ('nijeplatiozadnji@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Nije','Zadnji','grad','42145135',true,'slika',false, 1, null),
       ('jesteplatiozadnji@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Jeste','Zadnji','grad','42145135',true,'slika',false, 1, null),
       ('dobradrivertest@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Jeste','Zadnji','grad','42145135',true,'slika',false, 1, null),
       ('batakojiceka@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Bata','Ceka','grad','42145135',true,'slika',false, 1, null),
       ('aaaaaaaaaaaaaaa@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Aaaa','Aaaa','grad','42145135',true,'slika',false, 1, null),
       ('bbbbbbbbbbbbbbbbbb@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Bbbbbbbbbbbb','Bbbbb','grad','42145135',true,'slika',false, 1, null),
       ('zaRezervisanje@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Rezervisan','Simic','grad','42145135',true,'slika',false, 1, null),
       ('seleBrateMojhihixD@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Rezervisan','Simic','grad','42145135',true,'slika',false, 1, null),
       ('nomoneysadgeSelen@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Money','LessSell','grad','42145135',true,'slika',false, 1, null);

INSERT INTO registered_user (id, is_busy, tokens)
VALUES
(1, true, 5000),
(2, true, 5000),
(3, true, 5000),
(4, true, 5000),
(5, true, 5000),
(9, true, 10),
(10, true, 5000),
(11, true, 5000),
(12, true, 5000),
(13, true, 5000),
(14, true, 5000),
(15, true, 5000),
(16, true, 5000),
(17, true, 5000),
(18, true, 13);

INSERT INTO driver (id, active, car_type, baby_allowed, pet_allowed, is_driver_free, current_coords_id, license_plate_number)
VALUES (6, true, 'SEDAN', true, true, true, 3, 'NS 8734 SN'),
       (7, true, 'SUV', false, true, false, 4 , 'NS 834 FN'),
       (8, true, 'SUV', false, true, false, 5 , 'NS 834 FN');


INSERT INTO route (start_location_id, end_location_id, routejson)
VALUES (2, 1, JSON '{"code":"Ok","routes":[{"legs":[{"steps":[{"geometry":{"coordinates":[[19.813661,45.241581],[19.813364,45.24209]],"type":"LineString"},"maneuver":{"bearing_after":338,"bearing_before":0,"location":[19.813661,45.241581],"modifier":"right","type":"depart"},"mode":"driving","driving_side":"right","name":"Богдана Шупута","intersections":[{"out":0,"entry":[true],"bearings":[338],"location":[19.813661,45.241581]}],"weight":10.9,"duration":10.9,"distance":61.2},{"geometry":{"coordinates":[[19.813364,45.24209],[19.814338,45.242378],[19.815268,45.242656]],"type":"LineString"},"maneuver":{"bearing_after":66,"bearing_before":337,"location":[19.813364,45.24209],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Ади Ендреа","intersections":[{"out":0,"in":1,"entry":[true,false,true,true],"bearings":[60,165,240,345],"location":[19.813364,45.24209]},{"out":0,"in":1,"entry":[true,false,true],"bearings":[60,240,345],"location":[19.814338,45.242378]}],"weight":28,"duration":28,"distance":162.2},{"geometry":{"coordinates":[[19.815268,45.242656],[19.815231,45.242739],[19.814788,45.243586]],"type":"LineString"},"maneuver":{"bearing_after":341,"bearing_before":66,"location":[19.815268,45.242656],"modifier":"left","type":"end of road"},"mode":"driving","driving_side":"right","name":"Суботичка","intersections":[{"out":2,"in":1,"entry":[true,false,true],"bearings":[165,240,345],"location":[19.815268,45.242656]},{"out":2,"in":1,"entry":[true,false,true],"bearings":[60,165,345],"location":[19.815231,45.242739]}],"weight":17.5,"duration":17.5,"distance":110},{"geometry":{"coordinates":[[19.814788,45.243586],[19.817565,45.244508]],"type":"LineString"},"maneuver":{"bearing_after":64,"bearing_before":338,"location":[19.814788,45.243586],"modifier":"right","type":"turn"},"mode":"driving","driving_side":"right","name":"Цвећарска","intersections":[{"out":0,"in":1,"entry":[true,false,true],"bearings":[60,165,345],"location":[19.814788,45.243586]}],"weight":34.7,"duration":34.7,"distance":241},{"geometry":{"coordinates":[[19.817565,45.244508],[19.817565,45.244508]],"type":"LineString"},"maneuver":{"bearing_after":0,"bearing_before":65,"location":[19.817565,45.244508],"type":"arrive"},"mode":"driving","driving_side":"right","name":"Цвећарска","intersections":[{"in":0,"entry":[true],"bearings":[245],"location":[19.817565,45.244508]}],"weight":0,"duration":0,"distance":0}],"summary":"Ади Ендреа, Цвећарска","weight":91.1,"duration":91.1,"distance":574.4}],"weight_name":"routability","weight":91.1,"duration":91.1,"distance":574.4}],"waypoints":[{"hint":"PRvmhW-vi4haAAAAWQAAAAAAAAAAAAAA3lh7QkTBdEIAAAAAAAAAAFoAAABZAAAAAAAAAAAAAADn9AAAHVUuAe1UsgJtVS4BBFWyAgAAHxXOuOqC","distance":6.783069622,"name":"Богдана Шупута","location":[19.813661,45.241581]},{"hint":"n7dFgLi4RYCNAAAAWwEAAAAAAAAAAAAAJlTEQnL4cEMAAAAAAAAAAI0AAABbAQAAAAAAAAAAAADn9AAAXWQuAVxgsgJNZC4BdWCyAgAATw_OuOqC","distance":3.04935726,"name":"Цвећарска","location":[19.817565,45.244508]}]}'),
       (4, 5, JSON '{"code":"Ok","routes":[{"legs":[{"steps":[{"geometry":{"coordinates":[[45.208008,19.87561],[45.224736,19.883899],[45.231566,19.887324]],"type":"LineString"},"maneuver":{"bearing_after":62,"bearing_before":0,"location":[45.208008,19.87561],"type":"depart"},"mode":"driving","driving_side":"right","name":"","intersections":[{"out":0,"entry":[true],"bearings":[62],"location":[45.208008,19.87561]}],"weight":182.4,"duration":182.4,"distance":2787.3},{"geometry":{"coordinates":[[45.231566,19.887324],[45.231566,19.887324]],"type":"LineString"},"maneuver":{"bearing_after":0,"bearing_before":62,"location":[45.231566,19.887324],"type":"arrive"},"mode":"driving","driving_side":"right","name":"","intersections":[{"in":0,"entry":[true],"bearings":[242],"location":[45.231566,19.887324]}],"weight":0,"duration":0,"distance":0}],"summary":"","weight":182.4,"duration":182.4,"distance":2787.3}],"weight_name":"routability","weight":182.4,"duration":182.4,"distance":2787.3}],"waypoints":[{"hint":"-3magTN9moEpAwAADwUAAF4WAAAZdgAAVJeaRMc190TyrghGWm00RykDAAAPBQAAXhYAABl2AAAVGwAAyNGxAhpHLwE5DrICNtsuAQMA7wMjlaa1","distance":3460.480704854,"name":"","location":[45.208008,19.87561]},{"hint":"-3magTN9moERAgAA1QMAAJYeAAAzcAAAA2VKRBA7u0SV6DpG62krRxECAADVAwAAlh4AADNwAAAVGwAAzi2yAtx0LwFhi7ICz88uAQQA7wMjlaa1","distance":5307.849225642,"name":"","location":[45.231566,19.887324]}]}');

INSERT INTO route (start_location_id, end_location_id, routejson)VALUES (7,8,JSON '{"name":"Пушкинова, Булевар Цара Лазара","coordinates":[{"lat":45.24585,"lng":19.83141},{"lat":45.24553,"lng":19.83209},{"lat":45.24552,"lng":19.83212},{"lat":45.24546,"lng":19.83223},{"lat":45.24542,"lng":19.83231},{"lat":45.24539,"lng":19.83238},{"lat":45.24488,"lng":19.83347},{"lat":45.24486,"lng":19.8335},{"lat":45.24481,"lng":19.8336},{"lat":45.24477,"lng":19.83369},{"lat":45.24474,"lng":19.83374},{"lat":45.24401,"lng":19.83525},{"lat":45.244,"lng":19.83529},{"lat":45.24395,"lng":19.83538},{"lat":45.24393,"lng":19.83542},{"lat":45.24388,"lng":19.83551},{"lat":45.24374,"lng":19.83581},{"lat":45.24372,"lng":19.83584},{"lat":45.24368,"lng":19.83592},{"lat":45.24366,"lng":19.83596},{"lat":45.24363,"lng":19.83599},{"lat":45.24361,"lng":19.83602},{"lat":45.24358,"lng":19.83604},{"lat":45.24353,"lng":19.83607},{"lat":45.24347,"lng":19.8361},{"lat":45.24343,"lng":19.83613},{"lat":45.24334,"lng":19.83618},{"lat":45.24307,"lng":19.83634},{"lat":45.24286,"lng":19.83646},{"lat":45.24274,"lng":19.83653},{"lat":45.24267,"lng":19.83657},{"lat":45.24267,"lng":19.83657},{"lat":45.24256,"lng":19.83662},{"lat":45.24262,"lng":19.83684},{"lat":45.24264,"lng":19.83692},{"lat":45.24302,"lng":19.83842},{"lat":45.2432,"lng":19.83914},{"lat":45.24332,"lng":19.8396},{"lat":45.24372,"lng":19.84121},{"lat":45.24373,"lng":19.84126},{"lat":45.24374,"lng":19.84129},{"lat":45.24379,"lng":19.84146},{"lat":45.24379,"lng":19.84146},{"lat":45.24369,"lng":19.84152},{"lat":45.24367,"lng":19.84153},{"lat":45.24339,"lng":19.84168},{"lat":45.24322,"lng":19.84178},{"lat":45.24217,"lng":19.84236},{"lat":45.2418,"lng":19.84257},{"lat":45.24175,"lng":19.84259},{"lat":45.24171,"lng":19.84261},{"lat":45.24165,"lng":19.84265},{"lat":45.24165,"lng":19.84265},{"lat":45.24156,"lng":19.8427},{"lat":45.2416,"lng":19.84284},{"lat":45.24165,"lng":19.84302},{"lat":45.24167,"lng":19.84312},{"lat":45.24185,"lng":19.84378},{"lat":45.24194,"lng":19.84402},{"lat":45.24196,"lng":19.84413},{"lat":45.2421,"lng":19.84462},{"lat":45.2421,"lng":19.84462},{"lat":45.24198,"lng":19.84469},{"lat":45.24196,"lng":19.8447},{"lat":45.24156,"lng":19.84492},{"lat":45.24152,"lng":19.84494},{"lat":45.24144,"lng":19.84499},{"lat":45.24129,"lng":19.84507},{"lat":45.24121,"lng":19.84511},{"lat":45.24113,"lng":19.84516},{"lat":45.24087,"lng":19.84531},{"lat":45.24085,"lng":19.84532},{"lat":45.24083,"lng":19.84533},{"lat":45.24083,"lng":19.84533}],"instructions":[{"type":"Head","distance":553.4,"time":81.2,"road":"Пушкинова","direction":"SE","index":0,"mode":"driving","modifier":"Right","text":"Head southeast on Пушкинова"},{"type":"Left","distance":415.9,"time":47.3,"road":"Булевар Цара Лазара","direction":"S","index":31,"mode":"driving","modifier":"Left","text":"Turn left onto Булевар Цара Лазара"},{"type":"Right","distance":255.1,"time":25,"road":"Булевар ослобођења","direction":"SE","index":42,"mode":"driving","modifier":"Right","text":"Turn right onto Булевар ослобођења"},{"type":"Left","distance":173.6,"time":23.2,"road":"Народног фронта","direction":"S","index":52,"mode":"driving","modifier":"Left","text":"Turn left onto Народног фронта"},{"type":"Right","distance":152,"time":21.7,"road":"Драгише Брашована","direction":"SE","index":61,"mode":"driving","modifier":"Right","text":"Turn right onto Драгише Брашована"},{"type":"DestinationReached","distance":0,"time":0,"road":"Драгише Брашована","direction":"N","index":73,"mode":"driving","modifier":"Right","text":"You have arrived at your destination, on the right"}],"summary":{"totalDistance":1550,"totalTime":198.4},"waypointIndices":[0,73],"inputWaypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.2457403,"lng":19.8313},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.2407397,"lng":19.8450102},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"waypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.245853,"lng":19.831407},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.240828,"lng":19.845328},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"properties":{"isSimplified":true},"routesIndex":0}');
INSERT INTO route (start_location_id, end_location_id, routejson)VALUES (7,8,JSON '{"name":"Пушкинова, Булевар Цара Лазара","coordinates":[{"lat":45.24585,"lng":19.83141},{"lat":45.24553,"lng":19.83209},{"lat":45.24552,"lng":19.83212},{"lat":45.24546,"lng":19.83223},{"lat":45.24542,"lng":19.83231},{"lat":45.24539,"lng":19.83238},{"lat":45.24488,"lng":19.83347},{"lat":45.24486,"lng":19.8335},{"lat":45.24481,"lng":19.8336},{"lat":45.24477,"lng":19.83369},{"lat":45.24474,"lng":19.83374},{"lat":45.24401,"lng":19.83525},{"lat":45.244,"lng":19.83529},{"lat":45.24395,"lng":19.83538},{"lat":45.24393,"lng":19.83542},{"lat":45.24388,"lng":19.83551},{"lat":45.24374,"lng":19.83581},{"lat":45.24372,"lng":19.83584},{"lat":45.24368,"lng":19.83592},{"lat":45.24366,"lng":19.83596},{"lat":45.24363,"lng":19.83599},{"lat":45.24361,"lng":19.83602},{"lat":45.24358,"lng":19.83604},{"lat":45.24353,"lng":19.83607},{"lat":45.24347,"lng":19.8361},{"lat":45.24343,"lng":19.83613},{"lat":45.24334,"lng":19.83618},{"lat":45.24307,"lng":19.83634},{"lat":45.24286,"lng":19.83646},{"lat":45.24274,"lng":19.83653},{"lat":45.24267,"lng":19.83657},{"lat":45.24267,"lng":19.83657},{"lat":45.24256,"lng":19.83662},{"lat":45.24262,"lng":19.83684},{"lat":45.24264,"lng":19.83692},{"lat":45.24302,"lng":19.83842},{"lat":45.2432,"lng":19.83914},{"lat":45.24332,"lng":19.8396},{"lat":45.24372,"lng":19.84121},{"lat":45.24373,"lng":19.84126},{"lat":45.24374,"lng":19.84129},{"lat":45.24379,"lng":19.84146},{"lat":45.24379,"lng":19.84146},{"lat":45.24369,"lng":19.84152},{"lat":45.24367,"lng":19.84153},{"lat":45.24339,"lng":19.84168},{"lat":45.24322,"lng":19.84178},{"lat":45.24217,"lng":19.84236},{"lat":45.2418,"lng":19.84257},{"lat":45.24175,"lng":19.84259},{"lat":45.24171,"lng":19.84261},{"lat":45.24165,"lng":19.84265},{"lat":45.24165,"lng":19.84265},{"lat":45.24156,"lng":19.8427},{"lat":45.2416,"lng":19.84284},{"lat":45.24165,"lng":19.84302},{"lat":45.24167,"lng":19.84312},{"lat":45.24185,"lng":19.84378},{"lat":45.24194,"lng":19.84402},{"lat":45.24196,"lng":19.84413},{"lat":45.2421,"lng":19.84462},{"lat":45.2421,"lng":19.84462},{"lat":45.24198,"lng":19.84469},{"lat":45.24196,"lng":19.8447},{"lat":45.24156,"lng":19.84492},{"lat":45.24152,"lng":19.84494},{"lat":45.24144,"lng":19.84499},{"lat":45.24129,"lng":19.84507},{"lat":45.24121,"lng":19.84511},{"lat":45.24113,"lng":19.84516},{"lat":45.24087,"lng":19.84531},{"lat":45.24085,"lng":19.84532},{"lat":45.24083,"lng":19.84533},{"lat":45.24083,"lng":19.84533}],"instructions":[{"type":"Head","distance":553.4,"time":81.2,"road":"Пушкинова","direction":"SE","index":0,"mode":"driving","modifier":"Right","text":"Head southeast on Пушкинова"},{"type":"Left","distance":415.9,"time":47.3,"road":"Булевар Цара Лазара","direction":"S","index":31,"mode":"driving","modifier":"Left","text":"Turn left onto Булевар Цара Лазара"},{"type":"Right","distance":255.1,"time":25,"road":"Булевар ослобођења","direction":"SE","index":42,"mode":"driving","modifier":"Right","text":"Turn right onto Булевар ослобођења"},{"type":"Left","distance":173.6,"time":23.2,"road":"Народног фронта","direction":"S","index":52,"mode":"driving","modifier":"Left","text":"Turn left onto Народног фронта"},{"type":"Right","distance":152,"time":21.7,"road":"Драгише Брашована","direction":"SE","index":61,"mode":"driving","modifier":"Right","text":"Turn right onto Драгише Брашована"},{"type":"DestinationReached","distance":0,"time":0,"road":"Драгише Брашована","direction":"N","index":73,"mode":"driving","modifier":"Right","text":"You have arrived at your destination, on the right"}],"summary":{"totalDistance":1550,"totalTime":198.4},"waypointIndices":[0,73],"inputWaypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.2457403,"lng":19.8313},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.2407397,"lng":19.8450102},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"waypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.245853,"lng":19.831407},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.240828,"lng":19.845328},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"properties":{"isSimplified":true},"routesIndex":0}');


INSERT INTO ride (ride_state, price, start_date_time, end_date_time, expected_duration, distance, route_id, driver_id)
VALUES ('IN_PROGRESS', 16.3, '2023-09-17 18:47:52.069', '2023-01-01 18:47:52.069', 100, 54.3, 1, 6);

INSERT INTO ride (ride_state, price, start_date_time, end_date_time, expected_duration, distance, route_id, driver_id)
VALUES ('DRIVING_TO_START', 16.3, '2023-09-17 18:47:52.069', '2023-01-01 18:47:52.069', 100, 54.3, 1, 7);

INSERT INTO ride (ride_state, price, start_date_time, end_date_time, expected_duration, distance, route_id, driver_id)
VALUES ('STARTED', 16.3, '2023-09-17 18:47:52.069', '2023-01-01 18:47:52.069', 100, 54.3, 1, 7);

INSERT INTO ride (ride_state, price, expected_duration, distance, route_id)
VALUES ('WAITING_FOR_PAYMENT', 100, 100, 54.3, 1);

INSERT INTO ride (ride_state, price, expected_duration, distance, route_id)
VALUES ('WAITING_FOR_PAYMENT', 100, 100, 54.3, 1);

INSERT INTO ride (ride_state, price, expected_duration, distance, route_id)
VALUES ('WAITING_FOR_PAYMENT', 100, 100, 54.3, 1);

INSERT INTO ride (ride_state, price, expected_duration, distance, route_id)
VALUES ('WAITING_FOR_PAYMENT', 100, 100, 54.3, 1);

INSERT INTO ride (ride_state, price, expected_duration, distance, route_id)
VALUES ('WAITING_FOR_PAYMENT', 100, 100, 54.3, 1);

INSERT INTO ride (ride_state, price, expected_duration, distance, route_id)
VALUES ('WAITING_FOR_PAYMENT', 100, 100, 54.3, 1);

INSERT INTO ride (ride_state, price, expected_duration, distance, route_id, start_date_time)
VALUES ('WAITING_FOR_PAYMENT', 100, 100, 54.3, 1, '2023-02-05 18:47:52.069');


INSERT INTO ride_passengers(ride_id, registered_user_id)
VALUES (1, 2),(4,9),(4,2),(5,10),(5,11),(6,12),(7,13),(8,14),(9,15), (10,16);

INSERT INTO REGISTERED_USER_FAVOURITE_ROUTES (REGISTERED_USER_ID,FAVOURITE_ROUTES_ID)
VALUES (2, 1);

insert into DATA_FOR_RIDE_FROM_FROM (RIDE_ID, BABY_ALLOWED, CAR_TYPES,DURATION, PET_ALLOWED, START_LATITUDE,START_LONGITUDE)
values(4,false,'SUV;HATCHBACK;COUPE;MINIVAN;SEDAN;VAN;LIMOUSINE',100,false,45.19,19.21);

insert into DATA_FOR_RIDE_FROM_FROM (RIDE_ID, BABY_ALLOWED, CAR_TYPES,DURATION, PET_ALLOWED, START_LATITUDE,START_LONGITUDE)
values(5,false,'SUV;HATCHBACK;COUPE;MINIVAN;SEDAN;VAN;LIMOUSINE',100,false,45.19,19.21);

insert into DATA_FOR_RIDE_FROM_FROM (RIDE_ID, BABY_ALLOWED, CAR_TYPES,DURATION, PET_ALLOWED, START_LATITUDE,START_LONGITUDE)
values(6,false,'SUV;HATCHBACK;COUPE;MINIVAN;SEDAN;VAN;LIMOUSINE',100,false,45.19,19.21);

insert into DATA_FOR_RIDE_FROM_FROM (RIDE_ID, BABY_ALLOWED, CAR_TYPES,DURATION, PET_ALLOWED, START_LATITUDE,START_LONGITUDE)
values(7,false,'SUV;HATCHBACK;COUPE;MINIVAN;SEDAN;VAN;LIMOUSINE',100,false,45.19,19.21);

insert into DATA_FOR_RIDE_FROM_FROM (RIDE_ID, BABY_ALLOWED, CAR_TYPES,DURATION, PET_ALLOWED, START_LATITUDE,START_LONGITUDE)
values(8,false,'SUV;HATCHBACK;COUPE;MINIVAN;SEDAN;VAN;LIMOUSINE',100,false,45.19,19.21);

insert into DATA_FOR_RIDE_FROM_FROM (RIDE_ID, BABY_ALLOWED, CAR_TYPES,DURATION, PET_ALLOWED, START_LATITUDE,START_LONGITUDE)
values(9,false,'LIMOUSINE',100,false,45.19,19.21);

insert into DATA_FOR_RIDE_FROM_FROM (RIDE_ID, BABY_ALLOWED, CAR_TYPES,DURATION, PET_ALLOWED, START_LATITUDE,START_LONGITUDE, DATE_TIME)
values(10,false,'SUV;HATCHBACK;COUPE;MINIVAN;SEDAN;VAN;LIMOUSINE',100,false,45.19,19.21, '2023-02-05 18:47:52.069');