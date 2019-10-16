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

create table Account ( id int identity primary key,
	name varchar(50) not null,
	password varchar(500) not null,
	information int not null references information(id),
)
go

create table Roll ( id int identity primary key,
	name varchar(50) not null,
)
go

create table AccountRoll ( id int identity primary key,
	account int not null references account(id),
	roll int not null references roll(id),
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
	category int not null references category(id),
	status bit not null default 1, -- 0: disable, -- enable
)
go

create table Discount(id int identity primary key,
	name varchar(50) not null unique,
	sale float not null
)
go

create table Bill ( id int identity primary key,
	table_food int not null references TableFood(id),
	account int references account(id),
	discount int not null default null references discount(id), 
	checkin datetime not null default getdate(),
	checkout datetime not null default null,
)
go

create table BillDetail( id int identity primary key,
	bill int not null references bill(id),
	food int not null references food(id),
	quantity int not null,
)
go