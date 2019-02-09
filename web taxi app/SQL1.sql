
use Username;
drop table if exists Login_Information;
Create table Login_Information(
   id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
   username varchar(30),
   password varchar(30),
   login  varchar(5)
   );
   
insert into Login_Information values(null,'lz','123456','false');
insert into Login_Information values(null,'lu','654321','false');
insert into Login_Information values(null,'zheng','123456','false');
select * from login_information;
/*drop TABLE login_information*/
   
drop table if exists Drivers_Information;   
Create table Drivers_Information(
   id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
   username varchar(30),
   password varchar(30),
   login  varchar(5)
   );
insert into Drivers_Information values(null,'lz','123456','false');
insert into Drivers_Information values(null,'zheng','123456','false');

select * from Drivers_Information;
select * from login_information;

update  login_information set login='true' where username='lz';
update Login_Information set login='true';
update  Drivers_Information set login='false' where username='zheng';

delete from  Drivers_Information where password="123456";
