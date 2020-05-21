INSERT INTO Products(title, description, price) VALUES('Atsuktuvas', 'Puikiai suka', 12.50);
INSERT INTO Products(title, description, price) VALUES('Graztas', 'Puikiai grezia', 150.50);
INSERT INTO Products(title, description, price) VALUES('Plaktukas', 'Puikiai kala', 12.50);
INSERT INTO Products(title, description, price) VALUES('Vinis', 'Puikiai laiko', 1.50);

INSERT INTO Customers(mobile, user_name, password, name, last_name, address)
    VALUES('+37061234567', 'vartotojas', '{bcrypt}$2y$12$A7x.2lPxE6YdV8ed6OYbDucRiod32wqMF9JNerE.wq4glQWaIjRnO', 'Be', 'Fantazija', 'Lukiskiu 1 g.');

--INSERT INTO Users(username, password) VALUES('user', '{bcrypt}$2y$12$A7x.2lPxE6YdV8ed6OYbDucRiod32wqMF9JNerE.wq4glQWaIjRnO');
--INSERT INTO Authorities(username, authority) VALUES('user', 'ROLE_USER');