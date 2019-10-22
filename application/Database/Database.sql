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
go

---------------------------------------------------------------------------------------
-- create table

create table Account ( id int identity primary key,
	account varchar(50),
	password varchar(200) not null,
	roll int not null, -- 1: customer, 2: employee, 3: manager
	name varchar(50) not null,
	gender bit not null, -- 0: female, 1: male
	birthday date not null,
	address varchar(100) not null,
	phone varchar(15) not null,
	email varchar(50)
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
	idCustomer int references account(id),
	idEmployee int not null references account(id),
	idDiscount int not null default null references discount(id),
	checkin datetime not null default getdate(),
	checkout datetime not null default null,
)
go

create table BillDetail( id int identity primary key,
	idBill int not null references bill(id),
	idFood int not null references Food(id),
	quantity int not null,
)
go
---------------------------------------------------------------------------------------
-- insert data
use MilkTeaShop
go

insert into Category values('Milk Tea')
insert into Category values('Fruit Tea')
insert into Category values('Poppings Yogurt Tea')
insert into Category values('Rock Salt Cheese')
insert into Category values('Toppings')
go

insert into Food values ('Royal', 1)
insert into Food values ('Wintermelon', 1)
insert into Food values ('Roasted', 1)
insert into Food values ('Hazelnut', 1)
insert into Food values ('Caramel', 1)
insert into Food values ('Almond', 1)
insert into Food values ('Vanilla', 1)
insert into Food values ('Taro', 1)
insert into Food values ('Chocolate', 1)
insert into Food values ('Mocha', 1)
insert into Food values ('Honeydew', 1)
insert into Food values ('Coffee', 1)
insert into Food values ('Strawberry', 1)
insert into Food values ('Peppermint', 1)
insert into Food values ('Mango', 1)
insert into Food values ('Banana', 1)
insert into Food values ('Coffee Caramel', 1)
insert into Food values ('Cookies & Cream', 1)
insert into Food values ('Mochaccino', 1)
insert into Food values ('Black Forest', 1)
insert into Food values ('Double Dutch', 1)
insert into Food values ('Choco Oats', 1)
insert into Food values ('Green Apple', 2)
insert into Food values ('Wintermelon', 2)
insert into Food values ('Peach', 2)
insert into Food values ('Strawberry', 2)
insert into Food values ('Lychee', 2)
insert into Food values ('Mango', 2)
insert into Food values ('Passionfruit', 2)
insert into Food values ('Peach Mango', 2)
insert into Food values ('Lychee', 3)
insert into Food values ('Strawberry', 3)
insert into Food values ('Mango', 3)
insert into Food values ('Chocolate', 4)
insert into Food values ('Coffee', 4)
insert into Food values ('Wintermelon', 4)
insert into Food values ('Taro', 4)
insert into Food values ('Caramel', 4)
insert into Food values ('Royal', 4)
insert into Food values ('Cookies & Cream', 4)
insert into Food values ('Mochaccino', 4)
insert into Food values ('Double Dutch', 4)
insert into Food values ('Tapioca Pearls', 5)
insert into Food values ('Nata De Coco', 5)
insert into Food values ('Egg Pudding', 5)
insert into Food values ('Caramel Konjac Jelly', 5)
insert into Food values ('Coffee Jelly', 5)
insert into Food values ('Grass Jelly', 5)
insert into Food values ('Hantien Jelly', 5)
go

