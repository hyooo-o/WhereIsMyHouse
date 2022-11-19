CREATE TABLE `news` (
	`news_id` int NOT NULL AUTO_INCREMENT,
    `news_title` VARCHAR(100) NOT NULL,
    `news_url` VARCHAR(300) NOT NULL,
    PRIMARY KEY (`news_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
