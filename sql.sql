
CREATE TABLE users (
	user_ID int auto_increment,
    fname varchar(20) NOT NULL,
    lname varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    phone varchar(20) NOT NULL,
    dept varchar(10) NOT NULL,
    password varchar(50) NOT NULL,
    type int NOT NULL,
    status bool DEFAULT FALSE NOT NULL,
   constraint user_ID primary key (user_ID)
);
INSERT INTO USERS (fname, lname, email, phone, dept, password, type) VALUES ('Khalid', 'Elshafey', 'b00078593@aus.eduavail_courses', '0501234567', 'CMP', 'khalidpwd', 1);
INSERT INTO USERS (fname, lname, email, phone, dept, password, type) VALUES ('Adham', 'Abdelnaby', 'b00077846@aus.edu', '0507654321', 'CMP', 'adhampwd', 0);
INSERT INTO USERS (fname, lname, email, phone, dept, password, type) VALUES ('Test', 'TEST', 'test@aus.edu', '0507654321', 'COE', 'test', 1);
INSERT INTO USERS (fname, lname, email, phone, dept, password, type) VALUES ('Khalid', 'ElFakih', 'kelfakihuser_proof@aus.edu', '0503073091', 'COE', '123', 2);

select * from users;
CREATE TABLE rooms (
room_ID varchar(10),
room_type int NOT NULL,
building varchar(50) NOT NULL,
available bool NOT NULL,
constraint ROOM_ID_PK primary key (ROOM_ID)
);

INSERT INTO ROOMS VALUES ("ESB1010", 1, "Engineering & Science Building", TRUE);
INSERT INTO ROOMS VALUES ("PHY201", 0, "Physics Building", TRUE);
insert into rooms (room_ID, room_type, building, available) values ('ESB1011', 0, 'Engineering & Science Building', false);
insert into rooms (room_ID, room_type, building, available) values ('PHY202', 0, 'Physics Building', true);
INSERT INTO ROOMS VALUES ("ESB1015", 1, "Engineering & Science Building", TRUE);
INSERT INTO ROOMS VALUES ("LABTEST", 0, "Engineering & Science Building", TRUE);
INSERT INTO ROOMS VALUES ("ROOMTEST", 1, "Engineering & Science Building", TRUE);
INSERT INTO ROOMS VALUES ("AUS", 0, "Engineering & Science Building", TRUE);
INSERT INTO ROOMS VALUES ("AUSR", 1, "Engineering & Science Building", TRUE);


CREATE TABLE AVAIL_COURSES (
CRN int,
course_name varchar(50) NOT NULL,
instructor_ID int  NOT NULL,
constraint CRN_PK primary key (CRN),
constraint prof_id_fk foreign key (instructor_ID) references USERS (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO AVAIL_COURSES VALUES (10000,"Artificial Intelligence", 1);
INSERT INTO AVAIL_COURSES VALUES (20000,"Game Development", 1);
INSERT INTO AVAIL_COURSES VALUES (30000,"Testing Course", 1);
INSERT INTO AVAIL_COURSES VALUES (40000,"WOW Course", 3);

CREATE TABLE pending_reservations (
booking_ID int auto_increment,
course_code int NOT NULL,
user_ID int NOT NULL,
room_ID varchar(10) NOT NULL,
reserv_date date not null,
time_ID int not null,
processed boolean DEFAULT FALSE not null,
constraint prv_BOOKING_ID_PK primary key (BOOKING_ID),
constraint prv_user_id_fk foreign key (user_ID) references USERS (user_ID)  ON DELETE CASCADE ON UPDATE CASCADE,
constraint prv_room_id_fk foreign key (room_ID) references ROOMS (room_ID)  ON DELETE CASCADE ON UPDATE CASCADE,
constraint prv_course_id_fk foreign key (course_code) references AVAIL_COURSES (CRN)  ON DELETE CASCADE ON UPDATE CASCADE,
constraint ptv_time_id_fk foreign key (time_ID) references avail_timings (time_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (10000, 1, "ESB1010",'2021-10-05',1);
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 2, "PHY201",'2022-10-05',2);

INSERT INTO RESERVATIONS (course_code, user_ID, room_ID) VALUES (20000, 1, "ESB1011");

CREATE table active_reservations (
booking_ID int auto_increment,
course_code int NOT NULL,
user_ID int NOT NULL,
room_ID varchar(10) NOT NULL,
reserv_date date not null,
time_ID int not null,
constraint atv_BOOKING_ID_PK primary key (BOOKING_ID),
constraint atv_user_id_fk foreign key (user_ID) references USERS (user_ID)  ON DELETE CASCADE ON UPDATE CASCADE,
constraint atv_room_id_fk foreign key (room_ID) references ROOMS (room_ID)  ON DELETE CASCADE ON UPDATE CASCADE,
constraint atv_course_id_fk foreign key (course_code) references AVAIL_COURSES (CRN)  ON DELETE CASCADE ON UPDATE CASCADE,
constraint atv_time_id_fk foreign key (time_ID) references avail_timings (time_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (10000, 1, "ESB1010",'2021-10-05',1);
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 2, "PHY201",'2022-10-05',2);
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (30000, 1, "ESB1015",'2022-05-05',12);
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 2, "PHY201",'2023-10-05',2);
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 1, "PHY201",'2024-10-05',2);
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 5, "PHY201",'2024-10-05',11);
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 2, "PHY201",'2024-10-05',11);

