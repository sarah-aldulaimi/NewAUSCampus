CREATE TABLE users (
	AUS_ID integer,
    user_name varchar(20) NOT NULL,
    pass_word varchar(20) NOT NULL,
    full_name varchar(50) NOT NULL,
    vacc_status bool,
    user_type int,
   constraint AUS_ID_PK primary key (AUS_ID)
   
);

INSERT INTO USERS VALUES(77846,"b00077846","123","Adham Abdelnaby",True,0);
INSERT INTO USERS VALUES(78593,"b00078593","123","Khalid Alshafey",True,1);

CREATE TABLE rooms (
room_ID varchar(10),
room_type int NOT NULL,
building varchar(50) NOT NULL,
available bool NOT NULL,
constraint ROOM_ID_PK primary key (ROOM_ID)
);

INSERT INTO ROOMS VALUES ("ESB1010",1,"Engineering & Science Building",TRUE);
INSERT INTO ROOMS VALUES ("PHY201",0,"Physics Building",FALSE);

CREATE TABLE AVAIL_COURSES (
CRN int,
course_name varchar(50) NOT NULL,
course_inst varchar(20) NOT NULL,
instructor_ID int  NOT NULL,
constraint CRN_PK primary key (CRN),
constraint prof_id_fk foreign key (instructor_ID) references USERS (aus_id)
);

INSERT INTO AVAIL_COURSES VALUES (10000,"Artificial Intelligence","Khalid Al Shafey",78593);
INSERT INTO AVAIL_COURSES VALUES (20000,"Game Development","Khalid Al Shafey",78593);

CREATE TABLE reservations (
booking_ID int auto_increment,
course_code int NOT NULL,
user_ID int NOT NULL,
room_ID varchar(10) NOT NULL,
constraint BOOKING_ID_PK primary key (BOOKING_ID),
constraint user_id_fk foreign key (user_ID) references USERS (aus_id),
constraint room_id_fk foreign key (room_ID) references ROOMS (room_ID),
constraint course_id_fk foreign key (course_code) references AVAIL_COURSES (CRN)
);
select * from reservations;
CREATE table active_reservations (
room_ID varchar(10) not null,
reserv_date date not null,
time_ID int not null,
reserv_count int not null,
constraint room_date_time_PK primary key (room_ID, reserv_date, time_ID),
constraint active_reservation_room_id_fk foreign key (room_ID) references ROOMS (room_ID),
constraint time_id_fk foreign key (time_ID) references avail_timings (time_ID)
);


INSERT INTO RESERVATIONS VALUES (1,10000,78593,"ESB1010");

CREATE TABLE STUD_COURSES (
stud_id int NOT NULL,
CRN int NOT NULL,
constraint COMPKEY_PK primary key (STUD_ID,CRN),
constraint stud_id_fk foreign key (stud_id) references USERS (aus_id)
);
INSERT INTO STUD_COURSES VALUES (77846,10000);
INSERT INTO STUD_COURSES VALUES (77846,20000);

SELECT * FROM reservations WHERE course_code IN (SELECT CRN FROM STUD_COURSES WHERE stud_id = 77846);

CREATE TABLE avail_timings (
time_ID int,
start_time time NOT NULL,
end_time time NOT NULL,
constraint time_id_PK primary key (time_ID)
);

insert into avail_timings values (1, '16:00', '17:00');
insert into avail_timings values (2, '9:00', '10:00');
select date_format(start_time, '%H:%i') as start_time from avail_timings;
select * from active_reservations