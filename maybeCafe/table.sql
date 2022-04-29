CREATE SCHEMA maybecafe;

# 공부게시판 테이블
create table maybecafe.studyboard(
    sno int auto_increment primary key,
    title varchar(150) not null,
    content varchar(2000) not null,
    writer varchar(50) not null,
    regdate timestamp default now() not null,
    updatedate timestamp default now() not null,
    hit int not null,
    like_no int not null
);

insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목1', '테스트 내용1', '작가1', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목2', '테스트 내용2', '작가2', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목3', '테스트 내용3', '작가3', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목4', '테스트 내용4', '작가4', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목5', '테스트 내용5', '작가5', 0, 0);

select * from studyboard;

select * from studyboard WHERE sno = 1;

# 유저테이블

# 가입인사 테이블
create table maybecafe.greetboard(
    gno int auto_increment primary key,
    content varchar(1000) not null,
    writer varchar(50) not null,
    regdate timestamp default now() not null
);
select * from greetboard;

INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......1', '작성자1');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......2', '작성자2');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......3', '작성자3');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......4', '작성자4');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......5', '작성자5');

SELECT * FROM greetboard ORDER BY gno DESC;