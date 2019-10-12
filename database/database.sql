-- create database
use master
if exists (select name from sysdatabases where name = 'milktea_shop') 
	drop database milktea_shop

create database milktea_shop
go

-- use table
use milktea_shop
go

-- create table
create table information ( id int identity primary key,
	fullname varchar(50) not null,
	gender bit not null,
	birthday date not null,
	address varchar(100),
	phone varchar(15),
	email varchar(50)
)

create table account ( id int identity primary key,
	name varchar(50) not null,
	password varchar(500) not null,
	information int not null references information(id),
)

create table roll ( id int identity primary key,
	name varchar(50) not null,
)

create table account_roll ( id int identity primary key,
	account int not null references account(id),
	roll int not null references roll(id),
)
go

create table table_food ( id int identity primary key,
	name varchar(50) not null,
	status bit not null default 1, -- 0: disable, -- enable
)

create table category ( id int identity primary key,
	name varchar(50) not null
)

create table food ( id int identity primary key,
	name varchar(50) not null,	
	category int not null references category(id),
	status bit not null default 1, -- 0: disable, -- enable
)

create table discount(id int identity primary key,
	name varchar(50) not null unique,
	sale float not null
)

create table bill ( id int identity primary key,
	table_food int not null references table_food(id),
	account int references account(id),
	checkin datetime not null default getdate(),
	checkout datetime default null,
	discount int not null references discount(id), 
)

create table bill_details( id int identity primary key,
	bill int not null references bill(id),
	food int not null references food(id),
	quantity int not null default 1,
)