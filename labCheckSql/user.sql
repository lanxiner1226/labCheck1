create table user(
id int(11) auto_increment primary key not null,
userName varchar(20) not null,
pwd varchar(100) not null,
name varchar(20) not null,
phonecode varchar(100),
address_id int(11),
role int(2),
foreign key(address_id) references user_location(id)
)default charset=utf8;
insert into user values(null,'100000','123456','admin',null,1,3);