use master

-- drop database if exists
if (select count(name) from sysdatabases where name = 'MilkTeaShop') <> 0
	drop database MilkTeaShop
go

-- create database
create database MilkTeaShop
go

-- use database
use MilkTeaShop

-- create table
create table Information ( id int identity primary key,
	name varchar(50) not null,
	gender bit not null, -- 0: female, -- male
	birthday date not null,
	address varchar(100) not null,
	phone varchar(15) not null,
	email varchar(50)
)
go

create table Roll ( id int identity(0,1) primary key,
	name varchar(50) not null,	
)
go

create table Account ( id int identity(0,1) primary key,
	name varchar(50) not null,
	password varchar(500) not null,
	idInformation int references information(id),
	idRoll int not null references Roll(id)
)
go

create table TableFood ( id int identity primary key,
	status bit not null default 1, -- 0: disable, -- enable
	x int not null default 0,
	y int not null default 0
)
go

create table Category ( id int identity primary key,
	name varchar(50) not null unique
)
go

create table Food ( id int identity primary key,
	name varchar(50) not null,
	idCategory int not null references category(id)	
)
go

create table Discount(id int identity primary key,
	name varchar(50) not null unique,
	sale float not null
)
go

create table Bill ( id int identity primary key,
	idTableFood int not null references TableFood(id),
	idAccount int references account(id),
	idDiscount int not null default null references discount(id),
	checkin datetime not null default getdate(),
	checkout datetime not null default null,
)
go

create table BillDetail( id int identity primary key,
	idBill int not null references bill(id),
	idFood int not null references food(id),
	quantity int not null,
)
go
---------------------------------------------------------------------------------------
-- insert data
use MilkTeaShop
go

insert into Roll values('Guest')
insert into Roll values('Customer')
insert into Roll values('Employee')
insert into Roll values('Manager')
go

insert into category values('Milk Tea')
insert into category values('Fruit Tea')
insert into category values('Poppings Yogurt Tea')
insert into category values('Rock Salt Cheese')
insert into category values('Toppings')
go

insert into food values ('Royal', 1)
insert into food values ('Wintermelon', 1)
insert into food values ('Roasted', 1)
insert into food values ('Hazelnut', 1)
insert into food values ('Caramel', 1)
insert into food values ('Almond', 1)
insert into food values ('Vanilla', 1)
insert into food values ('Taro', 1)
insert into food values ('Chocolate', 1)
insert into food values ('Mocha', 1)
insert into food values ('Honeydew', 1)
insert into food values ('Coffee', 1)
insert into food values ('Strawberry', 1)
insert into food values ('Peppermint', 1)
insert into food values ('Mango', 1)
insert into food values ('Banana', 1)
insert into food values ('Coffee Caramel', 1)
insert into food values ('Cookies & Cream', 1)
insert into food values ('Mochaccino', 1)
insert into food values ('Black Forest', 1)
insert into food values ('Double Dutch', 1)
insert into food values ('Choco Oats', 1)

insert into food values ('Green Apple', 2)
insert into food values ('Wintermelon', 2)
insert into food values ('Peach', 2)
insert into food values ('Strawberry', 2)
insert into food values ('Lychee', 2)
insert into food values ('Mango', 2)
insert into food values ('Passionfruit', 2)
insert into food values ('Peach Mango', 2)

insert into food values ('Lychee', 3)
insert into food values ('Strawberry', 3)
insert into food values ('Mango', 3)

insert into food values ('Chocolate', 4)
insert into food values ('Coffee', 4)
insert into food values ('Wintermelon', 4)
insert into food values ('Taro', 4)
insert into food values ('Caramel', 4)
insert into food values ('Royal', 4)
insert into food values ('Cookies & Cream', 4)
insert into food values ('Mochaccino', 4)
insert into food values ('Double Dutch', 4)

insert into food values ('Tapioca Pearls', 5)
insert into food values ('Nata De Coco', 5)
insert into food values ('Egg Pudding', 5)
insert into food values ('Caramel Konjac Jelly', 5)
insert into food values ('Coffee Jelly', 5)
insert into food values ('Grass Jelly', 5)
insert into food values ('Hantien Jelly', 5)
go

