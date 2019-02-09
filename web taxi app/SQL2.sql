
use username;
drop table if exists details;
create table details(
id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
Customer_name varchar(20),
current_city varchar(50),
Current_location varchar(50),
Destination varchar(50),
Driver_name varchar(20),
Price  float,
statu varchar(20)
);
select * from details ;
insert into details(current_city,current_location,destination,statu) values('atnlone','rock','willow','undone');
insert into details values(null,'lz','athlone','center','dublin','jim',40,'undone');
insert into details values(null,'lyu@qq.com','athlone','center','dublin','zu@123.com','400','undone');
insert into details(customer_name,current_city,current_location,destination,statu) 
select username ,"athlone","center","dublin","undone"from login_information where login='true' ;
select Customer_name,current_city,Current_location,Destination from details where statu="undone";

insert into details values(null,'lz@qq.com','athlone','ait','dublin','xxq@qq.com',30,'undone');

update details set Driver_name=(select username from Drivers_Information where login="true"), Price=7.0,statu="done" where Customer_name="lz";
update details set Driver_name=null,Price=null,statu="undone";
update details set Price=90.0 where Customer_name="lyu@qq.com";
select * from details where Customer_name=(select username from Login_Information where login="true") ;

update details set statu="done" where Driver_name="xx" and statu="undone" and Customer_name=(select Customer_name from login_information where login="true");