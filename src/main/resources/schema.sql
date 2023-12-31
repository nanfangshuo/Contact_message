DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `role`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)

);

DROP TABLE IF EXISTS `Contact`;
CREATE TABLE `Contact`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `avatar`      varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `phoneNumber` varchar(255) DEFAULT NULL,
    `userId`      bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `Message`;
CREATE TABLE `Message`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `content`     varchar(255) DEFAULT NULL,
    `fromUserId`  bigint(20) DEFAULT NULL,
    `toUserId`    bigint(20) DEFAULT NULL,
    `fromUserName` varchar(255) DEFAULT NULL,
    `toUserName` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);