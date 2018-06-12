create table epil(
	EP_NO NUMBER PRIMARY KEY,
	EP_NAME VARCHAR2(50) REFERENCES MEMBER(ID),
	EP_WRITER VARCHAR2(50) REFERENCES MEMBER(ID),
	EP_CONTENT VARCHAR2(500),
	EP_DATE DATE,
	EP_STAR NUMBER
)
insert into epilogue(ep_no,ep_content,ep_date,ep_star) values(1,'내용',sysdate,3)
drop table epilogue

insert into epil values(1,'popf','admin','내용',sysdate,3)
select * from epil

select * from epil where EP_NAME='admin'