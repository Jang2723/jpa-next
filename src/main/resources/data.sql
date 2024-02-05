INSERT INTO instructor (name)
VALUES
    ('Vivien Moran'),
    ('Tanisha Gordon'),
    ('Sharon Harrell'),
    ('Christopher Mcclain'),
    ('Edan Brian'),
    ('Plato Best'),
    ('Uriah Nguyen'),
    ('Jesse Romero'),
    ('Nehru Huff'),
    ('Jolene Roach');


INSERT INTO student (name,age,phone,email,advisor_id)
VALUES
    ('Deirdre Mclean',36,'010-2453-6183','proin.velit@yahoo.couk',null),
    ('Rebecca Ayala',37,'010-8356-8511','in.tempus@protonmail.org',3),
    ('Griffin Joyner',31,'010-5568-0941','in.ornare@icloud.net',4),
    ('Karina Reynolds',38,'010-7756-2174','id.sapien@outlook.net',3),
    ('Ryan Burgess',36,'010-4341-0548','ultrices.iaculis.odio@protonmail.net',9),
    ('Beck Mack',23,'010-5758-9647','in.magna@yahoo.couk',6),
    ('Rooney Beasley',32,'010-2702-3193','lobortis@google.edu',9),
    ('Kelly Nguyen',33,'010-4211-0843','commodo@aol.org',7),
    ('Demetria Barton',23,'010-7823-0136','pellentesque.habitant.morbi@aol.net',7),
    ('Hedy Hardy',24,'010-1763-3441','eu.ligula@protonmail.net',10);
INSERT INTO student (name,age,phone,email,advisor_id)
VALUES
    ('Risa West',25,'010-9586-4657','in.faucibus@protonmail.com',4),
    ('Dillon Cardenas',25,'010-1681-6456','augue.ut@aol.edu',4),
    ('Isaiah Kirby',30,'010-6346-4650','vitae@protonmail.edu',3),
    ('Kareem Flowers',38,'010-9613-6581','a.auctor@outlook.edu',null),
    ('Tiger Hardin',25,'010-9275-9244','et.magnis@yahoo.net',5),
    ('Orson French',39,'010-1013-8332','enim.commodo.hendrerit@outlook.edu',6),
    ('Isaiah Schroeder',21,'010-0604-1786','orci.lacus@protonmail.ca',4),
    ('Jaime Fischer',38,'010-8928-6221','primis.in.faucibus@icloud.couk',5),
    ('Jin Strong',37,'010-2588-2713','hendrerit.donec.porttitor@hotmail.org',8),
    ('Lars Whitaker',21,'010-3347-5135','in.consequat@yahoo.edu',4);
INSERT INTO student (name,age,phone,email,advisor_id)
VALUES
    ('Cairo Baxter',38,'010-1866-1418','magna.cras.convallis@yahoo.com',3),
    ('Wynne Lucas',27,'010-8272-2451','varius.ultrices@google.com',3),
    ('Ian Rowland',34,'010-5366-1711','proin.non@yahoo.net',5),
    ('Hoyt Nieves',38,'010-1779-3633','molestie.sed@outlook.couk',7),
    ('Ethan Francis',23,'010-6152-7722','at@icloud.edu',3),
    ('George Coffey',31,'010-5144-8473','rutrum.eu.ultrices@yahoo.com',10),
    ('Derek Burton',38,'010-3163-4466','lectus.pede@icloud.com',7),
    ('Cullen Cardenas',38,'010-3278-0155','arcu.imperdiet.ullamcorper@hotmail.edu',10),
    ('Zelenia Mcleod',32,'010-0493-6954','lorem.donec@google.edu',4),
    ('Logan Jimenez',37,'010-3858-1106','sapien.aenean@outlook.couk',null);

INSERT INTO lecture (start_time,end_time,name,day,instructor_id)
VALUES
    (9,15,'nec','tue',6),
    (10,13,'Cum sociis','mon',5),
    (11,15,'Sed','thu',5),
    (11,14,'Phasellus in','mon',4),
    (10,14,'Sed','wed',1),
    (10,14,'at','thu',2),
    (10,15,'penatibus et','fri',9),
    (10,13,'aliquam','wed',5),
    (11,13,'vitae','fri',6),
    (11,14,'dolor.','wed',5);
INSERT INTO lecture (start_time,end_time,name,day,instructor_id)
VALUES
    (15,16,'enim','sat',8),
    (13,15,'mollis.','fri',7),
    (12,16,'urna','tue',5),
    (12,18,'purus, accumsan','tue',2),
    (14,16,'tempus mauris','fri',1),
    (13,15,'lobortis','fri',2),
    (13,18,'nisl arcu','fri',9),
    (13,16,'ultrices','wed',4),
    (14,15,'adipiscing','fri',8),
    (12,15,'libero.','mon',2);
INSERT INTO lecture (start_time,end_time,name,day,instructor_id)
VALUES
    (11,15,'euismod','mon',4),
    (9,14,'Cras eu','tue',8),
    (10,15,'pharetra.','sat',9),
    (11,14,'sit','thu',2),
    (11,14,'orci, consectetuer','tue',1),
    (10,13,'mi.','mon',6),
    (11,15,'egestas','mon',6),
    (10,14,'ligula','thu',6),
    (10,13,'arcu imperdiet','thu',5),
    (11,15,'pede blandit','tue',9);

INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
    (11,29),
    (25,6),
    (23,21),
    (14,27),
    (6,3),
    (23,16),
    (12,4),
    (13,30),
    (4,5),
    (15,23);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
    (6,7),
    (10,17),
    (29,19),
    (14,11),
    (17,21),
    (16,4),
    (17,1),
    (21,10),
    (28,2),
    (27,26);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
    (24,3),
    (6,24),
    (8,29),
    (18,25),
    (15,5),
    (14,18),
    (8,4),
    (15,25),
    (9,12),
    (16,18);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
    (14,25),
    (13,25),
    (23,12),
    (18,23),
    (17,20),
    (9,28),
    (6,6),
    (9,5),
    (11,23),
    (2,26);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
    (25,20),
    (15,28),
    (20,8),
    (13,14),
    (15,8),
    (8,21),
    (8,20),
    (3,7),
    (14,10),
    (24,5);