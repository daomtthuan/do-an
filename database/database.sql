use master
go

-- drop database if exists
if (select count(name) from sysdatabases where name = 'milktea_shop') > 0 
	drop database milktea_shop
go

-- create database
use master
create database milktea_shop
go

-- use database
use milktea_shop
go

-- create table
create table information ( id int identity primary key,
	fullname varchar(50) not null,
	gender bit not null, -- 0: female, -- male
	birthday date not null,
	address varchar(100) not null,
	phone varchar(15) not null,
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
	status bit not null default 1, -- 0: disable, -- enable
	x int not null default 0,
	y int not null default 0
)

create table category ( id int identity primary key,
	name varchar(50) not null unique
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
	discount int not null default null references discount(id), 
	checkin datetime not null default getdate(),
	checkout datetime not null default null,
)

create table bill_details( id int identity primary key,
	bill int not null references bill(id),
	food int not null references food(id),
	quantity int not null,
)