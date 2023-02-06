#Create table Person with apropriate fields
CREATE TABLE person (
id INT AUTO_INCREMENT PRIMARY KEY,
firstName VARCHAR(255),
lastName VARCHAR(255),
age INT,
city VARCHAR(255)
);

#Insert 10 users into table
INSERT INTO person (firstName, lastName, age, city) VALUES ('Jhon', 'Holt', 25, 'New York'),
('Howard', 'Roark', 32, 'Washington'), ('Piter', 'Lewis', 18, 'Neapol'),
('David', 'Beckham', 47, 'London'), ('Wesley', 'Snipes', 60, 'Orlando'),
('Din', 'Winchester', 16, 'Dakota'), ('Miki', 'Rurk', 72, 'New York'),
('Jhon', 'Cena', 39, 'Ohio'), ('Peter', 'Parker', 15, 'New York'),
('Hulk', 'Hogan', 83, 'Ottawa');

#Show data like firstName, lastName
SELECT firstName, lastName FROM person;

#Show data like firstName, age
SELECT firstName, age FROM person;

#Show data like firstName, lastName, age, city
SELECT firstName, lastName, age, city FROM person;

#Show all data and sort by city
SELECT * FROM person ORDER BY city DESC;

#Show all data and sort by age
SELECT * FROM person ORDER BY age;

#Show all data and sort by firstName
SELECT * FROM person ORDER BY firstName DESC;