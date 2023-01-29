
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
            ('registrovani1@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Put','Putic','grad','42145135',true,'slika',false,1, null),
            ('registrovani2@gmail.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Isidora', 'Mladjic', 'grad', '42145135', true, 'slika', false, 1, null),
            ('john.doe@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'John', 'Doe', 'New York', '123-456-7890', true, 'picture.jpg', false, 1, null),
            ('sarah.johnson@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Sarah', 'Johnson', 'Houston', '123-456-7890', true, 'picture.jpg', false, 1, null),
            ('pera@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Pera','Peric','grad','42145135',true,'slika', true, 2, 'vozio pijan'),
            ('zima@gmail.com','$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O','Sima','Simic','grad','42145135', true, 'slika', true, 2, 'neki razlog'),
            ('michael.brown@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Michael', 'Brown', 'Philadelphia', '123-456-7890', true, 'picture.jpg', false, 2, null),
            ('ashley.taylor@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Ashley', 'Taylor', 'San Diego', '123-456-7890', true, 'picture.jpg', false, 2, null),
            ('matthew.davis@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Matthew', 'Davis', 'Dallas', '123-456-7890', true, 'picture.jpg', false, 2, null),
            ('jane.doe@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Jane', 'Doe', 'Los Angeles', '123-456-7890', true, 'picture.jpg', false, 2, null),
            ('bob.smith@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Bob', 'Smith', 'Chicago', '123-456-7890', true, 'picture.jpg', false, 2, null),
            ('lisa.johnson@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Lisa', 'Johnson', 'Phoenix', '123-456-7890', true, 'picture.jpg', false, 2, null),
            ('admin@gmail.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Robert', 'Williams', 'San Antonio', '123-456-7890', true, 'picture.jpg', false, 3, null);

INSERT INTO registered_user (id, is_busy, tokens)
    VALUES
      (1, true, 24),
      (2, false, 245),
      (3, true, 454),
      (4, false, 32),
      (5, true, 52),
      (6, true, 52);

INSERT INTO driver (id, active, car_type, baby_allowed, pet_allowed, is_driver_free, current_coords_id, license_plate_number)
    VALUES (7, true, 'SEDAN', true, true, true, 3, 'NS 8734 SN'), -- Blocked
            (8, true, 'SUV', false, true, false, 3 , 'NS 834 FN'), -- Blocked
            (9, false, 'HATCHBACK', true, false, true, 3, 'NS 2354 AS'),
            (10, true, 'VAN', false, false, true, 3, 'NS 832 EG'),
            (11, true, 'SEDAN', true, false, true, 3, 'NS 3734 AG'),
            (12, true, 'SUV', true, true, true, 3, 'NS 254 VD'),
            (13, true, 'SUV', true, true, true, 6, 'NS 4634 SG'),
            (14, true, 'SUV', true, true, true, 5, 'NS 434 BI');


INSERT INTO change_profile_requests (name, surname, email, city, phone, picture, car_type, baby_allowed, pets_allowed)
    VALUES ('Djurica', 'Djuric', 'djura@gmail.com', 'Samac','42145135', 'slika', 'SUV', true, false),
            ('Simija', 'Radesic', 'zima@gmail.com', 'Samac','42145135', 'slika', 'SUV', false, false);

insert into route (start_location_id, end_location_id, routejson)values (1,2,JSON '{"name":"Пушкинова, Булевар Цара Лазара","coordinates":[{"lat":45.24585,"lng":19.83141},{"lat":45.24553,"lng":19.83209},{"lat":45.24552,"lng":19.83212},{"lat":45.24546,"lng":19.83223},{"lat":45.24542,"lng":19.83231},{"lat":45.24539,"lng":19.83238},{"lat":45.24488,"lng":19.83347},{"lat":45.24486,"lng":19.8335},{"lat":45.24481,"lng":19.8336},{"lat":45.24477,"lng":19.83369},{"lat":45.24474,"lng":19.83374},{"lat":45.24401,"lng":19.83525},{"lat":45.244,"lng":19.83529},{"lat":45.24395,"lng":19.83538},{"lat":45.24393,"lng":19.83542},{"lat":45.24388,"lng":19.83551},{"lat":45.24374,"lng":19.83581},{"lat":45.24372,"lng":19.83584},{"lat":45.24368,"lng":19.83592},{"lat":45.24366,"lng":19.83596},{"lat":45.24363,"lng":19.83599},{"lat":45.24361,"lng":19.83602},{"lat":45.24358,"lng":19.83604},{"lat":45.24353,"lng":19.83607},{"lat":45.24347,"lng":19.8361},{"lat":45.24343,"lng":19.83613},{"lat":45.24334,"lng":19.83618},{"lat":45.24307,"lng":19.83634},{"lat":45.24286,"lng":19.83646},{"lat":45.24274,"lng":19.83653},{"lat":45.24267,"lng":19.83657},{"lat":45.24267,"lng":19.83657},{"lat":45.24256,"lng":19.83662},{"lat":45.24262,"lng":19.83684},{"lat":45.24264,"lng":19.83692},{"lat":45.24302,"lng":19.83842},{"lat":45.2432,"lng":19.83914},{"lat":45.24332,"lng":19.8396},{"lat":45.24372,"lng":19.84121},{"lat":45.24373,"lng":19.84126},{"lat":45.24374,"lng":19.84129},{"lat":45.24379,"lng":19.84146},{"lat":45.24379,"lng":19.84146},{"lat":45.24369,"lng":19.84152},{"lat":45.24367,"lng":19.84153},{"lat":45.24339,"lng":19.84168},{"lat":45.24322,"lng":19.84178},{"lat":45.24217,"lng":19.84236},{"lat":45.2418,"lng":19.84257},{"lat":45.24175,"lng":19.84259},{"lat":45.24171,"lng":19.84261},{"lat":45.24165,"lng":19.84265},{"lat":45.24165,"lng":19.84265},{"lat":45.24156,"lng":19.8427},{"lat":45.2416,"lng":19.84284},{"lat":45.24165,"lng":19.84302},{"lat":45.24167,"lng":19.84312},{"lat":45.24185,"lng":19.84378},{"lat":45.24194,"lng":19.84402},{"lat":45.24196,"lng":19.84413},{"lat":45.2421,"lng":19.84462},{"lat":45.2421,"lng":19.84462},{"lat":45.24198,"lng":19.84469},{"lat":45.24196,"lng":19.8447},{"lat":45.24156,"lng":19.84492},{"lat":45.24152,"lng":19.84494},{"lat":45.24144,"lng":19.84499},{"lat":45.24129,"lng":19.84507},{"lat":45.24121,"lng":19.84511},{"lat":45.24113,"lng":19.84516},{"lat":45.24087,"lng":19.84531},{"lat":45.24085,"lng":19.84532},{"lat":45.24083,"lng":19.84533},{"lat":45.24083,"lng":19.84533}],"instructions":[{"type":"Head","distance":553.4,"time":81.2,"road":"Пушкинова","direction":"SE","index":0,"mode":"driving","modifier":"Right","text":"Head southeast on Пушкинова"},{"type":"Left","distance":415.9,"time":47.3,"road":"Булевар Цара Лазара","direction":"S","index":31,"mode":"driving","modifier":"Left","text":"Turn left onto Булевар Цара Лазара"},{"type":"Right","distance":255.1,"time":25,"road":"Булевар ослобођења","direction":"SE","index":42,"mode":"driving","modifier":"Right","text":"Turn right onto Булевар ослобођења"},{"type":"Left","distance":173.6,"time":23.2,"road":"Народног фронта","direction":"S","index":52,"mode":"driving","modifier":"Left","text":"Turn left onto Народног фронта"},{"type":"Right","distance":152,"time":21.7,"road":"Драгише Брашована","direction":"SE","index":61,"mode":"driving","modifier":"Right","text":"Turn right onto Драгише Брашована"},{"type":"DestinationReached","distance":0,"time":0,"road":"Драгише Брашована","direction":"N","index":73,"mode":"driving","modifier":"Right","text":"You have arrived at your destination, on the right"}],"summary":{"totalDistance":1550,"totalTime":198.4},"waypointIndices":[0,73],"inputWaypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.2457403,"lng":19.8313},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.2407397,"lng":19.8450102},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"waypoints":[{"options":{"allowUTurn":false},"latLng":{"lat":45.245853,"lng":19.831407},"name":"6, Puskinova, Grbavica, МЗ 7. Јули, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true},{"options":{"allowUTurn":false},"latLng":{"lat":45.240828,"lng":19.845328},"name":"10, Dragise Brasovana, Лиман 2, Liman 2, Liman, Novi Sad, Novi Sad City, South Backa Administrative District, Vojvodina, 21102, Serbia","_initHooksCalled":true}],"properties":{"isSimplified":true},"routesIndex":0}');



INSERT INTO ride (ride_state, price, start_date_time, end_date_time, expected_duration, distance, route_id, driver_id)
   VALUES ('STARTED', 16.3, '2023-09-17 18:47:52.069', '2023-01-01 18:47:52.069', 100, 54.3, 1, 9);

INSERT INTO ride_passengers(ride_id, registered_user_id)
    VALUES (1, 1);


INSERT INTO review (car_stars, driver_stars, comment, reviewer_id, driver_id)
    VALUES
      (2, 2, 'The driver was rude and the car was in bad condition.', 1, 11),
      (1, 1, 'This was the worst ride I have ever taken.', 1, 12);

INSERT INTO MESSAGE (text, sender, receiver)
    VALUES ('Hey, Zima, how are you?','pera@gmail.com', 'zima@gmail.com' ),
            ('Happy Monday Laura! I have good news, the shoes you inquired about are back in stock in your size', 'zima@gmail.com',  'pera@gmail.com'),
            ('Ovo je Djurina poruka', 'djura@gmail.com',  'zima@gmail.com'),
            ('Ovo je poruka koju Djura pise zimi', 'djura@gmail.com',  'pera@gmail.com');


INSERT INTO TIME_SPAN (start_time, end_time) VALUES ('2023-01-23 10:47:52.069', '2023-01-23 11:47:52.069'); --1
INSERT INTO TIME_SPAN (start_time, end_time) VALUES ('2023-01-23 12:47:52.069', '2023-01-23 13:47:52.069'); --1
INSERT INTO TIME_SPAN (start_time, end_time) VALUES ('2023-01-23 15:47:52.069', '2023-01-23 17:47:52.069'); --1
INSERT INTO TIME_SPAN (start_time, end_time) VALUES ('2023-01-23 18:47:52.069', '2023-01-23 18:50:52.069'); --0
INSERT INTO TIME_SPAN (start_time, end_time) VALUES ('2023-01-23 19:47:52.069', '2023-01-23 21:47:52.069'); --2
INSERT INTO TIME_SPAN (start_time, end_time) VALUES ('2023-01-23 23:47:52.069', '2023-01-24 01:47:52.069'); --2

INSERT INTO DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) VALUES (9, 1);
INSERT INTO DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) VALUES (9, 2);
INSERT INTO DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) VALUES (9, 3);
INSERT INTO DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) VALUES (9, 4);
INSERT INTO DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) VALUES (9, 5);
INSERT INTO DRIVER_ACTIVE_TIME (DRIVER_ID, ACTIVE_TIME_ID) VALUES (9, 6);