insert into information values ('Dao Minh Trung Thuan', 1, '1999-09-18', 'Can Tho', '0939908568', 'DmtthuanA18088@cusc.ctu.edu.vn')
insert into information values ('Ha Thi Hong Tham', 0, '2000-11-01', 'An Giang', '0981118447', 'Htham@cusc.ctu.edu.vn')
insert into information values ('Tan Tan Lap', 1, '2000-09-19', 'Can Tho', '0373200876', 'TtlapA18081@cusc.ctu.edu.vn')
insert into information values ('Tran Quoc Cuong', 1, '1995-01-01', 'Can Tho', '0xxxxxxxxx', 'TqcuongA18080@cusc.ctu.edu.vn')
insert into information values ('Maria Anders', 0, '1988-01-21', 'Obere Str. 57', '0845625627', 'MariaAnders@gmail.com')
insert into information values ('Ana Trujillo', 1, '1988-12-15', 'Avda. de la Constitucien 2222', '0955321544', 'AnaTrujillo@gmail.com')
insert into information values ('Antonio Moreno', 0, '2000-04-29', 'Mataderos  2312', '0757049561', 'AntonioMoreno@gmail.com')
insert into information values ('Thomas Hardy', 1, '1998-05-04', '120 Hanover Sq.', '0846175306', 'ThomasHardy@gmail.com')
insert into information values ('Christina Berglund', 0, '1988-10-13', 'Berguvsvegen  8', '0847915635', 'ChristinaBerglund@gmail.com')
insert into information values ('Hanna Moos', 1, '1986-02-14', 'Forsterstr. 57', '0790588406', 'HannaMoos@gmail.com')
insert into information values ('Frederique Citeaux', 0, '1991-06-13', '24, place Kleber', '0867360581', 'FrederiqueCiteaux@gmail.com')
insert into information values ('Marten Sommer', 1, '1995-06-29', 'C/ Araquil, 67', '0811650189', 'MartinSommer@gmail.com')
insert into information values ('Laurence Lebihan', 1, '1989-11-29', '12, rue des Bouchers', '0729020780', 'LaurenceLebihan@gmail.com')
insert into information values ('Elizabeth Lincoln', 1, '1991-06-03', '23 Tsawassen Blvd.', '0778228262', 'ElizabethLincoln@gmail.com')
insert into information values ('Victoria Ashworth', 1, '1994-06-22', 'Fauntleroy Circus', '0846700939', 'VictoriaAshworth@gmail.com')
insert into information values ('Patricio Simpson', 0, '1995-03-23', 'Cerrito 333', '0898394215', 'PatricioSimpson@gmail.com')
insert into information values ('Francisco Chang', 1, '1999-07-08', 'Sierras de Granada 9993', '0999285622', 'FranciscChang@gmail.com')
insert into information values ('Yang Wang', 1, '1995-12-12', 'Hauptstr. 29', '0894011671', 'YangWang@gmail.com')
insert into information values ('Pedro Afonso', 0, '1995-02-28', 'Av. dos Luseadas, 23', '0979653598', 'PedroAfonso@gmail.com')
insert into information values ('Elizabeth Brown', 1, '1996-04-19', 'Berkeley Gardens 12  Brewery', '0767524917', 'ElizabethBrown@gmail.com')
insert into information values ('Sven Ottlieb', 1, '1997-09-30', 'Walserweg 21', '0840003673', 'SvenOttlieb@gmail.com')
insert into information values ('Janine Labrune', 1, '1997-06-03', '67, rue des Cinquante Otages', '0724427718', 'JanineLabrune@gmail.com')
insert into information values ('Ann Devon', 0, '1995-03-20', '35 King George', '0961335869', 'AnnDevon@gmail.com')
insert into information values ('Roland Mendel', 0, '1991-10-03', 'Kirchgasse 6', '0820458319', 'RolandMendel@gmail.com')
insert into information values ('Aria Cruz', 0, '1988-11-14', 'Rua Ores, 92', '0859464541', 'AriaCruz@gmail.com')
insert into information values ('Diego Roel', 0, '1990-05-28', 'C/ Moralzarzal, 86', '0761258081', 'DiegoRoel@gmail.com')
insert into information values ('Martine Rance', 0, '1987-09-11', '184, chaussee de Tournai', '0873318423', 'MartineRanc@gmail.com')
insert into information values ('Maria Larsson', 0, '1987-06-11', 'ekergatan 24', '0994047587', 'MariaLarsson@gmail.com')
insert into information values ('Peter Franken', 1, '2000-10-22', 'Berliner Platz 43', '0857161496', 'PeterFranken@gmail.com')
insert into information values ('Carine Schmitt', 1, '1995-08-02', '54, rue Royale', '0895815419', 'CarineSchmitt@gmail.com')
insert into information values ('Paolo Accorti', 1, '1991-12-07', 'Via Monte Bianco 34', '0777372533', 'PaoloAccorti@gmail.com')
insert into information values ('Lino Rodriguez', 1, '1986-12-02', 'Jardim das rosas n. 32', '0980581308', 'LinoRodriguez@gmail.com')
insert into information values ('Eduardo Saavedra', 0, '1999-02-13', 'Rambla de Cataluea, 23', '0996550032', 'EduardoSaavedra@gmail.com')
insert into information values ('Jose Pedro Freyre', 0, '1991-12-08', 'C/ Romero, 33', '0819486666', 'JosPedroFreyre@gmail.com')
insert into information values ('Andre Fonseca', 1, '1994-09-26', 'Av. Brasil, 442', '0712963137', 'AndrFonseca@gmail.com')
insert into information values ('Howard Snyder', 1, '1998-01-15', '2732 Baker Blvd.', '0791781973', 'HowardSnyder@gmail.com')
insert into information values ('Manuel Pereira', 1, '1997-09-28', '5e Ave. Los Palos Grandes', '0811748121', 'ManuelPereira@gmail.com')
insert into information values ('Mario Pontes', 1, '1991-08-25', 'Rua do Paeo, 67', '0962221899', 'MarioPontes@gmail.com')
insert into information values ('Carlos Hernendez', 1, '1997-08-12', 'Carrera 22 con Ave', '0875753870', 'CarlosHernndez@gmail.com')
insert into information values ('Yoshi Latimer', 0, '1992-02-07', 'City Center Plaza 516 Main St.', '0810945378', 'YoshiLatimer@gmail.com')
insert into information values ('Patricia McKenna', 0, '1998-02-27', '8 Johnstown Road', '0898568692', 'PatriciaMcKenna@gmail.com')
insert into information values ('Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into information values ('Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into information values ('Daniel Tonini', 0, '1995-07-30', '67, avenue de l''Europe', '0823614781', 'DanielTonini@gmail.com')
insert into information values ('Annette Roulet', 0, '1989-10-08', '1 rue Alsace-Lorraine', '0865275054', 'AnnetteRoulet@gmail.com')
insert into information values ('Yoshi Tannamuri', 1, '1988-08-28', '1900 Oak St.', '0892177465', 'YoshiTannamuri@gmail.com')
insert into information values ('John Steel', 0, '1989-06-09', '12 Orchestra Terrace', '0812822808', 'JohnSteel@gmail.com')
insert into information values ('Renate Messner', 1, '1990-11-04', 'Magazinweg 7', '0872157386', 'RenateMessner@gmail.com')
insert into information values ('Jaime Yorres', 1, '1997-09-03', '87 Polk St. Suite 5', '0943449823', 'JaimeYorres@gmail.com')
insert into information values ('Carlos Gonzelez', 0, '1996-04-06', 'Carrera 52 con Ave', '0917563768', 'CarlosGonzalez@gmail.com')
insert into information values ('Felipe Izquierdo', 0, '1999-08-09', 'Ave. 5 de Mayo Porlamar', '0732947155', 'FelipeIzquierdo@gmail.com')
insert into information values ('Fran Wilson', 1, '1992-02-07', '89 Chiaroscuro Rd.', '0999703111', 'FranWilson@gmail.com')
insert into information values ('Giovanni Rovelli', 0, '1991-11-07', 'Via Ludovico il Moro 22', '0884367996', 'GiovannRovelli@gmail.com')
insert into information values ('Catherine Dewey', 0, '1996-03-07', 'Rue Joseph-Bens 532', '0837192617', 'CatherineDewey@gmail.com')
insert into information values ('Jean Fresniere', 1, '1998-05-18', '43 rue St. Laurent', '0752307744', 'JeanFresnire@gmail.com')
insert into information values ('Alexander Feuer', 0, '1994-11-16', 'Heerstr. 22', '0883720661', 'AlexanderFeuer@gmail.com')
insert into information values ('Simon Crowther', 1, '2000-02-07', 'South House 300 Queensbridge', '0991264821', 'SimonCrowther@gmail.com')
insert into information values ('Yvonne Moncada', 1, '1998-07-19', ' Gustavo Moncada 8585', '0944101027', 'YvonneMoncada@gmail.com')
insert into information values ('Rene Phillips', 0, '1988-06-21', '2743 Bering St.', '0862899782', 'RenePhillips@gmail.com')
insert into information values ('Henriette Pfalzheim', 0, '1993-07-25', 'Mehrheimerstr. 369', '0972237128', 'HenriettePfalzheim@gmail.com')
insert into information values ('Marie Bertrand', 0, '1994-07-04', '265, boulevard Charonne', '0962889043', 'MarieBertrand@gmail.com')
insert into information values ('Guillermo Fernendez', 1, '1997-01-01', 'Calle Dr. Jorge Cash 321', '0819554040', 'GuillermoFernandez@gmail.com')
insert into information values ('Georg Pipps', 1, '2000-06-05', 'Geislweg 14', '0857073643', 'GeorgPipps@gmail.com')
insert into information values ('Isabel de Castro', 0, '2000-03-04', 'Estrada da saede n. 58', '0770522428', 'IsabelCastro@gmail.com')
insert into information values ('Bernardo Batista', 0, '2000-08-19', 'Rua da Panificadora, 12', '0748732186', 'BernardoBatista@gmail.com')
insert into information values ('Lucia Carvalho', 1, '1989-10-05', 'Alameda dos Canerios, 891', '0823089980', 'LuciaCarvalho@gmail.com')
insert into information values ('Horst Kloss', 1, '1999-10-27', 'Taucherstraee 10', '0834061137', 'HorstKloss@gmail.com')
insert into information values ('Sergio Gutierrez', 0, '1990-08-27', 'Av. del Libertador 900', '0746152241', 'SergioGutierrez@gmail.com')
insert into information values ('Paula Wilson', 1, '2000-10-02', '2817 Milton Dr.', '0741324493', 'PaulaWilson@gmail.com')
insert into information values ('Maurizio Moroni', 0, '1996-11-29', 'Strada Provinciale 124', '0873636274', 'MaurizioMoroni@gmail.com')
insert into information values ('Janete Limeira', 0, '2000-07-26', 'Av. Copacabana, 267', '0890638761', 'JaneteLimeira@gmail.com')
insert into information values ('Michael Holz', 0, '1995-04-07', 'Grenzacherweg 237', '0975336859', 'MichaelHolz@gmail.com')
insert into information values ('Alejandra Camino', 1, '1997-09-16', 'Gran Vea, 1', '0955988131', 'AlejandraCamino@gmail.com')
insert into information values ('Jonas Bergulfsen', 1, '1998-03-18', 'Erling Skakkes gate 78', '0978336457', 'JonasBergulfsen@gmail.com')
insert into information values ('Jose Pavarotti', 1, '1988-08-28', '187 Suffolk Ln.', '0858818527', 'JosePavarotti@gmail.com')
insert into information values ('Hari Kumar', 1, '2001-06-15', '90 Wadhurst Rd.', '0899656499', 'HariKumar@gmail.com')
insert into information values ('Jytte Petersen', 1, '1993-11-30', 'Vinbeltet 34', '0873438295', 'JyttePetersen@gmail.com')
insert into information values ('Dominique Perrier', 1, '1991-05-15', '25, rue Lauriston', '0790728975', 'DominiquePerrier@gmail.com')
insert into information values ('Art Braunschweiger', 0, '1995-02-13', 'P.O. Box 555', '0778456998', 'ArtBraunschweiger@gmail.com')
insert into information values ('Pascale Cartrain', 1, '1990-03-10', 'Boulevard Tirou, 255', '0887343755', 'PascaleCartrain@gmail.com')
insert into information values ('Liz Nixon', 1, '1996-08-08', '89 Jefferson Way Suite 2', '0845622853', 'LizNixon@gmail.com')
insert into information values ('Liu Wong', 1, '1992-12-13', '55 Grizzly Peak Rd.', '0849184161', 'LiuWong@gmail.com')
insert into information values ('Karin Josephs', 0, '1988-03-18', 'Luisenstr. 48', '0817673179', 'KarinJosephs@gmail.com')
insert into information values ('Miguel Angel Paolino', 0, '2000-10-02', 'Avda. Azteca 123', '0818253779', 'MiguelAngelPaolino@gmail.com')
insert into information values ('Anabela Domingues', 1, '1994-12-16', 'Av. Ines de Castro, 414', '0790191118', 'AnabelaDomingues@gmail.com')
insert into information values ('Helvetius Nagy', 1, '2000-02-13', '722 DaVinci Blvd.', '0917379809', 'HelvetiusNagy@gmail.com')
insert into information values ('Palle Ibsen', 1, '1987-09-09', 'Smagsloget 45', '0878965858', 'PalleIbsen@gmail.com')
insert into information values ('Mary Saveley', 1, '2000-03-25', '2, rue du Commerce', '0736662843', 'MarySaveley@gmail.com')
insert into information values ('Paul Henriot', 0, '1998-10-06', '59 rue de l''Abbaye', '0923982418', 'PaulHenriot@gmail.com')
insert into information values ('Rita Muller', 0, '1986-06-29', 'Adenauerallee 900', '0813036613', 'RitaMuller@gmail.com')
insert into information values ('Pirkko Koskitalo', 0, '2000-07-14', 'Torikatu 38', '0753921033', 'PirkkoKoskitalo@gmail.com')
insert into information values ('Paula Parente', 0, '1998-09-14', 'Rua do Mercado, 12', '0941035889', 'PaulaParente@gmail.com')
insert into information values ('Karl Jablonski', 1, '1994-04-25', '305 - 14th Ave. S. Suite 3B', '0910925041', 'KarlJablonski@gmail.com')
insert into information values ('Matti Karttunen', 0, '1996-08-04', 'Keskuskatu 45', '0857252976', 'MattiKarttunen@gmail.com')
insert into information values ('Piestrzeniewicz', 1, '1999-10-13', 'ul. Filtrowa 68', '0814400062', 'Piestrzeniewicz@gmail.com')
insert into information values ('Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into information values ('Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into information values ('Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into information values ('Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into information values ('Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into information values ('Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into information values ('Karin Josephs', 0, '1988-03-18', 'Luisenstr. 48', '0817673179', 'KarinJosephs@gmail.com')
insert into information values ('Miguel Angel Paolino', 0, '2000-10-02', 'Avda. Azteca 123', '0818253779', 'MiguelAngelPaolino@gmail.com')
insert into information values ('Anabela Domingues', 1, '1994-12-16', 'Av. Ines de Castro, 414', '0790191118', 'AnabelaDomingues@gmail.com')
go

--ProcedureInsertAccount 'dmtt1', '1', 1, 3

insert into Account values ('guest','1',null,0)
insert into Account values ('dmtt1', '1', 1, 3)
insert into Account values ('htht2', '1', 2, 3)
insert into Account values ('ttl3', '1', 3, 2)
insert into Account values ('tqc4', '1', 4, 2)
go

insert into Account values ('Maria Anders', '1', 5, 1)
insert into Account values ('Ana Trujillo', '1', 6, 1)
insert into Account values ('Antonio Moreno', '1', 7, 1)
insert into Account values ('Thomas Hardy', '1', 8, 1)
insert into Account values ('Christina Berglund', '1', 9, 1)
insert into Account values ('Hanna Moos', '1', 10, 1)
go

insert into Discount values ('MA0011',0.1)
insert into Discount values ('MA0021',0.1)
insert into Discount values ('MA0031',0.1)
insert into Discount values ('MA0041',0.1)
insert into Discount values ('MA0051',0.1)
insert into Discount values ('MA0061',0.1)
insert into Discount values ('MA0071',0.1)
insert into Discount values ('MA0081',0.1)
go


---------------------------------------------------------------------------------------
-- create procedure

create proc ProcedureLogin
	@name varchar(50),
	@password varchar(500)
as begin
	select * from Account where name = @name and password = @password
end
go

create proc ProcedureInsertInformation
	@name varchar(50),
	@gender bit,
	@birthday date,
	@address varchar(100),
	@phone varchar(15),
	@email varchar(50)
as begin
	insert into information values (@name, @gender, @birthday, @address, @phone, @email)	
end
go

create proc ProcedureInsertAccount
	@account varchar(50),
	@password varchar(500),
	@idInformation int,
	@idRoll int
as begin
	insert into account values (@account, @password, @idInformation, @idRoll);
	select result = 1;
end
go

---------------------------------------------------------------------------------------
-- create trigger
create trigger TriggerInsertInformation 
	on Information after insert
as begin
	select id from inserted
end
go
---------------------------------------------------------------------------------------
-- create view

create view ViewCustomer as (
select r.id as roll, a.name as account,
		i.name,i.birthday,i.gender,
		i.address,i.phone,i.email
from Account as a
	join Information as i on a.idInformation = i.id
	join Roll as r on a.idRoll = r.id
where r.id = 2
)
go

create view ViewEmployee as(
select r.id as roll, a.name as account,
		i.name,i.birthday,i.gender,
		i.address,i.phone,i.email
from Account as a
	join Information as i on a.idInformation = i.id
	join Roll as r on a.idRoll = r.id
where r.id = 3)
go

create view ViewManage as (
select r.id as roll, a.name as account,
		i.name,i.birthday,i.gender,
		i.address,i.phone,i.email
from Account as a
	join Information as i on a.idInformation = i.id
	join Roll as r on a.idRoll = r.id
where r.id = 4
)
go
