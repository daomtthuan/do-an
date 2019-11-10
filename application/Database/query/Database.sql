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