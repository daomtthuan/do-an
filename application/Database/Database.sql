use master

-- drop database if exists
if (select count(name)
from sysdatabases
where name = 'MilkTeaShop') <> 0
	drop database [MilkTeaShop]
go

-- create database
create database [MilkTeaShop]
go

-- use database
use [MilkTeaShop]
go

---------------------------------------------------------------------------------------
-- create table

create table [Account]
(
	[id] int identity primary key,
	[account] varchar(50) not null,
	[password] varchar(200) not null,
	[roll] int not null,
	-- 1: customer, 2: employee, 3: manager
	[name] varchar(50) not null,
	[gender] bit not null,
	-- 0: female, 1: male
	[birthday] date not null,
	[address] varchar(100) not null,
	[phone] varchar(15) not null,
	[email] varchar(50),
	[status] bit not null default 1
	-- 0: disable, --1: enable
)
go

create table [Table]
(
	[id] int identity primary key,
	[x] float not null default 0,
	[y] float not null default 0,
	[status] int not null default 1
	-- 0: disable, --1: enable; 2: busy
)
go

create table [Category]
(
	[id] int identity primary key,
	[name] varchar(50) not null unique,
	[status] bit not null default 1
	-- 0: disable, --1: enable
)
go

create table [Food]
(
	[id] int identity primary key,
	[name] varchar(50) not null,
	[idCategory] int not null references [Category]([id]),
	[price] float not null,
	[status] bit not null default 1
	-- 0: disable, -- enable
)
go

create table [Discount]
(
	[id] int identity primary key,
	[name] varchar(50) not null unique,
	[sale] float not null,
	[status] bit not null default 1
	-- 0: disable, -- enable
)
go

create table [Bill]
(
	[id] int identity primary key,

	[idTable] int not null references [Table]([id]),
	[idCustomer] int references [Account]([id]),
	[idEmployee] int not null references [Account]([id]),

	[nameDiscount] varchar(50),
	[sale] float not null default 0,

	[checkin] datetime not null default getdate(),
	[checkout] datetime
)
go

create table [BillDetail]
(
	[id] int identity primary key,
	[idBill] int not null references [Bill]([id]),

	[idFood] int not null,
	[nameFood] varchar(50) not null,

	[idCategory] int not null,
	[nameCategory] varchar(50) not null,

	[quantity] int not null,
	[price] float not null
)
go
---------------------------------------------------------------------------------------

insert into [Category]
	([name])
values('Milk Tea')
insert into [Category]
	([name])
values('Fruit Tea')
insert into [Category]
	([name])
values('Yogurt')
insert into [Category]
	([name])
values('Cheese')
insert into [Category]
	([name])
values('Toppings')
go

insert into [Food]
	([name], [idCategory], [price])
values
	('Royal', 1, 15.3)
insert into [Food]
	([name], [idCategory], [price])
values
	('Wintermelon', 1, 13.2)
insert into [Food]
	([name], [idCategory], [price])
values
	('Roasted', 1, 12.5)
insert into [Food]
	([name], [idCategory], [price])
