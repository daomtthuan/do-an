use master
declare @database varchar(11) = 'MilkTeaShop'
exec(
	-- drop database if exists
	'if (select count(name) from sysdatabases where name = @database) > 0 
		drop database ' + @database + '
	go '+
	-- create database
	'create database '+ @database + '
	go '+

	-- use database
	'use '+ @database + '
	go'
)

-- create table
create table Information ( id int identity primary key,
	name varchar(50) not null,
	gender bit not null, -- 0: female, -- male
	birthday date not null,
	address varchar(100) not null,
	phone varchar(15) not null,
	email varchar(50)
)

create table Account ( id int identity primary key,
	name varchar(50) not null,
	password varchar(500) not null,
	information int not null references information(id),
)

create table Roll ( id int identity primary key,
	name varchar(50) not null,
)

create table AccountRoll ( id int identity primary key,
	account int not null references account(id),
	roll int not null references roll(id),
)

create table TableFood ( id int identity primary key,
	status bit not null default 1, -- 0: disable, -- enable
	x int not null default 0,
	y int not null default 0
)

create table Category ( id int identity primary key,
	name varchar(50) not null unique
)

create table Food ( id int identity primary key,
	name varchar(50) not null,	
	category int not null references category(id),
	status bit not null default 1, -- 0: disable, -- enable
)

create table Discount(id int identity primary key,
	name varchar(50) not null unique,
	sale float not null
)

create table Bill ( id int identity primary key,
	table_food int not null references TableFood(id),
	account int references account(id),
	discount int not null default null references discount(id), 
	checkin datetime not null default getdate(),
	checkout datetime not null default null,
)

create table BillDetail( id int identity primary key,
	bill int not null references bill(id),
	food int not null references food(id),
	quantity int not null,
)

--create login MilkTeaShop with password = 'sa' default_database = ''