CREATE TABLE IF NOT EXISTS mybatis.user (
	id int not null,
    name VARCHAR(45) not null,
    phone VARCHAR(45) not null,
    address VARCHAR(200) not null,
	PRIMARY KEY (id)
);

insert into mybatis.user(id, name, phone, address)
values (1, '홍길동', '010-1111-1111', '부산진구');
insert into mybatis.user(id, name, phone, address)
values (2, '강감찬', '010-2222-2222', '부산시 중구');
insert into mybatis.user(id, name, phone, address)
values (3, '계백', '010-3333-3333', '서울시 강서구');
insert into mybatis.user
values (4, '이순신', '010-4444-4444', '서울시 북구');

DROP TABLE IF EXISTS Products;
CREATE TABLE Products
(
    prod_id     INT     		PRIMARY KEY AUTO_INCREMENT,
    prod_name   VARCHAR(255)    NOT NULL,
    prod_price  INT             NOT NULL
);

INSERT INTO Products (prod_name, prod_price) values ('베베숲 물티슈', 2700);
INSERT INTO Products (prod_name, prod_price) values ('여름 토퍼', 35180);
INSERT INTO Products (prod_name, prod_price) values ('페이크 삭스', 860);
INSERT INTO Products (prod_name, prod_price) values ('우산', 2900);

DROP TABLE IF EXISTS board;
create table board(
    bno int auto_increment primary key,
    title varchar(150) not null,
    content varchar(2000) not null,
    writer varchar(50) not null,
    regdate timestamp default now() not null,
    updatedate timestamp default now() not null
);

insert into board(title, content, writer) values ('테스트 제목1', '테스트 내용1', '작가1');
insert into board(title, content, writer) values ('테스트 제목2', '테스트 내용2', '작가2');
insert into board(title, content, writer) values ('테스트 제목3', '테스트 내용3', '작가3');

# 처음부터 3개 건너뛴 후 4개 출력
SELECT * FROM board LIMIT 3, 4;