DROP TABLE IF EXISTS user_order;
DROP TABLE IF EXISTS consideration;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN', 'USER', 'BLACKLISTED') NOT NULL,
  `birthdate` date NOT NULL,
  `job` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `pseudo` varchar(255) DEFAULT NULL,
  `subscribe_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
);
CREATE TABLE project (
  id int(11) NOT NULL AUTO_INCREMENT,
  category enum(
    'TOURDATE',
    'EP',
    'CD',
    'EVENT',
    'INSTRUMENT',
    'COMMUNICATION'
  ) NOT NULL,
  date_end date NOT NULL,
  date_init date NOT NULL,
  description tinytext DEFAULT NULL,
  name varchar(255) NOT NULL,
  photo varchar(255) DEFAULT NULL,
  status enum(
    'INPROGRESS',
    'CONCEPTION',
    'BLACKLISTED',
    'PAUSED'
  ) NOT NULL,
  user_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKo06v2e9kuapcugnyhttqa1vpt (user_id),
  CONSTRAINT FKo06v2e9kuapcugnyhttqa1vpt FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
CREATE TABLE consideration (
  id int(11) NOT NULL AUTO_INCREMENT,
  consid_amount int(10) unsigned NOT NULL,
  description tinytext DEFAULT NULL,
  photo varchar(255) DEFAULT NULL,
  title varchar(255) NOT NULL,
  project_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKimeom8mttfiynorty0y8xt5lx (project_id),
  CONSTRAINT FKimeom8mttfiynorty0y8xt5lx FOREIGN KEY (project_id) REFERENCES project (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
CREATE TABLE user_order (
  id int(11) NOT NULL AUTO_INCREMENT,
  amount double unsigned NOT NULL,
  date date NOT NULL,
  project_id int(11) DEFAULT NULL,
  user_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK1wqvsjpqw0y60tqxfkwva4p32 (project_id),
  KEY FKj86u1x7csa8yd68ql2y1ibrou (user_id),
  CONSTRAINT FK1wqvsjpqw0y60tqxfkwva4p32 FOREIGN KEY (project_id) REFERENCES project (id),
  CONSTRAINT FKj86u1x7csa8yd68ql2y1ibrou FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;