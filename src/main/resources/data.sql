INSERT INTO User (username, password, role)
VALUES ('admin', 'admin', 'admin');
INSERT INTO User (username, password, role)
VALUES ('defaultUser', 'defaultUser', 'user');
INSERT INTO User (username, password, role)
VALUES ('newUser', 'newUser', 'user');

INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('zf.png', 'defaultName', 'defaultPhoneNumber', 2);
INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('zhy.png', 'defaultNam2', 'defaultPhoneNumber', 3);
INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('zpc1.png', 'defaultNam3', 'defaultPhoneNumber', 3);
INSERT INTO Contact (avatar, name, phoneNumber, userId)
VALUES ('zpc2.png', 'defaultNam4', 'defaultPhoneNumber', 2);

INSERT INTO Message (content, fromUserId, toUserId, fromUserName, toUserName)
VALUES ('你好', 2, 3, 'defaultUser', 'newUser');
INSERT INTO Message (content, fromUserId, toUserId, fromUserName, toUserName)
VALUES ('你也好', 3, 2, 'newUser', 'defaultUser');