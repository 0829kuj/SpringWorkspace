create table demo.myuser(
	idnum int AUTO_INCREMENT PRIMARY KEY,
	id VARCHAR(45) NOT NULL,
	name VARCHAR(45) NOT NULL
);

select * from demo.myuser;

drop table demo.myuser;

insert into demo.myuser(id, name) values("Hong", "홍길동");
insert into demo.myuser(id, name) values("Gang", "강감찬");
insert into demo.myuser(id, name) values("Gea", "계백");
insert into demo.myuser(id, name) values("Song", "송상현");
insert into demo.myuser(id, name) values("Kim", "김좌진");


select * from demo.comment;
drop table demo.comment;

create table demo.comment(
	conum int AUTO_INCREMENT PRIMARY KEY,
	content VARCHAR(200) NOT NULL,
    writer int not null,
    target int not null,
    regdate timestamp default now() not null
);

insert into demo.comment(content, writer, target) values("방명록에 남기는 글귀_상현아안녕", 1, 4);
insert into demo.comment(content, writer, target) values("방명록에 남기는 글귀_감찬아안녕", 1, 2);
insert into demo.comment(content, writer, target) values("방명록에 남기는 글귀_좌진아안녕", 1, 5);
insert into demo.comment(content, writer, target) values("방명록에 남기는 글귀_길동아안녕", 2, 1);
insert into demo.comment(content, writer, target) values("방명록에 남기는 글귀_길동아안뇽안뇽", 3, 1);
insert into demo.comment(content, writer, target) values("방명록에 남기는 글귀_상현아안뇽안뇽", 3, 4);
insert into demo.comment(content, writer, target) values("방명록에 남기는 글귀_길동아안뇽안뇽반갑다!", 5, 1);

select * from comment where target = 1;