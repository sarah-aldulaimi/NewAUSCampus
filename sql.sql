CREATE TABLE users (
	user_ID int auto_increment,
    fname varchar(20) NOT NULL,
    lname varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    phone varchar(20) NOT NULL,
    dept varchar(10) NOT NULL,
    password varchar(50) NOT NULL,
    type int NOT NULL,
    status bool DEFAULT False NOT NULL,
   constraint user_ID primary key (user_ID)
);

INSERT INTO USERS (fname, lname, email, phone, dept, password, type) VALUES ('Khalid', 'Elshafey', 'b00078593@aus.edu', '0501234567', 'CMP', 'khalidpwd', 1);
INSERT INTO USERS (fname, lname, email, phone, dept, password, type) VALUES ('Adham', 'Abdelnaby', 'b00077846@aus.edu', '0507654321', 'CMP', 'adhampwd', 0);
INSERT INTO USERS (fname, lname, email, phone, dept, password, type) VALUES ('Test', 'TEST', 'test@aus.edu', '0507654321', 'COE', 'test', 1);


CREATE TABLE rooms (
room_ID varchar(10),
room_type int NOT NULL,
building varchar(50) NOT NULL,
available bool NOT NULL,
constraint ROOM_ID_PK primary key (ROOM_ID)
);

INSERT INTO ROOMS VALUES ("ESB1010", 1, "Engineering & Science Building", TRUE);
INSERT INTO ROOMS VALUES ("PHY201", 0, "Physics Building", TRUE);

CREATE TABLE AVAIL_COURSES (
CRN int,
course_name varchar(50) NOT NULL,
instructor_ID int  NOT NULL,
constraint CRN_PK primary key (CRN),
constraint prof_id_fk foreign key (instructor_ID) references USERS (user_id)
);
INSERT INTO AVAIL_COURSES VALUES (10000,"Artificial Intelligence", 1);
INSERT INTO AVAIL_COURSES VALUES (20000,"Game Development", 1);
INSERT INTO AVAIL_COURSES VALUES (30000,"Testing Course", 1);
INSERT INTO AVAIL_COURSES VALUES (40000,"WOW Course", 3);

CREATE TABLE reservations (
booking_ID int auto_increment,
course_code int NOT NULL,
user_ID int NOT NULL,
room_ID varchar(10) NOT NULL,
constraint BOOKING_ID_PK primary key (BOOKING_ID),
constraint user_id_fk foreign key (user_ID) references USERS (user_ID),
constraint room_id_fk foreign key (room_ID) references ROOMS (room_ID),
constraint course_id_fk foreign key (course_code) references AVAIL_COURSES (CRN)
);

INSERT INTO RESERVATIONS (course_code, user_ID, room_ID) VALUES (10000, 1, "ESB1010");

CREATE table active_reservations (
room_ID varchar(10) not null,
reserv_date date not null,
time_ID int not null,
reserv_count int not null,
constraint room_date_time_PK primary key (room_ID, reserv_date, time_ID),
constraint active_reservation_room_id_fk foreign key (room_ID) references ROOMS (room_ID),
constraint time_id_fk foreign key (time_ID) references avail_timings (time_ID)
);

CREATE TABLE STUD_COURSES (
stud_id int NOT NULL,
CRN int NOT NULL,
constraint COMPKEY_PK primary key (STUD_ID, CRN),
constraint CRN_fk foreign key (CRN) references avail_courses (CRN),
constraint stud_id_fk foreign key (stud_id) references USERS (user_ID)
);
INSERT INTO STUD_COURSES VALUES (2, 10000);
INSERT INTO STUD_COURSES VALUES (2, 20000);

SELECT * FROM reservations WHERE course_code IN (SELECT CRN FROM STUD_COURSES WHERE stud_id = 2);

CREATE TABLE avail_timings (
time_ID int,
start_time time NOT NULL,
end_time time NOT NULL,
constraint time_id_PK primary key (time_ID)
);

insert into avail_timings values (1, '16:00', '17:00');
insert into avail_timings values (2, '9:00', '10:00');

CREATE TABLE user_proof(
  pic_ID INT NOT NULL auto_increment,
  user_ID INT NOT NULL,
  photo MEDIUMBLOB NOT NULL,
  PRIMARY KEY (pic_ID));