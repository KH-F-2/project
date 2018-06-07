CREATE TABLE SELL_BOARD(
   SB_NO      NUMBER   PRIMARY KEY,
   SB_WRITER   VARCHAR2(50)   REFERENCES MEMBER(ID),
   SB_PURCHASE_DATE   DATE   NOT NULL,
   SB_TITLE   VARCHAR2(200)   NOT NULL,
   SB_CONTENT   VARCHAR2(1000)   NOT NULL,
   SB_PRICE   NUMBER         NOT NULL,
   SB_DATE      DATE         NOT NULL,
   SB_READCOUNT   NUMBER,
   SB_LAT      NUMBER         NOT NULL,
   SB_LNG      NUMBER         NOT NULL,
   SB_STATE   NUMBER,                  
   SB_CATEGORY   NUMBER         REFERENCES CATEGORY(CATEGORY_ID),
   SB_HASHTAG   VARCHAR2(1000)
);

insert into sell_board values(1,'ddd',sysdate,'판매제목','판매내용',200,sysdate,0,1,1,0,0,'응');

ALTER TABLE SELL_BOARD
RENAME COLUMN SB_BDATE TO SB_PDATE;

alter table SELL_BOARD ADD (SB_MDATE DATE DEFAULT SYSDATE);

DROP TABLE SELL_BOARD;

SELECT * FROM SELL_BOARD;

CREATE TABLE REPORT_BOARD(
	RB_NO		NUMBER,
	RB_WRITER	VARCHAR2(15),
	RB_TITLE	VARCHAR2(30),
	RB_CONTENT	VARCHAR2(200),
	PRIMARY KEY(RB_NO)
);


select * from (select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, 
	SB_READCOUNT, SB_DATE from 
	(select * from SELL_BOARD where SB_TITLE LIKE '%123%' 
	order by SB_NO desc)) where rnum>=1 and rnum<=5;
	
select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, SB_READCOUNT, SB_DATE 
	from (select * from SELL_BOARD where SB_TITLE LIKE '%123%' order by SB_NO desc);
	
select * from (select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, SB_READCOUNT, SB_DATE 
	from (select * from SELL_BOARD where SB_TITLE LIKE '%123%' order by SB_NO desc)) where rnum>=1 and rnum<=5;