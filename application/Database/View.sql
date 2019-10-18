

CREATE VIEW Manage_Customers AS
SELECT r.id as roll, a.name as account,
		i.name,i.birthday,i.gender,
		i.address,i.phone,i.email
FROM Account as a
	JOIN Information as i ON a.idInformation = i.id
	JOIN Roll as r ON a.idRoll = r.id
WHERE r.id = 2

select * from Manage_Customers
go

CREATE VIEW Manage_Employee AS(
SELECT r.id as roll, a.name as account,
		i.name,i.birthday,i.gender,
		i.address,i.phone,i.email
FROM Account as a
	JOIN Information as i ON a.idInformation = i.id
	JOIN Roll as r ON a.idRoll = r.id
WHERE r.id = 3)
go

select * from Manage_Employee

CREATE VIEW Manage AS
SELECT r.id as roll, a.name as account,
		i.name,i.birthday,i.gender,
		i.address,i.phone,i.email
FROM Account as a
	JOIN Information as i ON a.idInformation = i.id
	JOIN Roll as r ON a.idRoll = r.id
WHERE r.id = 4
