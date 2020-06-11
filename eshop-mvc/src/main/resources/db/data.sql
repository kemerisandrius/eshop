INSERT INTO Products(title, description, price) VALUES('Atsuktuvas', 'Puikiai suka', 12.50);
INSERT INTO Products(title, description, price) VALUES('Graztas', 'Puikiai grezia', 150.50);
INSERT INTO Products(title, description, price) VALUES('Plaktukas', 'Puikiai kala', 12.50);
INSERT INTO Products(title, description, price) VALUES('Vinis', 'Puikiai laiko', 1.50);

INSERT INTO Users(user_id, username, password, name, last_name)
    VALUES(1, 'user', '{bcrypt}$2y$12$A7x.2lPxE6YdV8ed6OYbDucRiod32wqMF9JNerE.wq4glQWaIjRnO', 'John', 'Doe');
INSERT INTO Users(user_id, username, password, name, last_name)
    VALUES(2, 'admin', '{bcrypt}$2y$12$A7x.2lPxE6YdV8ed6OYbDucRiod32wqMF9JNerE.wq4glQWaIjRnO', 'Jack', 'Sparrow');

INSERT INTO Roles(role_id, role) VALUES(1, 'CUSTOMER');
INSERT INTO Roles(role_id, role) VALUES(2, 'ADMIN');

INSERT INTO Users_Roles(user_id, role_id) VALUES(1, 1);
INSERT INTO Users_Roles(user_id, role_id) VALUES(2, 2);

INSERT INTO Delivery_info(mobile, name, last_name, address, delivery_info_id)
    VALUES('+37061234567', 'John', 'Doe', 'Lukiskiu 1 g.', 1);
