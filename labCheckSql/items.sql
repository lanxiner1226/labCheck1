create table user_record(
id int(11) auto_increment primary key not null,
user_id int(11) not null,
record_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
foreign key(user_id) references user(id)
)default charset=utf8;