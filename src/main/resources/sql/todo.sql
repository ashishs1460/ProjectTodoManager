CREATE DATABASE todo;

USER DATABASE todo;

CREATE TABLE
  `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci

CREATE TABLE
  `project` (
    `id` int NOT NULL AUTO_INCREMENT,
    `created_date` date DEFAULT NULL,
    `title` varchar(255) DEFAULT NULL,
    `user_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`),
    CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
  ) ENGINE = InnoDB AUTO_INCREMENT = 12 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci 
CREATE TABLE
  `todo` (
    `id` int NOT NULL AUTO_INCREMENT,
    `created_date` date DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `status` varchar(255) DEFAULT NULL,
    `updated_date` date DEFAULT NULL,
    `project_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKar5jun3w6snk3ymjm3uth2w34` (`project_id`),
    CONSTRAINT `FKar5jun3w6snk3ymjm3uth2w34` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
  ) ENGINE = InnoDB AUTO_INCREMENT = 15 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci