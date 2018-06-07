drop table member;

create table member(
	id varchar2(15),
	password varchar2(15),
	nickname varchar2(15) unique,
	email varchar2(30),
	emailcheck varchar2(10),
	phone varchar2(20),
	post varchar2(15),
	address varchar2(300),
	detailaddress varchar2(300),
	latitude number,
	longitude number,
	PRIMARY KEY(id)
);

select * from member;
