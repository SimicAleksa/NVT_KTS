insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked) values ('pera@gmail.com','sifra','Pera','Peric','grad','42145135',true,'slika',false);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked) values ('zima@gmail.com','sifra','Sima','Simic','grad','42145135',true,'slika',false);
insert into my_users (email, password, name, surname, city, phone, profile_activated, picture, is_blocked) values ('djura@gmail.com','sifra','Djura','Djuric','grad','42145135',true,'slika',false);

INSERT INTO COORD (x, y) values (4.5, 5.5);
INSERT INTO REVIEW (comment) values ('Kojmentar');
INSERT INTO MESSAGE (text, sender, receiver) values ('Hey','pera@gmail.com', 'zima@gmail.com' );
INSERT INTO MESSAGE (text, sender, receiver) values ('How are you??', 'zima@gmail.com',  'pera@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Djurina poruka', 'djura@gmail.com',  'zima@gmail.com');
INSERT INTO MESSAGE (text, sender, receiver) values ('Nije zimina', 'djura@gmail.com',  'pera@gmail.com');