values
	('Hazelnut', 1, 13.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Caramel', 1, 15.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Almond', 1, 16.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Vanilla', 1, 15.3)
insert into [Food]
	([name], [idCategory], [price])
values
	('Taro', 1, 13.2)
insert into [Food]
	([name], [idCategory], [price])
values
	('Chocolate', 1, 17.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Mocha', 1, 16.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Honeydew', 1, 10.9)
insert into [Food]
	([name], [idCategory], [price])
values
	('Coffee', 1, 15.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Strawberry', 1, 15.9)
insert into [Food]
	([name], [idCategory], [price])
values
	('Peppermint', 1, 15.7)
insert into [Food]
	([name], [idCategory], [price])
values
	('Mango', 1, 16.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Banana', 1, 13.2)
insert into [Food]
	([name], [idCategory], [price])
values
	('Coffee', 1, 15.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Cookies', 1, 12.8)
insert into [Food]
	([name], [idCategory], [price])
values
	('Mochaccino', 1, 14.8)
insert into [Food]
	([name], [idCategory], [price])
values
	('Black Forest', 1, 16.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Double Dutch', 1, 12.3)
insert into [Food]
	([name], [idCategory], [price])
values
	('Choco Oats', 1, 16.5)
insert into [Food]
	([name], [idCategory], [price])
values
	('Green Apple', 2, 17.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Wintermelon', 2, 15.8)
insert into [Food]
	([name], [idCategory], [price])
values
	('Peach', 2, 14.2)
insert into [Food]
	([name], [idCategory], [price])
values
	('Strawberry', 2, 14.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Lychee', 2, 19.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Mango', 2, 17.9)
insert into [Food]
	([name], [idCategory], [price])
values
	('Passionfruit', 2, 12.3)
insert into [Food]
	([name], [idCategory], [price])
values
	('Peach Mango', 2, 16.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Lychee', 3, 16.5)
insert into [Food]
	([name], [idCategory], [price])
values
	('Strawberry', 3, 15.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Mango', 3, 14.9)
insert into [Food]
	([name], [idCategory], [price])
values
	('Chocolate', 4, 17.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Coffee', 4, 19.3)
insert into [Food]
	([name], [idCategory], [price])
values
	('Wintermelon', 4, 11.2)
insert into [Food]
	([name], [idCategory], [price])
values
	('Taro', 4, 13.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Caramel', 4, 17.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Royal', 4, 18.5)
insert into [Food]
	([name], [idCategory], [price])
values
	('Cookies', 4, 12.4)
insert into [Food]
	([name], [idCategory], [price])
values
	('Mochaccino', 4, 18.5)
insert into [Food]
	([name], [idCategory], [price])
values
	('Double Dutch', 4, 14.6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Tapioca Pearls', 5, 5)
insert into [Food]
	([name], [idCategory], [price])
values
	('Nata De Coco', 5, 6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Egg Pudding', 5, 7)
insert into [Food]
	([name], [idCategory], [price])
values
	('Caramel', 5, 6)
insert into [Food]
	([name], [idCategory], [price])
values
	('Coffee', 5, 5)
insert into [Food]
	([name], [idCategory], [price])
values
	('Grass', 5, 3)
insert into [Food]
	([name], [idCategory], [price])
values
	('Hantien', 5, 5)
go

insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('dmtt1', '1', 3, 'Dao Minh Trung Thuan', 1, '1999-09-18', 'Can Tho', '0939908568', 'DmtthuanA18088@cusc.ctu.edu.vn')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('htht2', '1', 3, 'Ha Thi Hong Tham', 0, '2000-11-01', 'An Giang', '0981118447', 'HthamA18124@cusc.ctu.edu.vn')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ttl3', '1', 2, 'Tan Tan Lap', 1, '2000-09-19', 'Can Tho', '0373200876', 'TtlapA18081@cusc.ctu.edu.vn')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ma5', '1', 1, 'Maria Anders', 0, '1988-01-21', 'Obere Str. 57', '0845625627', 'MariaAnders@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('at6', '1', 1, 'Ana Trujillo', 1, '1988-12-15', 'Avda. de la Constitucien 2222', '0955321544', 'AnaTrujillo@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('am7', '1', 1, 'Antonio Moreno', 0, '2000-04-29', 'Mataderos  2312', '0757049561', 'AntonioMoreno@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('th8', '1', 1, 'Thomas Hardy', 1, '1998-05-04', '120 Hanover Sq.', '0846175306', 'ThomasHardy@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('cb9', '1', 1, 'Christina Berglund', 0, '1988-10-13', 'Berguvsvegen  8', '0847915635', 'ChristinaBerglund@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hm10', '1', 1, 'Hanna Moos', 1, '1986-02-14', 'Forsterstr. 57', '0790588406', 'HannaMoos@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('fc11', '1', 1, 'Frederique Citeaux', 0, '1991-06-13', '24, place Kleber', '0867360581', 'FrederiqueCiteaux@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ms12', '1', 1, 'Marten Sommer', 1, '1995-06-29', 'C/ Araquil, 67', '0811650189', 'MartinSommer@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ll13', '1', 1, 'Laurence Lebihan', 1, '1989-11-29', '12, rue des Bouchers', '0729020780', 'LaurenceLebihan@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('el14', '1', 1, 'Elizabeth Lincoln', 1, '1991-06-03', '23 Tsawassen Blvd.', '0778228262', 'ElizabethLincoln@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('va15', '1', 1, 'Victoria Ashworth', 1, '1994-06-22', 'Fauntleroy Circus', '0846700939', 'VictoriaAshworth@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ps16', '1', 1, 'Patricio Simpson', 0, '1995-03-23', 'Cerrito 333', '0898394215', 'PatricioSimpson@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('fc17', '1', 1, 'Francisco Chang', 1, '1999-07-08', 'Sierras de Granada 9993', '0999285622', 'FranciscChang@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('yw18', '1', 1, 'Yang Wang', 1, '1995-12-12', 'Hauptstr. 29', '0894011671', 'YangWang@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pa19', '1', 1, 'Pedro Afonso', 0, '1995-02-28', 'Av. dos Luseadas, 23', '0979653598', 'PedroAfonso@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('eb20', '1', 1, 'Elizabeth Brown', 1, '1996-04-19', 'Berkeley Gardens 12  Brewery', '0767524917', 'ElizabethBrown@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('so21', '1', 1, 'Sven Ottlieb', 1, '1997-09-30', 'Walserweg 21', '0840003673', 'SvenOttlieb@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jl22', '1', 1, 'Janine Labrune', 1, '1997-06-03', '67, rue des Cinquante Otages', '0724427718', 'JanineLabrune@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ad23', '1', 1, 'Ann Devon', 0, '1995-03-20', '35 King George', '0961335869', 'AnnDevon@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('rm24', '1', 1, 'Roland Mendel', 0, '1991-10-03', 'Kirchgasse 6', '0820458319', 'RolandMendel@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ac25', '1', 1, 'Aria Cruz', 0, '1988-11-14', 'Rua Ores, 92', '0859464541', 'AriaCruz@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('dr26', '1', 1, 'Diego Roel', 0, '1990-05-28', 'C/ Moralzarzal, 86', '0761258081', 'DiegoRoel@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('mr27', '1', 1, 'Martine Rance', 0, '1987-09-11', '184, chaussee de Tournai', '0873318423', 'MartineRanc@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ml28', '1', 1, 'Maria Larsson', 0, '1987-06-11', 'ekergatan 24', '0994047587', 'MariaLarsson@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pf29', '1', 1, 'Peter Franken', 1, '2000-10-22', 'Berliner Platz 43', '0857161496', 'PeterFranken@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('cs30', '1', 1, 'Carine Schmitt', 1, '1995-08-02', '54, rue Royale', '0895815419', 'CarineSchmitt@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pa31', '1', 1, 'Paolo Accorti', 1, '1991-12-07', 'Via Monte Bianco 34', '0777372533', 'PaoloAccorti@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('lr32', '1', 1, 'Lino Rodriguez', 1, '1986-12-02', 'Jardim das rosas n. 32', '0980581308', 'LinoRodriguez@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('es33', '1', 1, 'Eduardo Saavedra', 0, '1999-02-13', 'Rambla de Cataluea, 23', '0996550032', 'EduardoSaavedra@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jpf34', '1', 1, 'Jose Pedro Freyre', 0, '1991-12-08', 'C/ Romero, 33', '0819486666', 'JosPedroFreyre@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('af35', '1', 1, 'Andre Fonseca', 1, '1994-09-26', 'Av. Brasil, 442', '0712963137', 'AndrFonseca@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hs36', '1', 1, 'Howard Snyder', 1, '1998-01-15', '2732 Baker Blvd.', '0791781973', 'HowardSnyder@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('mp37', '1', 1, 'Manuel Pereira', 1, '1997-09-28', '5e Ave. Los Palos Grandes', '0811748121', 'ManuelPereira@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('mp38', '1', 1, 'Mario Pontes', 1, '1991-08-25', 'Rua do Paeo, 67', '0962221899', 'MarioPontes@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ch39', '1', 1, 'Carlos Hernendez', 1, '1997-08-12', 'Carrera 22 con Ave', '0875753870', 'CarlosHernndez@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('yl40', '1', 1, 'Yoshi Latimer', 0, '1992-02-07', 'City Center Plaza 516 Main St.', '0810945378', 'YoshiLatimer@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pm41', '1', 1, 'Patricia McKenna', 0, '1998-02-27', '8 Johnstown Road', '0898568692', 'PatriciaMcKenna@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hb42', '1', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pc43', '1', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('dt44', '1', 1, 'Daniel Tonini', 0, '1995-07-30', '67, avenue de l''Europe', '0823614781', 'DanielTonini@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ar45', '1', 1, 'Annette Roulet', 0, '1989-10-08', '1 rue Alsace-Lorraine', '0865275054', 'AnnetteRoulet@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('yt46', '1', 1, 'Yoshi Tannamuri', 1, '1988-08-28', '1900 Oak St.', '0892177465', 'YoshiTannamuri@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('js47', '1', 1, 'John Steel', 0, '1989-06-09', '12 Orchestra Terrace', '0812822808', 'JohnSteel@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('rm48', '1', 1, 'Renate Messner', 1, '1990-11-04', 'Magazinweg 7', '0872157386', 'RenateMessner@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jy49', '1', 1, 'Jaime Yorres', 1, '1997-09-03', '87 Polk St. Suite 5', '0943449823', 'JaimeYorres@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('cg50', '1', 1, 'Carlos Gonzelez', 0, '1996-04-06', 'Carrera 52 con Ave', '0917563768', 'CarlosGonzalez@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('fi51', '1', 1, 'Felipe Izquierdo', 0, '1999-08-09', 'Ave. 5 de Mayo Porlamar', '0732947155', 'FelipeIzquierdo@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('fw52', '1', 1, 'Fran Wilson', 1, '1992-02-07', '89 Chiaroscuro Rd.', '0999703111', 'FranWilson@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('gr53', '1', 1, 'Giovanni Rovelli', 0, '1991-11-07', 'Via Ludovico il Moro 22', '0884367996', 'GiovannRovelli@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('cd54', '1', 1, 'Catherine Dewey', 0, '1996-03-07', 'Rue Joseph-Bens 532', '0837192617', 'CatherineDewey@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jf55', '1', 1, 'Jean Fresniere', 1, '1998-05-18', '43 rue St. Laurent', '0752307744', 'JeanFresnire@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('af56', '1', 1, 'Alexander Feuer', 0, '1994-11-16', 'Heerstr. 22', '0883720661', 'AlexanderFeuer@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('sc57', '1', 1, 'Simon Crowther', 1, '2000-02-07', 'South House 300 Queensbridge', '0991264821', 'SimonCrowther@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ym58', '1', 1, 'Yvonne Moncada', 1, '1998-07-19', ' Gustavo Moncada 8585', '0944101027', 'YvonneMoncada@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('rp59', '1', 1, 'Rene Phillips', 0, '1988-06-21', '2743 Bering St.', '0862899782', 'RenePhillips@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hp60', '1', 1, 'Henriette Pfalzheim', 0, '1993-07-25', 'Mehrheimerstr. 369', '0972237128', 'HenriettePfalzheim@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('mb61', '1', 1, 'Marie Bertrand', 0, '1994-07-04', '265, boulevard Charonne', '0962889043', 'MarieBertrand@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('gf62', '1', 1, 'Guillermo Fernendez', 1, '1997-01-01', 'Calle Dr. Jorge Cash 321', '0819554040', 'GuillermoFernandez@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('gp63', '1', 1, 'Georg Pipps', 1, '2000-06-05', 'Geislweg 14', '0857073643', 'GeorgPipps@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('idc64', '1', 1, 'Isabel de Castro', 0, '2000-03-04', 'Estrada da saede n. 58', '0770522428', 'IsabelCastro@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('bb65', '1', 1, 'Bernardo Batista', 0, '2000-08-19', 'Rua da Panificadora, 12', '0748732186', 'BernardoBatista@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('lc66', '1', 1, 'Lucia Carvalho', 1, '1989-10-05', 'Alameda dos Canerios, 891', '0823089980', 'LuciaCarvalho@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hk67', '1', 1, 'Horst Kloss', 1, '1999-10-27', 'Taucherstraee 10', '0834061137', 'HorstKloss@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('sg68', '1', 1, 'Sergio Gutierrez', 0, '1990-08-27', 'Av. del Libertador 900', '0746152241', 'SergioGutierrez@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pw69', '1', 1, 'Paula Wilson', 1, '2000-10-02', '2817 Milton Dr.', '0741324493', 'PaulaWilson@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('mm70', '1', 1, 'Maurizio Moroni', 0, '1996-11-29', 'Strada Provinciale 124', '0873636274', 'MaurizioMoroni@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jl71', '1', 1, 'Janete Limeira', 0, '2000-07-26', 'Av. Copacabana, 267', '0890638761', 'JaneteLimeira@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('mh72', '1', 1, 'Michael Holz', 0, '1995-04-07', 'Grenzacherweg 237', '0975336859', 'MichaelHolz@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ac73', '1', 1, 'Alejandra Camino', 1, '1997-09-16', 'Gran Vea, 1', '0955988131', 'AlejandraCamino@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jb74', '1', 1, 'Jonas Bergulfsen', 1, '1998-03-18', 'Erling Skakkes gate 78', '0978336457', 'JonasBergulfsen@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jp75', '1', 1, 'Jose Pavarotti', 1, '1988-08-28', '187 Suffolk Ln.', '0858818527', 'JosePavarotti@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hk76', '1', 1, 'Hari Kumar', 1, '2001-06-15', '90 Wadhurst Rd.', '0899656499', 'HariKumar@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('jp77', '1', 1, 'Jytte Petersen', 1, '1993-11-30', 'Vinbeltet 34', '0873438295', 'JyttePetersen@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('dp78', '1', 1, 'Dominique Perrier', 1, '1991-05-15', '25, rue Lauriston', '0790728975', 'DominiquePerrier@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ab79', '1', 1, 'Art Braunschweiger', 0, '1995-02-13', 'P.O. Box 555', '0778456998', 'ArtBraunschweiger@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pc80', '1', 1, 'Pascale Cartrain', 1, '1990-03-10', 'Boulevard Tirou, 255', '0887343755', 'PascaleCartrain@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ln81', '1', 1, 'Liz Nixon', 1, '1996-08-08', '89 Jefferson Way Suite 2', '0845622853', 'LizNixon@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('lw82', '1', 1, 'Liu Wong', 1, '1992-12-13', '55 Grizzly Peak Rd.', '0849184161', 'LiuWong@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('kj83', '1', 1, 'Karin Josephs', 0, '1988-03-18', 'Luisenstr. 48', '0817673179', 'KarinJosephs@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('map84', '1', 1, 'Miguel Angel Paolino', 0, '2000-10-02', 'Avda. Azteca 123', '0818253779', 'MiguelAngelPaolino@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ad85', '1', 1, 'Anabela Domingues', 1, '1994-12-16', 'Av. Ines de Castro, 414', '0790191118', 'AnabelaDomingues@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hn86', '1', 1, 'Helvetius Nagy', 1, '2000-02-13', '722 DaVinci Blvd.', '0917379809', 'HelvetiusNagy@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pi87', '1', 1, 'Palle Ibsen', 1, '1987-09-09', 'Smagsloget 45', '0878965858', 'PalleIbsen@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ms88', '1', 1, 'Mary Saveley', 1, '2000-03-25', '2, rue du Commerce', '0736662843', 'MarySaveley@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ph89', '1', 1, 'Paul Henriot', 0, '1998-10-06', '59 rue de l''Abbaye', '0923982418', 'PaulHenriot@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('rm90', '1', 1, 'Rita Muller', 0, '1986-06-29', 'Adenauerallee 900', '0813036613', 'RitaMuller@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pk91', '1', 1, 'Pirkko Koskitalo', 0, '2000-07-14', 'Torikatu 38', '0753921033', 'PirkkoKoskitalo@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pp92', '1', 1, 'Paula Parente', 0, '1998-09-14', 'Rua do Mercado, 12', '0941035889', 'PaulaParente@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('kj93', '1', 1, 'Karl Jablonski', 1, '1994-04-25', '305 - 14th Ave. S. Suite 3B', '0910925041', 'KarlJablonski@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('mk94', '1', 1, 'Matti Karttunen', 0, '1996-08-04', 'Keskuskatu 45', '0857252976', 'MattiKarttunen@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('p95', '1', 1, 'Piestrzeniewicz', 1, '1999-10-13', 'ul. Filtrowa 68', '0814400062', 'Piestrzeniewicz@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hb96', '1', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pc97', '1', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hb98', '1', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pc99', '1', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('hb100', '1', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('pc101', '1', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('kj102', '1', 1, 'Karin Josephs', 0, '1988-03-18', 'Luisenstr. 48', '0817673179', 'KarinJosephs@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('map103', '1', 1, 'Miguel Angel Paolino', 0, '2000-10-02', 'Avda. Azteca 123', '0818253779', 'MiguelAngelPaolino@gmail.com')
insert into [Account]
	([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
values
	('ad104', '1', 1, 'Anabela Domingues', 1, '1994-12-16', 'Av. Ines de Castro, 414', '0790191118', 'AnabelaDomingues@gmail.com')
go

insert into [Discount]
	([name], [sale])
values
	('MA0011', 10.2)
insert into [Discount]
	([name], [sale])
values
	('MA0021', 5.5)
insert into [Discount]
	([name], [sale])
values
	('MA0031', 3.2)
insert into [Discount]
	([name], [sale])
values
	('MA0041', 7.1)
insert into [Discount]
	([name], [sale])
values
	('MA0051', 10.0)
insert into [Discount]
	([name], [sale])
values
	('MA0061', 11.3)
insert into [Discount]
	([name], [sale])
values
	('MA0071', 5.8)
insert into [Discount]
	([name], [sale])
values
	('MA0081', 2.9)
go

insert into [Table]
	([x], [y])
values
	(10, 20)
insert into [Table]
	([x], [y])
values
	(100, 100)
insert into [Table]
	([x], [y])
values
	(150, 50)
insert into [Table]
	([x], [y])
values
	(250, 30)
insert into [Table]
	([x], [y])
values
	(200, 100)
go

---------------------------------------------------------------------------------------
-- create view
create view [EnabledAdmin]
as
	(
	select *
	from [Account]
	where [roll] > 1 and [status] = 1
)
go

-- create view
create view [EnabledCustomer]
as
	(
	select *
	from [Account]
	where [roll] = 1 and [status] = 1
)
go

create view [EnabledDiscount]
as
	(
	select *
	from [Discount]
	where [status] = 1
)
go

create view [EnabledCategory]
as
	(
	select *
	from [Category]
	where [status] = 1
)
go

create view [EnabledFood]
as
	(
	select *
	from [Food]
	where [status] = 1
)
go

create view [EnabledTable]
as
	(
	select *
	from [Table]
	where [status] > 0
)
go

---------------------------------------------------------------------------------------
-- create procedure

create proc [loginAdmin]
	@account varchar(50),
	@password varchar(500)
as
begin
	select *
	from [EnabledAdmin]
	where [account] = @account and [password] = @password;
end
go

create proc [loginCustomer]
	@account varchar(50),
	@password varchar(500)
as
begin
	select *
	from [EnabledCustomer]
	where [account] = @account and [password] = @password;
end
go

create proc [checkDiscount]
	@name varchar(50)
as
begin
	select *
	from [EnabledDiscount]
	where [name] = @name;
end
go

create proc [getEnabledFood]
	@idCategory int
as
begin
	select *
	from [EnabledFood]
	where [idCategory] = @idCategory;
end
go

-- insert

create proc [insertAccount]
	@account varchar(50),
	@password varchar(200),
	@roll int,
	@name varchar(50),
	@gender bit,
	@birthday date,
	@address varchar(100),
	@phone varchar(15),
	@email varchar(50)
as
begin
	insert into [Account]
		([account], [password], [roll], [name], [gender], [birthday], [address], [phone], [email])
	values
		(@account + cast(ident_current('Account') as varchar), @password, @roll, @name, @gender, @birthday, @address, @phone, @email);
	select *
	from [Account]
	where [id] = scope_identity();
end
go

create proc [insertCategory]
	@name varchar(50)
as
begin
	if exists (select *
	from [Category]
	where [name] = @name)
		select *
	from [Category]
	where [id] = 0;
	else begin
		insert into [Category]
			([name])
		values
			(@name);
		select *
		from [Category]
		where [id] = scope_identity();
	end
end
go

create proc [insertDiscount]
	@name varchar(50),
	@sale float
as
begin
	if exists (select *
	from [Discount]
	where [name]= @name)
		select [id] = 0;
	else
		insert into [Discount]
		([name], [sale])
	values
		(@name, @sale);
	select *
	from [Discount]
	where [id] = scope_identity();
end
go

create proc [insertFood]
	@name varchar(50),
	@idCategory int,
	@price float
as
begin
	insert into [Food]
		([name],[idCategory],[price])
	values
		(@name, @idCategory, @price);
	select *
	from [Food]
	where [id] = scope_identity();
end
go

create proc [insertTable]
	@x float,
	@y float
as
begin
	insert into [Table]
		([x],[y])
	values
		(@x, @y);
	select *
	from [Table]
	where [id] = scope_identity();
end
go

create proc [insertBill]
	@idTable int,
	@idCustomer int,
	@idEmployee int,
	@nameDiscount varchar(50),
	@sale float
as
begin
	insert into [Bill]
		([idTable], [idCustomer], [idEmployee], [nameDiscount], [sale])
	values(@idTable, @idCustomer, @idEmployee, @nameDiscount, @sale);
	select *
	from [Bill]
	where [id] = scope_identity();
end
go

create proc [insertBillDetail]
	@idBill int,
	@idFood int,
	@nameFood varchar(50),
	@idCategory int,
	@nameCategory varchar(50),
	@quantity int,
	@price float
as
begin
	insert into [BillDetail]
		([idBill], [idFood], [nameFood], [idCategory], [nameCategory], [quantity], [price])
	values
		(@idBill, @idFood, @nameFood, @idCategory, @nameCategory, @quantity, @price);
	select *
	from [BillDetail]
	where [id] = scope_identity();
end
go

-- update

create proc [updateAccount]
	@id int,
	@password varchar(200),
	@name varchar(50),
	@gender bit,
	@birthday date,
	@address varchar(100),
	@phone varchar(15),
	@email varchar(50),
	@status bit
as
begin
	update [Account] set
		[password] = @password,
		[name] = @name,
		[gender] = @gender,
		[birthday] = @birthday,
		[address] = @address,
		[phone] = @phone,
		[email] = @email,
		[status] = @status
	where [id] = @id;
	select *
	from [Account]
	where [id] = @id;
end
go

create proc [updateTable]
	@id int,
	@x float,
	@y float,
	@status bit
as
begin
	update [Table] set
		[x] = @x,
		[y] = @y,
		[status] = @status
	where [id] = @id;
	select *
	from [Table]
	where [id] = @id;
end
go

create proc [updateCategory]
	@id int,
	@name varchar(50),
	@status bit
as
begin
	if exists (select *
	from [Category]
	where [name]= @name)
		select *
	from [Category]
	where [id] = 0;
	else begin
		update [Category] set
			[name] = @name,
			[status] = @status
		where [id] = @id;
		select *
		from [Category]
		where [id] = @id;
	end
end
go

create proc [updateFood]
	@id int,
	@name varchar(50),
	@idCategory int,
	@price float,
	@status bit
as
begin
	update [Food] set
		[name] = @name,
		[idCategory] = @idCategory,
		[price] = @price,
		[status] = @status
	where [id] = @id;
	select *
	from [Food]
	where [id] = @id;
end
go

create proc [updateDiscount]
	@id int,
	@name varchar(50),
	@sale float,
	@status bit
as
begin
	if exists (select *
	from [Category]
	where [name]= @name)
		select *
	from [Discount]
	where [id] = 0;
	else begin
		update [Discount] set
			[name] = @name,
			[sale] = @sale,
			[status] = @status
		where [id] = @id;
		select *
		from [Discount]
		where [id] = @id;
	end
end
go