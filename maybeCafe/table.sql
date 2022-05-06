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
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목1', '테스트 내용6', '작가6', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목2', '테스트 내용7', '작가7', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목3', '테스트 내용8', '작가8', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목4', '테스트 내용9', '작가9', 0, 0);
insert into studyboard(title, content, writer, hit, like_no) values ('테스트 제목5', '테스트 내용10', '작가10', 0, 0);

select * from studyboard ORDER BY sno DESC;

select * from studyboard WHERE sno = 1;
SELECT count(*) FROM studyboard;


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
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......6', '작성자6');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......7', '작성자7');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......8', '작성자8');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......9', '작성자9');
INSERT INTO greetboard(content, writer) VALUES ('가입인사입니다.......10', '작성자10');

SELECT * FROM greetboard ORDER BY gno DESC;


# 유저 테이블
CREATE TABLE IF NOT EXISTS users (
	uno int not null auto_increment,
	uid VARCHAR(45) not null,
    password VARCHAR(255) not null,
    email VARCHAR(45) not null,
    username VARCHAR(45) not null,
    phone_number VARCHAR(45) not null,
    profile_img VARCHAR(45),
    profile_description VARCHAR(1000),
	PRIMARY KEY (uno)
);
SELECT * FROM maybecafe.users;

insert into users(uid, password, email, username, phone_number)
values ('hong', '1234', 'hong@gmail.com', '홍길동', '010-1111-1111');


# 관리자 테이블
CREATE TABLE IF NOT EXISTS admin (
	id int not null auto_increment,
    username VARCHAR(45) not null,
    password VARCHAR(255) not null,
	PRIMARY KEY (id)
);