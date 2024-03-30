-- create database MooMooBank;
use MooMooBank;

-- drop table customer;
-- drop table account;
-- drop table tel;
-- drop table info;

create table info(
	idcus varchar(15) primary key check (idcus regexp '^0[0-9]{11}$'), /*USER ID NUMBER (CCCD)*/
    namecus varchar(40),
	sexcus varchar(10),
    birthcus date,
    address varchar(40)
);
insert into info values ('000000000000','TRUONG DANG TRUC LAM','Male','2003-01-30','Can Tho - Viet Nam');
insert into info values ('000000000001','LS','Male','2003-01-30','Soc Trang - Viet Nam');
insert into info values ('000000000002','TOI THANH','Male','2003-4-24','Can Tho - Viet Nam');
insert into info values ('000000000003','LE XUAN THANH','Male','2003-4-24','Ha Noi - Viet Nam');
insert into info values ('000000000004','PHO','Female','1900-01-30','Ho Chi Minh - Viet Nam');
insert into info values ('000000000005','BO ME','Female','2003-12-25','An Giang - Viet Nam');
insert into info values ('000000000006','BO CON','Male','2004-01-01','Hau Giang - Viet Nam');
-- select * from info

create table tel(
	phone varchar(13) primary key check (phone regexp '^0[0-9]{8,10}$'), /*PHONE NUMBER*/
    brand varchar(13),
    money bigint
);
insert into tel values('0907543817','Mobi',69000);
insert into tel values('0907543818','Viettel',96000);
insert into tel values('0907543819','Vina',30000);
-- select * from tel

create table account(
	idacc varchar(15) primary key check (idacc regexp '^[0-9]{10,13}$'), /*ACCOUNT ID: only 10-13 numbers*/
	pass varchar(30) check (pass regexp '(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])((?=.*[!@#$&*])|(?=.*_)).{8,}$'), /* PASSOWORD: at least 8 characters (contain at least 1 letter, 1 number and 1 symbol (not " "))*/ 
    idcus varchar(15) check (idcus regexp '^0[0-9]{11}$'), /*USER ID NUMBER (CCCD)*/
    phone varchar(13) check (phone regexp '^0[0-9]{8,10}$'), /*PHONE NUMBER*/
    pin varchar(7) check (pin regexp'^[0-9]{6}$'),
    constraint fk_idcus foreign key (idcus) references info(idcus),
    constraint fk_phone foreign key (phone) references tel(phone)
);
-- delete from account where idacc = '9907543817';
insert into account values ('9907543817','Suttocdo_007','000000000000','0907543817','666666');
-- select * from account;

create table customer(
	idacc varchar(15) check (idacc regexp '^[0-9]{10,13}$'),
    balance bigint check (balance>=0),
    constraint fk_idacc foreign key (idacc) references account(idacc)
);
insert into customer values('9907543817',10000000);
-- select * from customer;
-- select info.namecus from info inner join account on info.idcus=account.idcus where account.idacc = '9907543817';

-- drop table exacc;
create table exacc(
	exid varchar(20) check (exid regexp '^[0-9]{9,14}$'), /*ACCOUNT ID: only 10-13 numbers*/
    exname varchar(60),
    excomp varchar(40) check (excomp in ('Vietcombank','TP Bank','Agribank','Sacombank','BIDV')),
    exbal bigint,
    primary key (exid, excomp)
);
insert into exacc values('2222222222','SWIM NGOO', 'Vietcombank',100000);
insert into exacc values('3333333333','MOO MOO', 'Vietcombank',100000);
insert into exacc values('4444444444','MOO MOO GIAN DU', 'TP Bank',100000);
insert into exacc values('5555555555','BOA BOA', 'TP Bank',100000);
insert into exacc values('6666666666','CHU BO NGOK NGHEK', 'Agribank',100000);
insert into exacc values('7777777777','MONKEY', 'Sacombank',100000);
insert into exacc values('8888888888','MONGKI', 'Sacombank',100000);
insert into exacc values('9999999999','SWIM NGOO', 'BIDV',100000);
select * from exacc;