INSERT INTO active_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (10000, 1, "ESB1010",'2021-10-05',1);
INSERT INTO active_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 2, "PHY201",'2022-10-05',2);
INSERT INTO active_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (30000, 1, "ESB1015",'2022-05-05',12);
INSERT INTO active_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 2, "PHY201",'2023-10-05',2);
INSERT INTO active_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 1, "PHY201",'2024-10-05',2);
INSERT INTO active_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 5, "PHY201",'2024-10-05',11);
CREATE TABLE STUD_COURSES (
stud_id int NOT NULL,
CRN int NOT NULL,
constraint COMPKEY_PK primary key (STUD_ID, CRN),
constraint CRN_fk foreign key (CRN) references avail_courses (CRN)  ON DELETE CASCADE ON UPDATE CASCADE,
constraint stud_id_fk foreign key (stud_id) references USERS (user_ID)  ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO STUD_COURSES VALUES (2, 10000);
INSERT INTO STUD_COURSES VALUES (2, 20000);
INSERT INTO STUD_COURSES VALUES (2, 30000);
select booking_ID , course_code, RESERVATIONS.user_ID, fname,room_ID FROM reservations,users 
WHERE course_code IN (SELECT CRN FROM STUD_COURSES WHERE stud_id = 2) AND RESERVATIONS.user_ID=users.user_ID;

CREATE TABLE avail_timings (
time_ID int,
start_time time NOT NULL,
end_time time NOT NULL,
constraint time_id_PK primary key (time_ID)
);

insert into avail_timings values (1, '8:00', '9:00');
insert into avail_timings values (2, '9:00', '10:00');
insert into avail_timings values (3, '10:00', '11:00');
insert into avail_timings values (4, '11:00', '12:00');
insert into avail_timings values (5, '12:00', '13:00');
insert into avail_timings values (6, '13:00', '14:00');
insert into avail_timings values (7, '14:00', '15:00');
insert into avail_timings values (8, '15:00', '16:00');
insert into avail_timings values (9, '16:00', '17:00');
insert into avail_timings values (10, '8:00', '10:00');
insert into avail_timings values (11, '11:00', '13:00');
insert into avail_timings values (12, '14:00', '16:00');

CREATE TABLE user_proof(
  pic_ID INT NOT NULL auto_increment,
  user_ID INT NOT NULL,
  photo MEDIUMBLOB NOT NULL,
  PRIMARY KEY (pic_ID),
  constraint user_proof_id_fk foreign key (user_ID) references USERS (user_ID) ON DELETE CASCADE ON UPDATE CASCADE);

select  booking_ID , course_code, ACTIVE_RESERVATIONS.user_ID, CONCAT(fname,' ',lname) AS fname,
ACTIVE_RESERVATIONS.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, 
room_type AS BOOKING_TYPE FROM ACTIVE_RESERVATIONS,users,avail_timings,rooms
 WHERE course_code IN (SELECT CRN FROM STUD_COURSES WHERE stud_id = 1) 
 AND ACTIVE_RESERVATIONS.user_ID=users.user_ID AND ACTIVE_RESERVATIONS.time_ID=avail_timings.time_ID 
 AND ACTIVE_RESERVATIONS.room_ID=rooms.room_ID;
 
 select  booking_ID , course_code, ACTIVE_RESERVATIONS.user_ID, CONCAT(fname,' ',lname) AS fname,
ACTIVE_RESERVATIONS.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, 
room_type AS BOOKING_TYPE FROM ACTIVE_RESERVATIONS,users,avail_timings,rooms
 WHERE course_code IN (SELECT CRN FROM STUD_COURSES WHERE stud_id = 1) 
 AND ACTIVE_RESERVATIONS.user_ID=users.user_ID AND ACTIVE_RESERVATIONS.time_ID=avail_timings.time_ID 
 AND ACTIVE_RESERVATIONS.room_ID=rooms.room_ID AND
 (users.type=1 OR active_reservations.user_ID IN (select distinct stud_id from STUD_courses where stud_id=1));


select  booking_ID , course_code, ACTIVE_RESERVATIONS.user_ID, CONCAT(fname,' ',lname) AS fname,
ACTIVE_RESERVATIONS.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, 
room_type AS BOOKING_TYPE FROM ACTIVE_RESERVATIONS,users,avail_timings,rooms
 WHERE course_code IN (SELECT CRN FROM avail_courses WHERE instructor_ID = 1) 
 AND ACTIVE_RESERVATIONS.user_ID=users.user_ID AND ACTIVE_RESERVATIONS.time_ID=avail_timings.time_ID 
 AND ACTIVE_RESERVATIONS.room_ID=rooms.room_ID AND users.type=1;
 
 select  booking_ID , course_code, ACTIVE_RESERVATIONS.user_ID, CONCAT(fname,' ',lname) AS fname,
ACTIVE_RESERVATIONS.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, 
room_type AS BOOKING_TYPE FROM ACTIVE_RESERVATIONS,users,avail_timings,rooms
 WHERE course_code IN (SELECT CRN FROM avail_courses WHERE instructor_ID = 1) 
 AND ACTIVE_RESERVATIONS.user_ID=users.user_ID AND ACTIVE_RESERVATIONS.time_ID=avail_timings.time_ID 
 AND ACTIVE_RESERVATIONS.room_ID=rooms.room_ID AND users.type=1;
 
select  booking_ID , course_code, pending_reservations.user_ID, CONCAT(fname,' ',lname) AS fname,
users.type,pending_reservations.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, 
room_type AS BOOKING_TYPE,users.status FROM pending_reservations,users,avail_timings,rooms WHERE pending_reservations.user_ID=users.user_ID AND pending_reservations.time_ID=avail_timings.time_ID 
 AND pending_reservations.room_ID=rooms.room_ID AND pending_reservations.processed=FALSE;
 
 
 select  booking_ID , course_code, active_reservations.user_ID, CONCAT(fname,' ',lname) AS fname,
users.type,active_reservations.room_ID, reserv_date,date_format(start_time,'%H:%i') AS start_time,date_format(end_time,'%H:%i') AS end_time, 
room_type AS BOOKING_TYPE FROM active_reservations,users,avail_timings,rooms WHERE active_reservations.user_ID=users.user_ID AND active_reservations.time_ID=avail_timings.time_ID 
 AND active_reservations.room_ID=rooms.room_ID;
 
SELECT COUNT(booking_ID) AS capacity
FROM pending_reservations
WHERE  reserv_date ='2024-10-05' AND time_ID=11 AND room_ID='PHY201';
INSERT INTO pending_reservations (course_code, user_ID, room_ID,reserv_date,time_ID) VALUES (20000, 2, "PHY201",'2024-10-05',11);


 