INSERT INTO User (username, password, role)
VALUES ('defaultUser', 'defaultUser', 'user');
INSERT INTO User (username, password, role)
VALUES ('newUser', 'newUser', 'user');

INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('旺仔1.png', 'defaultName0', 'defaultPhoneNumber0', 1);
INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('旺仔2.png', 'defaultName1', 'defaultPhoneNumber1', 2);
INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('defaultPictrue.png', 'defaultName2', 'defaultPhoneNumber2', 2);
INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('Gundam.png', 'defaultName3', 'defaultPhoneNumber3', 1);

INSERT INTO Message (content, fromUserId, toUserId, fromUserName, toUserName)
VALUES ('你好', 1, 2, 'defaultUser', 'newUser');
INSERT INTO Message (content, fromUserId, toUserId, fromUserName, toUserName)
VALUES ('你也好', 2, 1, 'newUser', 'defaultUser');