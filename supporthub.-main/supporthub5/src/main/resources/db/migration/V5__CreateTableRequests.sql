CREATE TABLE requests(
request_id bigint NOT NULL AUTO_INCREMENT,
description varchar(255)  NOT NULL,
state varchar(255) NOT NULL,
created_on DATE NOT NULL ,
location_id bigint,
service_id bigint,
user_id bigint,
PRIMARY KEY (request_id),
FOREIGN KEY (location_id) REFERENCES location(location_id),
FOREIGN KEY (service_id) REFERENCES service(service_id),
FOREIGN KEY (user_id) REFERENCES user(id)
);