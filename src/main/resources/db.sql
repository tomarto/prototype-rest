CREATE TABLE User (
	username VARCHAR(20) PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	password VARCHAR(20) NOT NULL,
	email VARCHAR(50),
	birth_date DATE,
	enabled BOOLEAN DEFAULT TRUE NOT NULL
);

INSERT INTO User VALUES('rafael.ortiz', 'Rafael', 'Ortiz', '123456', 'rafael.ortiz@test.com', TO_DATE('11-23-1990', 'MM-DD-YYYY'), TRUE);


CREATE TABLE User_roles (
	user_role_id INTEGER IDENTITY,
	username VARCHAR(20) NOT NULL,
	role VARCHAR(45) NOT NULL,
	CONSTRAINT fk_username FOREIGN KEY(username) REFERENCES User(username)
);

CREATE UNIQUE INDEX uni_username_role ON User_roles(role, username);
INSERT INTO User_roles(username, role) VALUES('rafael.ortiz', 'ROLE_ADMIN');


CREATE TABLE Action (
	id INTEGER PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	created_date DATE NOT NULL,
	due_date DATE NOT NULL
);

INSERT INTO Action VALUES('1', 'Action 1', TO_DATE('02-23-2015', 'MM-DD-YYYY'), TO_DATE('08-13-2015', 'MM-DD-YYYY'));
INSERT INTO Action VALUES('2', 'Action 2', TO_DATE('08-14-2015', 'MM-DD-YYYY'), TO_DATE('12-31-2015', 'MM-DD-YYYY'));
