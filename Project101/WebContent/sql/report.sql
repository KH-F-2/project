create table REPORT_BOARD (
	RB_NO NUMBER, 		--글번호
	rb_rp_id varchar2(100),
	rb_rp_no number,
	rb_rp_board_name varchar2(50),
	RB_WRITER VARCHAR2(100),	--작성자
	RB_TITLE  VARCHAR2(1000),	--제목
	RB_CONTENT VARCHAR2(3000),
	RB_DATE DATE,
	PRIMARY KEY(RB_NO)
);

SELECT * FROM REPORT_BOARD;

update REPORT_BOARD set RB_READCOUNT=RB_READCOUNT+1 where RB_NO=1;
drop table REPORT_BOARD;
insert into REPORT_BOARD values (1,'ssss','esfdjfk','fjksdjfklsjlkfj',5555, sysdate, 1);