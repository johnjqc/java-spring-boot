
DROP SEQUENCE IF EXISTS message_id;
CREATE SEQUENCE message_id;

DROP TABLE IF EXISTS message;
CREATE TABLE message (
    id int default message_id.nextval PRIMARY KEY,
    message VARCHAR(250) NOT NULL
);

DROP SEQUENCE IF EXISTS user_id;
CREATE SEQUENCE user_id;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id int default user_id.nextval PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL

);