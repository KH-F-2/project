DROP TABLE SELL_BOARD;

CREATE TABLE SELL_BOARD(
	SB_NO		NUMBER	PRIMARY KEY,
	SB_WRITER	VARCHAR2(50)	REFERENCES MEMBER(ID),
	SB_TITLE	VARCHAR2(200)	NOT NULL,
	SB_CONTENT	VARCHAR2(1000)	NOT NULL,
	SB_PRICE	NUMBER			NOT NULL,
	SB_DATE		DATE			NOT NULL,
	SB_READCOUNT	NUMBER,
	SB_LAT		NUMBER			NOT NULL,
	SB_LNG		NUMBER			NOT NULL,
	SB_STATE	NUMBER,						
	SB_CATEGORY	NUMBER			REFERENCES CATEGORY(CATEGORY_ID),
	SB_HASHTAG	VARCHAR2(1000)
);

SELECT TO_CHAR(SB_DATE, 'YY/MM/DD HH24:MI:SS') FROM SELL_BOARD;

select * from image;

select * from IMAGE where BOARD_NO=2 and BOARD_NAME='SELL_BOARD'

delete from SELL_BOARD;

alter table sell_board drop column SB_PURCHASE_DATE


update SELL_BOARD set SB_LAT = 37.571111, SB_LNG = 126.986160 where SB_NO = 3;
INSERT INTO SELL_BOARD (SB_NO, SB_WRITER, SB_PURCHASE_DATE, SB_TITLE, 
						SB_CONTENT, SB_PRICE, SB_DATE, SB_READCOUNT, SB_LAT, SB_LNG,
						SB_STATE, SB_CATEGORY, SB_HASHTAG) 
VALUES (5, 'admin', sysdate, '�겕濡ы븯痢� �뙏李� �뙘�땲�떎.', '�뙋�떎怨�!', 110000, sysdate, 0, 
		37.568301, 126.969727, 0, 1, '#�끂�듃 #�뒪留덊듃�룿');

select * from SELL_BOARD, CATEGORY 
					where SELL_BOARD.SB_CATEGORY = CATEGORY.CATEGORY_ID 
					AND SELL_BOARD.SB_NO=7
					
select * from SELL_BOARD inner join CATEGORY 
					ON SELL_BOARD.SB_CATEGORY = CATEGORY.CATEGORY_ID 
					WHERE SELL_BOARD.SB_NO=7					
					
select * from category

select * from (select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, 
				SB_READCOUNT, SB_DATE from (select * from SELL_BOARD order by SB_NO desc)) 
					where rnum>=1 and rnum<=5
					
select * from(select rownum rnum, SB_NO, SB_WRITER, SB_TITLE, SB_READCOUNT, SB_DATE, IMAGE_URL from 
	(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')) where rnum>=1 and rnum<=5;
	
select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD'
	


