CREATE SCHEMA miniblog;

drop table `miniblog`.`guestbook`;
CREATE TABLE `miniblog`.`guestbook` (
  `gno` INT NOT NULL AUTO_INCREMENT,
  `mno` INT NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `writer` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`gno`)
);

insert into guestbook(mno, content, writer) values(1, '길동아 안녕안녕~~!!1', '김철수');
insert into guestbook(mno, content, writer) values(1, '길동아 안녕안녕~~!!2', '이철수');
insert into guestbook(mno, content, writer) values(1, '길동아 안녕안녕~~!!3', '박철수');
insert into guestbook(mno, content, writer) values(1, '길동아 안녕안녕~~!!4', '최철수');
insert into guestbook(mno, content, writer) values(1, '길동아 안녕안녕~~!!5', '강철수');
insert into guestbook(mno, content, writer) values(1, '길동아 안녕안녕~~!!6', '공철수');

select * from `miniblog`.`guestbook`;