-- drop procedure signup;
-- SIGN UP 
DELIMITER $$
create procedure signup( in sp_idacc varchar(15), in sp_pass varchar(30), in sp_idcus varchar(15), in sp_phone varchar(13), in sp_pin varchar(7))
begin  	
	insert into account(idacc, pass, idcus, phone, pin) values (sp_idacc, sp_pass, sp_idcus, sp_phone, sp_pin); 
    insert into customer(idacc, balance) values(sp_idacc, 50000);
end$$
-- call signup('9907543818', 'Suttocdo_007', '000000000001', '0907543818','666666');
-- call signup('9907543819', 'Suttocdo_007', '000000000002', '0907543819','666666');
-- select * from account;

-- drop procedure intransfer;
-- IN TRANSFER
DELIMITER $$
create procedure intransfer( in sp_idsend varchar(15), in sp_idget varchar(15), in sp_money bigint)
begin
	declare send varchar(15) default '1';  
    declare receive varchar(15) default '1';
    select idacc into send from customer where idacc=sp_idsend;
    select idacc into receive from customer where idacc=sp_idget;
    if (send <> '1' AND receive <> '1') 
		then
			update customer set balance = balance - sp_money where idacc=sp_idsend;
			update customer set balance = balance + sp_money where idacc=sp_idget;
    end if;
end$$
-- call intransfer('9907543819','9907543817',10000);
-- select * from customer;

-- drop procedure extransfer;
-- EXTERNAL TRANSFER
DELIMITER $$
create procedure extransfer( in sp_idsend varchar(15), in sp_idget varchar(15), in sp_bankget varchar(15) ,in sp_money bigint)
begin
	declare send varchar(15) default '1';  
    declare receive varchar(15) default '1';
    select idacc into send from customer where idacc=sp_idsend;
    select exid into receive from exacc where exid=sp_idget and excomp=sp_bankget;
    if (send <> '1' AND receive <> '1') 
		then
			update customer set balance = balance - sp_money where idacc=sp_idsend;
			update exacc set exbal = exbal + sp_money where exid=sp_idget;
    end if;
end$$
-- select * from exacc;
-- call extransfer('9907543817','2222222222','Vietcombank',1000000);

-- drop procedure phonecharge;
-- EXTERNAL TRANSFER
DELIMITER $$
create procedure phonecharge( in sp_idsend varchar(15), in sp_idget varchar(15),in sp_money bigint)
begin
	declare send varchar(15) default '1';  
    declare receive varchar(15) default '1';
    select idacc into send from customer where idacc=sp_idsend;
    select phone into receive from tel where phone=sp_idget;
    if (send <> '1' AND receive <> '1') 
		then
			update customer set balance = balance - sp_money where idacc=sp_idsend;
			update tel set money = money + sp_money where phone=sp_idget;
    end if;
end$$
-- select * from tel;
-- call phonecharge ('9907543817','0907543819',100000);

-- drop table historytransfer
create table historytransfer(
	idacc varchar(15),
    money int,
    time varchar(30)
);
-- select * from historytransfer

-- drop trigger triggerhistory
delimiter $$
create trigger triggerhistory
after update on customer
for each row
begin
		insert into historytransfer
		set 
			idacc = new.idacc,
			money = new.balance - old.balance,
			time = sysdate();
end$$

-- drop procedure delacc;
-- DELETE ACCOUNT
DELIMITER $$
create procedure delacc( in sp_idacc varchar(15))
begin
	declare remain long default 1;  
    select balance into remain from customer where idacc=sp_idacc;
    if (remain = 0) 
		then
			delete from customer where idacc = sp_idacc;
			delete from account where idacc = sp_idacc;
    end if;
end$$ 
-- update customer set balance=0 where idacc = '9907543819';
-- call delacc('9907543819');
-- select * from customer;

-- drop role 'admin','manager','employee'';
create role 'admin','manager','employee';

grant all privileges on moomoobank.* to 'admin';

grant select on moomoobank.* to 'manager';
grant all privileges on moomoobank.customer to 'manager';

grant select on moomoobank.customer to 'employee'; 