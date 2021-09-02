CREATE TABLE user(
id bigint(20) NOT NULL AUTO_INCREMENT,
email_id varchar(50) NOT NULL,
first_name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
password varchar(255) NOT NULL,
phone_no varchar(255) NOT NULL,
role varchar(255) NOT NULL,
user_name varchar(255) NOT NULL,
PRIMARY KEY (id),
 UNIQUE KEY UK_username (user_name)
);