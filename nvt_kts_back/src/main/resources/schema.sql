CREATE table users (
 email varchar(30) primary key,
 password varchar(30),
 name varchar(30),
 surname varchar(30),
 city varchar(30),
 phone varchar(30),
 profileActivated boolean,
 picture varchar(30),
 isBlocked boolean);
INSERT INTO users(email,password,name,surname,city,phone,profileActivated,picture,isBlocked) VALUES ('email','sifra','ime','prezime','grad','42145135',true,'slika',true);
--
-- CREATE table drivers (
--     id int primary key auto_increment,
--     name varchar(30),
--     email varchar(30));
-- INSERT INTO drivers(name,email) VALUES ('Neko ime','neki e-mail');