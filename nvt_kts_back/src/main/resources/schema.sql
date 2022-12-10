INSERT INTO role (name) VALUES ('ROLE_USER'),
                                ('ROLE_DRIVER'),
                                ('ROLE_ADMIN');

INSERT INTO my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked, role_id)
    VALUES ('email1@gmail.com', '$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O', 'ime', 'prezime', 'grad',
                '42145135', true, 'slika', false, 1);

INSERT INTO COORD (x, y)
    VALUES (4.5, 5.5);

INSERT INTO REVIEW (comment)
    VALUES ('Kojmentar');
