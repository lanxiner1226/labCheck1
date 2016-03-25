create table userrelation(
teacher_id int(11) not null,
student_id int(11) not null,
foreign key(teacher_id) references user(id),
foreign key(student_id) references user(id)
)default charset=utf8;