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
	[idDiscount] int references [Discount]([id]),

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
-- create procedure

create proc [loginAdmin]
	@account varchar(50),
	@password varchar(500)
as
begin
	select *
	from [Account]
	where [account] = @account and [password] = @password and [roll] > 1 and [status] = 1;
end
go

create proc [loginCustomer]
	@account varchar(50),
	@password varchar(500)
as
begin
	select *
	from [Account]
	where [account] = @account and [password] = @password and [roll] = 1 and [status] = 1;
end
go

create proc [checkDiscount]
	@name varchar(50)
as
begin
	select *
	from [Discount]
	where [name] = @name and [status] = 1;
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
	@idDiscount int,
	@nameDiscount varchar(50),
	@sale float
as
begin
	insert into [Bill]
		([idTable], [idCustomer], [idEmployee], [idDiscount], [nameDiscount], [sale])
	values(@idTable, @idCustomer, @idEmployee,@idDiscount, @nameDiscount, @sale);
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
	@email varchar(50)
as
begin
	update [Account] set
		[password] = @password,
		[name] = @name,
		[gender] = @gender,
		[birthday] = @birthday,
		[address] = @address,
		[phone] = @phone,
		[email] = @email
	where [id] = @id;
	select *
	from [Account]
	where [id] = @id;
end
go

create proc [updateTable]
	@id int,
	@x float,
	@y float
as
begin
	update [Table] set
		[x] = @x,
		[y] = @y
	where [id] = @id;
	select *
	from [Table]
	where [id] = @id;
end
go

create proc [updateCategory]
	@id int,
	@name varchar(50)
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
			[name] = @name
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
	@price float
as
begin
	update [Food] set
		[name] = @name,
		[idCategory] = @idCategory,
		[price] = @price
	where [id] = @id;
	select *
	from [Food]
	where [id] = @id;
end
go

create proc [updateDiscount]
	@id int,
	@name varchar(50),
	@sale float
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
			[sale] = @sale
		where [id] = @id;
		select *
		from [Discount]
		where [id] = @id;
	end
end
go

-- change status

create proc [statusAccount]
	@id int,
	@status bit
as
begin
	update [Account] set
		[status] = @status
	where [id] = @id;
	select *
	from [Account]
	where [id] = @id;
end
go

create proc [statusTable]
	@id int,
	@status int
as
begin
	update [Table] set
		[status] = @status
	where [id] = @id;
	select *
	from [Table]
	where [id] = @id;
end
go

create proc [statusCategory]
	@id int,
	@status bit
as
begin	
	update [Category] set
		[status] = @status
	where [id] = @id;
	select *
	from [Category]
	where [id] = @id;
end
go

create proc [statusFood]
	@id int,
	@status bit
as
begin
	update [Food] set
		[status] = @status
	where [id] = @id;
	select *
	from [Food]
	where [id] = @id;
end
go

create proc [statusDiscount]
	@id int,
	@status bit
as
begin
	update [Discount] set
		[status] = @status
	where [id] = @id;
	select *
	from [Discount]
	where [id] = @id;
end
go

-- delete

create proc [deleteAccount]
	@id int
as
begin
	begin try
		delete [Account] where [id] = @id;
		select * from [Account] where [id] = 0;
	end try
	begin catch
		select * from [Account] where [id] = @id;
	end catch
end
go

create proc [deleteTable]
	@id int
as
begin
	begin try
		delete [Table] where [id] = @id;
		select * from [Table] where [id] = 0;
	end try
	begin catch
		select * from [Table] where [id] = @id;
	end catch
end
go

create proc [deleteCategory]
	@id int
as
begin	
	begin try
		delete [Category] where [id] = @id;
		select * from [Category] where [id] = 0;
	end try
	begin catch
		select * from [Category] where [id] = @id;
	end catch
end
go

create proc [deleteFood]
	@id int
as
begin
	begin try
		delete [Food] where [id] = @id;
		select * from [Food] where [id] = 0;
	end try
	begin catch
		select * from [Food] where [id] = @id;
	end catch
end
go

create proc [deleteDiscount]
	@id int
as
begin
	begin try
		delete [Discount] where [id] = @id;
		select * from [Discount] where [id] = 0;
	end try
	begin catch
		select * from [Discount] where [id] = @id;
	end catch
end
go