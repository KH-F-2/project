
create table message(
	MS_NO NUMBER,
	MS_SEND VARCHAR2(50) not null, --작성자
	MS_TO VARCHAR2(50) not null, --받는사람
	MS_DATE DATE, --보내는날짜
	MS_TITLE VARCHAR2(100), --제목
	MS_CONTENT VARCHAR2(500), --내용	
	MS_READCOUNT NUMBER --조회수
)
alter table message DROP COLUMN MS_READCOUNT
alter table message add(MS_READCOUNT number)
select * from message
INSERT INTO MESSAGE VALUES(11,'admin','popf',SYSDATE,'제목','내용',0)
select count(*) from message where MS_SEND = 'admin'
update message set MS_READCOUNT = MS_READCOUNT+1  where MS_NO=2

drop table message