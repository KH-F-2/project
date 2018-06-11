CREATE TABLE SELL_BOARD(
   SB_NO      NUMBER   PRIMARY KEY,
   SB_WRITER   VARCHAR2(100)   REFERENCES MEMBER(ID),
   SB_TITLE   VARCHAR2(1000)   NOT NULL,
   SB_CONTENT   VARCHAR2(3000)   NOT NULL,
   SB_PRICE   NUMBER         NOT NULL,
   SB_DATE      DATE         NOT NULL,
   SB_READCOUNT   NUMBER,
   SB_LAT      NUMBER         NOT NULL,
   SB_LNG      NUMBER         NOT NULL,
   SB_STATE   NUMBER,                  
   SB_CATEGORY   NUMBER         REFERENCES CATEGORY(CATEGORY_ID),
   SB_HASHTAG   VARCHAR2(1000)
);

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
	
--	37.539069613275636
--	126.9744667583002
	
select * from 
	(select rownum rnum, NUM, WRITER, TITLE, CONTENT, READCOUNT, DDATE, CATEGORY, HASHTAG, STATE, lat, lng, distance, IMAGE_URL, BOARD_NAME from 
		(select NUM, WRITER, TITLE, CONTENT, READCOUNT, DDATE, CATEGORY, HASHTAG, STATE, lat, lng, distance, IMAGE_URL, BOARD_NAME from 
			(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT CONTENT, SB_READCOUNT READCOUNT
			, SB_DATE DDATE, SB_CATEGORY CATEGORY, SB_HASHTAG HASHTAG, SB_STATE STATE, SB_LAT lat, SB_LNG lng
			,  sqrt(power((37.539069613275636 - SB_LAT), 2) + power((126.9744667583002 - SB_LNG), 2)) distance, IMAGE_URL, BOARD_NAME from 
			(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')) 
		UNION ALL 
			(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, PB_CONTENT CONTENT, PB_READCOUNT READCOUNT
			, PB_DATE DDATE, PB_CATEGORY CATEGORY, PB_HASHTAG HASHTAG, PB_STATE STATE,PB_LAT lat, PB_LNG lng
 			, sqrt(power((37.539069613275636 - PB_LAT), 2) + power((126.9744667583002 - PB_LNG), 2)) distance, IMAGE_URL, BOARD_NAME from  
			(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')))) 
where rnum >= 11 and rnum <= 20 order by distance;



select * from sell_board;
	               
insert into SELL_BOARD values(1, 'admin', sysdate, '545', '565', 0, sysdate, 0, 37.570674, 126.980263, 78900, 1, '5fgg');
insert into SELL_BOARD values(2, 'admin', sysdate, '324234', 'gsdg', 0, sysdate, 0, 37.571176, 126.983203, 78900, 1, 'sdfhdsf4');
insert into SELL_BOARD values(3, 'admin', sysdate, '123545', 'asdf123565', 39900, sysdate, 0, 37.570999, 126.980999, 78900, 1, 'ㅋㅋㅋ');
insert into SELL_BOARD values(4, 'admin', sysdate, '가나다', '라마바사', 29900, sysdate, 0, 37.570555, 126.980555, 78900, 1, 'ㅎㅎㅎ');
insert into SELL_BOARD values(5, 'admin', sysdate, '아자차', '카타파하', 59900, sysdate, 0, 37.570000, 126.980000, 78900, 1, 'ㅍㅍㅍ');
insert into SELL_BOARD values(6, 'admin', sysdate, 'ajax 테스트용', 'ㅋㅋㅋㅋㅋㅋㅋ', 12900, sysdate, 0, 37.570111, 126.980111, 78900, 1, 'ㅍㅍㅍ');