insert Account values ('dmtt1', '2', 1, 'Dao Minh Trung Thuan', 1, '1999-09-18', 'Can Tho', '0939908568', 'DmtthuanA18088@cusc.ctu.edu.vn')
insert Account values ('htht2', '2', 1, 'Ha Thi Hong Tham', 0, '2000-11-01', 'An Giang', '0981118447', 'HthamA18124@cusc.ctu.edu.vn')
insert Account values ('ttl3', '1', 1, 'Tan Tan Lap', 1, '2000-09-19', 'Can Tho', '0373200876', 'TtlapA18081@cusc.ctu.edu.vn')
insert Account values ('tqc4', '1', 1, 'Tran Quoc Cuong', 1, '1995-01-01', 'Can Tho', '0xxxxxxxxx', 'TqcuongA18080@cusc.ctu.edu.vn')
insert Account values ('ma5', '0', 1, 'Maria Anders', 0, '1988-01-21', 'Obere Str. 57', '0845625627', 'MariaAnders@gmail.com')
insert Account values ('at6', '0', 1, 'Ana Trujillo', 1, '1988-12-15', 'Avda. de la Constitucien 2222', '0955321544', 'AnaTrujillo@gmail.com')
insert Account values ('am7', '0', 1, 'Antonio Moreno', 0, '2000-04-29', 'Mataderos  2312', '0757049561', 'AntonioMoreno@gmail.com')
insert Account values ('th8', '0', 1, 'Thomas Hardy', 1, '1998-05-04', '120 Hanover Sq.', '0846175306', 'ThomasHardy@gmail.com')
insert Account values ('cb9', '0', 1, 'Christina Berglund', 0, '1988-10-13', 'Berguvsvegen  8', '0847915635', 'ChristinaBerglund@gmail.com')
insert Account values ('hm10', '0', 1, 'Hanna Moos', 1, '1986-02-14', 'Forsterstr. 57', '0790588406', 'HannaMoos@gmail.com')
insert Account values ('fc11', '0', 1, 'Frederique Citeaux', 0, '1991-06-13', '24, place Kleber', '0867360581', 'FrederiqueCiteaux@gmail.com')
insert Account values ('ms12', '0', 1, 'Marten Sommer', 1, '1995-06-29', 'C/ Araquil, 67', '0811650189', 'MartinSommer@gmail.com')
insert Account values ('ll13', '0', 1, 'Laurence Lebihan', 1, '1989-11-29', '12, rue des Bouchers', '0729020780', 'LaurenceLebihan@gmail.com')
insert Account values ('el14', '0', 1, 'Elizabeth Lincoln', 1, '1991-06-03', '23 Tsawassen Blvd.', '0778228262', 'ElizabethLincoln@gmail.com')
insert Account values ('va15', '0', 1, 'Victoria Ashworth', 1, '1994-06-22', 'Fauntleroy Circus', '0846700939', 'VictoriaAshworth@gmail.com')
insert Account values ('ps16', '0', 1, 'Patricio Simpson', 0, '1995-03-23', 'Cerrito 333', '0898394215', 'PatricioSimpson@gmail.com')
insert Account values ('fc17', '0', 1, 'Francisco Chang', 1, '1999-07-08', 'Sierras de Granada 9993', '0999285622', 'FranciscChang@gmail.com')
insert Account values ('yw18', '0', 1, 'Yang Wang', 1, '1995-12-12', 'Hauptstr. 29', '0894011671', 'YangWang@gmail.com')
insert Account values ('pa19', '0', 1, 'Pedro Afonso', 0, '1995-02-28', 'Av. dos Luseadas, 23', '0979653598', 'PedroAfonso@gmail.com')
insert Account values ('eb20', '0', 1, 'Elizabeth Brown', 1, '1996-04-19', 'Berkeley Gardens 12  Brewery', '0767524917', 'ElizabethBrown@gmail.com')
insert Account values ('so21', '0', 1, 'Sven Ottlieb', 1, '1997-09-30', 'Walserweg 21', '0840003673', 'SvenOttlieb@gmail.com')
insert Account values ('jl22', '0', 1, 'Janine Labrune', 1, '1997-06-03', '67, rue des Cinquante Otages', '0724427718', 'JanineLabrune@gmail.com')
insert Account values ('ad23', '0', 1, 'Ann Devon', 0, '1995-03-20', '35 King George', '0961335869', 'AnnDevon@gmail.com')
insert Account values ('rm24', '0', 1, 'Roland Mendel', 0, '1991-10-03', 'Kirchgasse 6', '0820458319', 'RolandMendel@gmail.com')
insert Account values ('ac25', '0', 1, 'Aria Cruz', 0, '1988-11-14', 'Rua Ores, 92', '0859464541', 'AriaCruz@gmail.com')
insert Account values ('dr26', '0', 1, 'Diego Roel', 0, '1990-05-28', 'C/ Moralzarzal, 86', '0761258081', 'DiegoRoel@gmail.com')
insert Account values ('mr27', '0', 1, 'Martine Rance', 0, '1987-09-11', '184, chaussee de Tournai', '0873318423', 'MartineRanc@gmail.com')
insert Account values ('ml28', '0', 1, 'Maria Larsson', 0, '1987-06-11', 'ekergatan 24', '0994047587', 'MariaLarsson@gmail.com')
insert Account values ('pf29', '0', 1, 'Peter Franken', 1, '2000-10-22', 'Berliner Platz 43', '0857161496', 'PeterFranken@gmail.com')
insert Account values ('cs30', '0', 1, 'Carine Schmitt', 1, '1995-08-02', '54, rue Royale', '0895815419', 'CarineSchmitt@gmail.com')
insert Account values ('pa31', '0', 1, 'Paolo Accorti', 1, '1991-12-07', 'Via Monte Bianco 34', '0777372533', 'PaoloAccorti@gmail.com')
insert Account values ('lr32', '0', 1, 'Lino Rodriguez', 1, '1986-12-02', 'Jardim das rosas n. 32', '0980581308', 'LinoRodriguez@gmail.com')
insert Account values ('es33', '0', 1, 'Eduardo Saavedra', 0, '1999-02-13', 'Rambla de Cataluea, 23', '0996550032', 'EduardoSaavedra@gmail.com')
insert Account values ('jpf34', '0', 1, 'Jose Pedro Freyre', 0, '1991-12-08', 'C/ Romero, 33', '0819486666', 'JosPedroFreyre@gmail.com')
insert Account values ('af35', '0', 1, 'Andre Fonseca', 1, '1994-09-26', 'Av. Brasil, 442', '0712963137', 'AndrFonseca@gmail.com')
insert Account values ('hs36', '0', 1, 'Howard Snyder', 1, '1998-01-15', '2732 Baker Blvd.', '0791781973', 'HowardSnyder@gmail.com')
insert Account values ('mp37', '0', 1, 'Manuel Pereira', 1, '1997-09-28', '5e Ave. Los Palos Grandes', '0811748121', 'ManuelPereira@gmail.com')
insert Account values ('mp38', '0', 1, 'Mario Pontes', 1, '1991-08-25', 'Rua do Paeo, 67', '0962221899', 'MarioPontes@gmail.com')
insert Account values ('ch39', '0', 1, 'Carlos Hernendez', 1, '1997-08-12', 'Carrera 22 con Ave', '0875753870', 'CarlosHernndez@gmail.com')
insert Account values ('yl40', '0', 1, 'Yoshi Latimer', 0, '1992-02-07', 'City Center Plaza 516 Main St.', '0810945378', 'YoshiLatimer@gmail.com')
insert Account values ('pm41', '0', 1, 'Patricia McKenna', 0, '1998-02-27', '8 Johnstown Road', '0898568692', 'PatriciaMcKenna@gmail.com')
insert Account values ('hb42', '0', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert Account values ('pc43', '0', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert Account values ('dt44', '0', 1, 'Daniel Tonini', 0, '1995-07-30', '67, avenue de l''Europe', '0823614781', 'DanielTonini@gmail.com')
insert Account values ('ar45', '0', 1, 'Annette Roulet', 0, '1989-10-08', '1 rue Alsace-Lorraine', '0865275054', 'AnnetteRoulet@gmail.com')
insert Account values ('yt46', '0', 1, 'Yoshi Tannamuri', 1, '1988-08-28', '1900 Oak St.', '0892177465', 'YoshiTannamuri@gmail.com')
insert Account values ('js47', '0', 1, 'John Steel', 0, '1989-06-09', '12 Orchestra Terrace', '0812822808', 'JohnSteel@gmail.com')
insert Account values ('rm48', '0', 1, 'Renate Messner', 1, '1990-11-04', 'Magazinweg 7', '0872157386', 'RenateMessner@gmail.com')
insert Account values ('jy49', '0', 1, 'Jaime Yorres', 1, '1997-09-03', '87 Polk St. Suite 5', '0943449823', 'JaimeYorres@gmail.com')
insert Account values ('cg50', '0', 1, 'Carlos Gonzelez', 0, '1996-04-06', 'Carrera 52 con Ave', '0917563768', 'CarlosGonzalez@gmail.com')
insert Account values ('fi51', '0', 1, 'Felipe Izquierdo', 0, '1999-08-09', 'Ave. 5 de Mayo Porlamar', '0732947155', 'FelipeIzquierdo@gmail.com')
insert Account values ('fw52', '0', 1, 'Fran Wilson', 1, '1992-02-07', '89 Chiaroscuro Rd.', '0999703111', 'FranWilson@gmail.com')
insert Account values ('gr53', '0', 1, 'Giovanni Rovelli', 0, '1991-11-07', 'Via Ludovico il Moro 22', '0884367996', 'GiovannRovelli@gmail.com')
insert Account values ('cd54', '0', 1, 'Catherine Dewey', 0, '1996-03-07', 'Rue Joseph-Bens 532', '0837192617', 'CatherineDewey@gmail.com')
insert Account values ('jf55', '0', 1, 'Jean Fresniere', 1, '1998-05-18', '43 rue St. Laurent', '0752307744', 'JeanFresnire@gmail.com')
insert Account values ('af56', '0', 1, 'Alexander Feuer', 0, '1994-11-16', 'Heerstr. 22', '0883720661', 'AlexanderFeuer@gmail.com')
insert Account values ('sc57', '0', 1, 'Simon Crowther', 1, '2000-02-07', 'South House 300 Queensbridge', '0991264821', 'SimonCrowther@gmail.com')
insert Account values ('ym58', '0', 1, 'Yvonne Moncada', 1, '1998-07-19', ' Gustavo Moncada 8585', '0944101027', 'YvonneMoncada@gmail.com')
insert Account values ('rp59', '0', 1, 'Rene Phillips', 0, '1988-06-21', '2743 Bering St.', '0862899782', 'RenePhillips@gmail.com')
insert Account values ('hp60', '0', 1, 'Henriette Pfalzheim', 0, '1993-07-25', 'Mehrheimerstr. 369', '0972237128', 'HenriettePfalzheim@gmail.com')
insert Account values ('mb61', '0', 1, 'Marie Bertrand', 0, '1994-07-04', '265, boulevard Charonne', '0962889043', 'MarieBertrand@gmail.com')
insert Account values ('gf62', '0', 1, 'Guillermo Fernendez', 1, '1997-01-01', 'Calle Dr. Jorge Cash 321', '0819554040', 'GuillermoFernandez@gmail.com')
insert Account values ('gp63', '0', 1, 'Georg Pipps', 1, '2000-06-05', 'Geislweg 14', '0857073643', 'GeorgPipps@gmail.com')
insert Account values ('idc64', '0', 1, 'Isabel de Castro', 0, '2000-03-04', 'Estrada da saede n. 58', '0770522428', 'IsabelCastro@gmail.com')
insert Account values ('bb65', '0', 1, 'Bernardo Batista', 0, '2000-08-19', 'Rua da Panificadora, 12', '0748732186', 'BernardoBatista@gmail.com')
insert Account values ('lc66', '0', 1, 'Lucia Carvalho', 1, '1989-10-05', 'Alameda dos Canerios, 891', '0823089980', 'LuciaCarvalho@gmail.com')
insert Account values ('hk67', '0', 1, 'Horst Kloss', 1, '1999-10-27', 'Taucherstraee 10', '0834061137', 'HorstKloss@gmail.com')
insert Account values ('sg68', '0', 1, 'Sergio Gutierrez', 0, '1990-08-27', 'Av. del Libertador 900', '0746152241', 'SergioGutierrez@gmail.com')
insert Account values ('pw69', '0', 1, 'Paula Wilson', 1, '2000-10-02', '2817 Milton Dr.', '0741324493', 'PaulaWilson@gmail.com')
insert Account values ('mm70', '0', 1, 'Maurizio Moroni', 0, '1996-11-29', 'Strada Provinciale 124', '0873636274', 'MaurizioMoroni@gmail.com')
insert Account values ('jl71', '0', 1, 'Janete Limeira', 0, '2000-07-26', 'Av. Copacabana, 267', '0890638761', 'JaneteLimeira@gmail.com')
insert Account values ('mh72', '0', 1, 'Michael Holz', 0, '1995-04-07', 'Grenzacherweg 237', '0975336859', 'MichaelHolz@gmail.com')
insert Account values ('ac73', '0', 1, 'Alejandra Camino', 1, '1997-09-16', 'Gran Vea, 1', '0955988131', 'AlejandraCamino@gmail.com')
insert Account values ('jb74', '0', 1, 'Jonas Bergulfsen', 1, '1998-03-18', 'Erling Skakkes gate 78', '0978336457', 'JonasBergulfsen@gmail.com')
insert Account values ('jp75', '0', 1, 'Jose Pavarotti', 1, '1988-08-28', '187 Suffolk Ln.', '0858818527', 'JosePavarotti@gmail.com')
insert Account values ('hk76', '0', 1, 'Hari Kumar', 1, '2001-06-15', '90 Wadhurst Rd.', '0899656499', 'HariKumar@gmail.com')
insert Account values ('jp77', '0', 1, 'Jytte Petersen', 1, '1993-11-30', 'Vinbeltet 34', '0873438295', 'JyttePetersen@gmail.com')
insert Account values ('dp78', '0', 1, 'Dominique Perrier', 1, '1991-05-15', '25, rue Lauriston', '0790728975', 'DominiquePerrier@gmail.com')
insert Account values ('ab79', '0', 1, 'Art Braunschweiger', 0, '1995-02-13', 'P.O. Box 555', '0778456998', 'ArtBraunschweiger@gmail.com')
insert Account values ('pc80', '0', 1, 'Pascale Cartrain', 1, '1990-03-10', 'Boulevard Tirou, 255', '0887343755', 'PascaleCartrain@gmail.com')
insert Account values ('ln81', '0', 1, 'Liz Nixon', 1, '1996-08-08', '89 Jefferson Way Suite 2', '0845622853', 'LizNixon@gmail.com')
insert Account values ('lw82', '0', 1, 'Liu Wong', 1, '1992-12-13', '55 Grizzly Peak Rd.', '0849184161', 'LiuWong@gmail.com')
insert Account values ('kj83', '0', 1, 'Karin Josephs', 0, '1988-03-18', 'Luisenstr. 48', '0817673179', 'KarinJosephs@gmail.com')
insert Account values ('map84', '0', 1, 'Miguel Angel Paolino', 0, '2000-10-02', 'Avda. Azteca 123', '0818253779', 'MiguelAngelPaolino@gmail.com')
insert Account values ('ad85', '0', 1, 'Anabela Domingues', 1, '1994-12-16', 'Av. Ines de Castro, 414', '0790191118', 'AnabelaDomingues@gmail.com')
insert Account values ('hn86', '0', 1, 'Helvetius Nagy', 1, '2000-02-13', '722 DaVinci Blvd.', '0917379809', 'HelvetiusNagy@gmail.com')
insert Account values ('pi87', '0', 1, 'Palle Ibsen', 1, '1987-09-09', 'Smagsloget 45', '0878965858', 'PalleIbsen@gmail.com')
insert Account values ('ms88', '0', 1, 'Mary Saveley', 1, '2000-03-25', '2, rue du Commerce', '0736662843', 'MarySaveley@gmail.com')
insert Account values ('ph89', '0', 1, 'Paul Henriot', 0, '1998-10-06', '59 rue de l''Abbaye', '0923982418', 'PaulHenriot@gmail.com')
insert Account values ('rm90', '0', 1, 'Rita Muller', 0, '1986-06-29', 'Adenauerallee 900', '0813036613', 'RitaMuller@gmail.com')
insert Account values ('pk91', '0', 1, 'Pirkko Koskitalo', 0, '2000-07-14', 'Torikatu 38', '0753921033', 'PirkkoKoskitalo@gmail.com')
insert Account values ('pp92', '0', 1, 'Paula Parente', 0, '1998-09-14', 'Rua do Mercado, 12', '0941035889', 'PaulaParente@gmail.com')
insert Account values ('kj93', '0', 1, 'Karl Jablonski', 1, '1994-04-25', '305 - 14th Ave. S. Suite 3B', '0910925041', 'KarlJablonski@gmail.com')
insert Account values ('mk94', '0', 1, 'Matti Karttunen', 0, '1996-08-04', 'Keskuskatu 45', '0857252976', 'MattiKarttunen@gmail.com')
insert Account values ('p95', '0', 1, 'Piestrzeniewicz', 1, '1999-10-13', 'ul. Filtrowa 68', '0814400062', 'Piestrzeniewicz@gmail.com')
insert Account values ('hb96', '0', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert Account values ('pc97', '0', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert Account values ('hb98', '0', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert Account values ('pc99', '0', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert Account values ('hb100', '0', 1, 'Helen Bennett', 0, '1997-12-25', 'Garden House Crowther Way', '0825609394', 'HelenBennett@gmail.com')
insert Account values ('pc101', '0', 1, 'Philip Cramer', 0, '2000-07-15', 'Maubelstr. 90', '0940014711', 'PhilipCramer@gmail.com')
insert Account values ('kj102', '0', 1, 'Karin Josephs', 0, '1988-03-18', 'Luisenstr. 48', '0817673179', 'KarinJosephs@gmail.com')
insert Account values ('map103', '0', 1, 'Miguel Angel Paolino', 0, '2000-10-02', 'Avda. Azteca 123', '0818253779', 'MiguelAngelPaolino@gmail.com')
insert Account values ('ad104', '0', 1, 'Anabela Domingues', 1, '1994-12-16', 'Av. Ines de Castro, 414', '0790191118', 'AnabelaDomingues@gmail.com')
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

create proc LoginAdmin
	@name varchar(50),
	@password varchar(500)
as begin
	select * from Account
	where name = @name and password = @password and roll > 1
end
go

create proc LoginCustomer
	@name varchar(50),
	@password varchar(500)
as begin
	select * from Account
	where name = @name and password = @password and roll = 1
end
go

create proc InsertAccount
	@account varchar(50),
	@name varchar(50),
	@gender bit,
	@birthday date,
	@address varchar(100),
	@phone varchar(15),
	@email varchar(50),
	@idRoll int
as begin
	insert into Information values (@name, @gender, @birthday, @address, @phone, @email)
	declare @idInformation int = scope_identity();
	insert into Account values (null, 0, '1', @account + cast(@idInformation as varchar), '1', @idInformation, @idRoll);
	select id = scope_identity(), name = @account, idInformation = @idInformation, idRoll = @idRoll;
end
go

create proc InsertCategory
	@name varchar(50)
as begin
	insert into Category values (@name)
end
go

create proc InsertDiscount
	@name varchar(50),
	@sale float
as begin
	insert into Discount values(@name, @sale)
	select 1
end
go

select * from ViewCustomer
