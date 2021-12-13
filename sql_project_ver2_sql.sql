create table studentmanagement.student (
msv varchar (10) primary key,
lastname varchar (50),
firstname varchar (50),
gender varchar (4),
classs varchar (10),
mathspoint float,
physicalpoint float,
chemistrypoint float,	
averagePoint float
);
select * from studentmanagement.student;
insert into studentmanagement.student value ('MSV-001', 'Nguyễn Việt', 'Đạt', 'Nam','CNPM', 8.5, 8.5, 8.5, 8.5);

select msv from studentmanagement.student where msv = 'MSV-01';
select * from studentmanagement.student where msv = 'MSV-001';
update studentmanagement.student SET lastname = 'Phùng Đức' where msv = 'MSV-002';
delete from studentmanagement.student where msv = 'MSV-002';
select * from studentmanagement.student order by firstname asc;

drop table studentmanagement.student;