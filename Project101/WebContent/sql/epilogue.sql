CREATE TABLE EPILOGUE_BOARD(
	SB_NO		NUMBER,
	SB_WRITER	VARCHAR2(100),
	SB_TITLE	VARCHAR2(200),
	SB_CONTENT	VARCHAR2(2000),
	SB_PRICE	NUMBER,
	SB_DATE		DATE,
	SB_READCOUNT		NUMBER,
	PRIMARY KEY(SB_NO)
);
select * from member;
--컬럼수정
alter table member modify(SB_FILE varchar2(50),file varchar2(50));
--컬럼추가
alter table epilogue_board add(SB_BDATE date) 
DROP TABLE EPILOGUE_BOARD;
alter table epilogue_board drop column bdate
alter table epilogue_board add(sb_grade number)
alter table member add(file varchar2(50))
SELECT * FROM EPILOGUE_BOARD;
desc epilogue_board
insert into EPILOGUE_BOARD values(1,'홍주성','리스트','내용',100,SYSDATE,1);
insert into member(epfile) values()
select * from 
(select rownum rnum, sb_no, sb_writer,sb_title,sb_readcount,
sb_date,sb_content 
from (select * from EPILOGUE_BOARD where sb_title like 1))
order by sb_no desc))
where rnum>=2 and rnum<=2;
