use username;
drop table if exists message;
create table message(
id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
Customer_name varchar(20),
Driver_name varchar(20),
mark integer,
message varchar(100)
);
insert into message (Customer_name, Driver_name,mark,massage) 
values( (select username from Login_Information where login="true"), (select Driver_name from details where Customer_name=(select username from Login_Information where username="")),"9","very good" );

select username from Login_Information where login="true";
select Driver_name from details where Customer_name=(select username from login_information where login="true");

insert into message (Customer_name, Driver_name,mark,massage) 
values( (select username from Login_Information where login="true"),'','','');

select * from message where Driver_name=(select username from Drivers_Information where login='true');

