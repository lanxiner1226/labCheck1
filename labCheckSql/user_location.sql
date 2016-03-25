create table user_location(
 id int(11) auto_increment primary key not null,
 location varchar(100),
 note1 varchar(50),
 note2 varchar(50)
)default charset=utf8;

insert into user_location values(null,"杭州电子科技大学603实验室",null,null);