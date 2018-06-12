drop table member;

create table member(
	id varchar2(100),
	password varchar2(100),
	nickname varchar2(100) unique,
	email varchar2(200),
	emailcheck varchar2(50),
	phone varchar2(100),
	post varchar2(50),
	address varchar2(500),
	detailaddress varchar2(500),
	latitude number,
	longitude number,
	PRIMARY KEY(id)
);
select * from MEMBER;

select * from member;
