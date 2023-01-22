INSERT INTO role (name) VALUES ('ROLE_USER'),
                                ('ROLE_DRIVER'),
                                ('ROLE_ADMIN');

INSERT INTO my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id)
    VALUES
      ('strahinjapopovic.evilpops@gmail.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'ime', 'prezime', 'grad', '42145135', true, 'slika', false, 1),
      ('john.doe@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'John', 'Doe', 'New York', '123-456-7890', true, 'picture.jpg', false, 1),
      ('sarah.johnson@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Sarah', 'Johnson', 'Houston', '123-456-7890', true, 'picture.jpg', false, 1),
      ('michael.brown@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Michael', 'Brown', 'Philadelphia', '123-456-7890', true, 'picture.jpg', false, 1),
      ('ashley.taylor@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Ashley', 'Taylor', 'San Diego', '123-456-7890', true, 'picture.jpg', false, 1),
      ('matthew.davis@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Matthew', 'Davis', 'Dallas', '123-456-7890', true, 'picture.jpg', false, 2),
      ('jane.doe@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Jane', 'Doe', 'Los Angeles', '123-456-7890', true, 'picture.jpg', false, 2),
      ('bob.smith@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Bob', 'Smith', 'Chicago', '123-456-7890', true, 'picture.jpg', false, 2),
      ('lisa.johnson@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Lisa', 'Johnson', 'Phoenix', '123-456-7890', true, 'picture.jpg', false, 2),
      ('robert.williams@example.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'Robert', 'Williams', 'San Antonio', '123-456-7890', true, 'picture.jpg', false, 2);

INSERT INTO driver (id, active, car_type, baby_allowed, pet_allowed, is_driver_free)
    VALUES
      (6, true, 'SEDAN', true, true, true),
      (7, true, 'SUV', false, true, false),
      (8, false, 'HATCHBACK', true, false, true),
      (9, true, 'VAN', false, false, true),
      (10, true, 'SEDAN', true, false, true);

INSERT INTO registered_user (id, is_busy)
    VALUES
      (1, false),
      (2, true),
      (3, true),
      (4, false),
      (5, true);

INSERT INTO coord (x, y, location_name)
    VALUES
      (1.0, 2.0, 'Neka lokacija 1'),
      (3.0, 4.0, 'Neka lokacija 2'),
      (5.0, 6.0, 'Neka lokacija 3'),
      (7.0, 8.0, 'Neka lokacija 4'),
      (9.0, 10.0, 'Neka lokacija 5'),
      (11.0, 12.0, 'Neka lokacija 6'),
      (13.0, 14.0, 'Neka lokacija 7'),
      (15.0, 16.0, 'Neka lokacija 8'),
      (17.0, 18.0, 'Neka lokacija 9'),
      (19.0, 20.0, 'Neka lokacija 10');

INSERT INTO route (start_location_id, end_location_id)
    VALUES
      (1, 2),
      (1, 3),
      (4, 6),
      (1, 5),
      (9, 4),
      (2, 4),
      (5, 8),
      (2, 3),
      (3, 9),
      (2, 7);

INSERT INTO route_optional_locations (route_id, optional_locations_id, optional_location_order)
    VALUES
        (1, 3, 1),
        (1, 4, 2),
        (1, 5, 3),
        (2, 8, 1),
        (2, 9, 2),
        (4, 8, 1),
        (7, 2, 1),
        (7, 10, 2),
        (9, 8, 1),
        (9, 10, 2),
        (10, 3, 1);

INSERT INTO ride (ride_state, price, start_date_time, end_date_time, expected_duration, distance, route_id, caller_id, driver_id)
    VALUES
      ('SCHEDULED', 50.0, '2022-01-01 10:00:00', '2022-01-01 11:00:00', 60, 10.0, 1, 1, 6),
      ('IN_PROGRESS', 75.0, '2022-01-02 10:00:00', '2022-01-02 11:00:00', 60, 15.0, 2, 1, 6),
      ('SCHEDULED', 100.0, '2022-01-03 10:00:00', '2022-01-03 11:00:00', 60, 20.0, 3, 1, 6),
      ('WAITING_FOR_PAYMENT', 125.0, '2022-01-04 10:00:00', '2022-01-04 11:00:00', 60, 25.0, 4, 1, 6),
      ('RESERVED', 150.0, '2022-01-05 10:00:00', '2022-01-05 11:00:00', 60, 30.0, 5, 1, 6),
      ('IN_PROGRESS', 175.0, '2022-01-06 10:00:00', '2022-01-06 11:00:00', 60, 35.0, 6, 1, 6);

INSERT INTO ride_passengers (ride_id, passengers_id)
    VALUES
        (1, 1),
        (1, 4),
        (3, 1),
        (4, 1),
        (4, 2),
        (4, 3),
        (5, 1),
        (6, 1);

INSERT INTO review (car_stars, driver_stars, comment, reviewer_id, ride_id)
    VALUES
      (5, 5, 'Great ride!', 1, 6),
      (4, 4, 'The car was a bit old, but the driver was very friendly.', 1, 6),
      (3, 3, 'The ride was ok, but the car could have been cleaner.', 1, 6),
      (2, 2, 'The driver was rude and the car was in bad condition.', 1, 6),
      (1, 1, 'This was the worst ride I have ever taken.', 1, 6),
      (5, 5, 'Great ride!', 1, 6),
      (4, 4, 'The car was a bit old, but the driver was very friendly.', 1, 6),
      (3, 3, 'The ride was ok, but the car could have been cleaner.', 1, 6),
      (2, 2, 'The driver was rude and the car was in bad condition.', 1, 6),
      (1, 1, 'This was the worst ride I have ever taken.', 1, 6);
