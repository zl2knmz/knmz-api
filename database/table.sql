CREATE TABLE `knmz_user` (
  `account` varchar(30) NOT NULL,
  `logo` varchar(256) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `nick` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `real_name` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `brief` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `status` INT(10) DEFAULT NULL COMMENT '用户状态：1-正常',
  `jpush_id` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`account`),
  KEY `IDX_HU_EMAIL` (`email`),
  KEY `IDX_HU_PHONE` (`phone`),
  KEY `IDX_HU_CREATE_DATE` (`create_date`),
  KEY `IDX_HU_UPDATE_DATE` (`update_date`),
  KEY `IDX_HU_NICK` (`nick`),
  KEY `IDX_HU_REALNAME` (`real_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

USE `springbootdb`;

insert  into `knmz_user`(`account`,`logo`,`password`,`nick`,`real_name`,`brief`,`email`,`phone`,`status`,`jpush_id`,`create_date`,`update_date`) values
('123456789',NULL,'11038837210188E3B3','knmz','knmz',NULL,'123456@qq.com','+8615018513737',1,NULL,'2019-11-08 17:54:07','2019-11-08 17:54:12');
