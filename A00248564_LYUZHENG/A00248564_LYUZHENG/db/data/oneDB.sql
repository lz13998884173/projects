DROP TABLE students IF EXISTS;
CREATE TABLE students (
Id INTEGER IDENTITY NOT NULL,
Name VARCHAR(32) NOT NULL,
Surname VARCHAR(32) NOT NULL,
Classroom VARCHAR(32) NOT NULL,
Phone VARCHAR(32) NOT NULL,
Email VARCHAR(32) NOT NULL,
Address VARCHAR(95) NOT NULL,
PRIMARY KEY(Id)
);
INSERT INTO students VALUES(NULL,'NAME','SURNAME','CLASSROOM','PHONE','EMAIL','ADDRESS');
INSERT INTO students VALUES(NULL,'Denis','Malahovskij','Engineering','0892488205','denismalahovskij@gmail.com','Beara House Bonavalley Athlone');
INSERT INTO students VALUES(NULL,'Joan','Macgill','Science','0892488205','hytcf@gmail.com','Centre Hill Athlone');
INSERT INTO students VALUES(NULL,'Jim','Mitchell','Business','0892488205','kmjhuyh@gmail.com','Moonksland Athlone');
INSERT INTO students VALUES(NULL,'John','Magner','Humanities','0892488205','gedssed@gmail.com','Greyd yard Athlone');
INSERT INTO students VALUES(NULL,'Jean','Madden','Design','0892488205','htcfre@gmail.com','Balymohon road Athlone');
SELECT * FROM students;