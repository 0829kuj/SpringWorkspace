CREATE TABLE `mybatis`.`reply` (
  `reply_no` INT NOT NULL AUTO_INCREMENT,
  `reply_bno` INT NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `writer` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP default now() NOT NULL,
  `updated_at` TIMESTAMP default now() NOT NULL,
  PRIMARY KEY (`reply_no`),
  INDEX `reply_bno_idx` (`reply_bno` ASC) VISIBLE,
  CONSTRAINT `reply_bno`
    FOREIGN KEY (`reply_bno`)
    REFERENCES `mybatis`.`board` (`bno`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);

SELECT * FROM mybatis.reply;


CREATE TABLE `mybatis`.`user` (
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`email`)
);

SELECT * FROM mybatis.user;