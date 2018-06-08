DROP TABLE SELL_BOARD;

CREATE TABLE SELL_BOARD(
	SB_NO		NUMBER	PRIMARY KEY,
	SB_WRITER	VARCHAR2(50)	REFERENCES MEMBER(ID),
	SB_PURCHASE_DATE	DATE	NOT NULL,
	SB_TITLE	VARCHAR2(200)	NOT NULL,
	SB_CONTENT	VARCHAR2(1000)	NOT NULL,
	SB_PRICE	NUMBER			NOT NULL,
	SB_DATE		DATE			NOT NULL,
	SB_READCOUNT	NUMBER,
	SB_LAT		NUMBER			NOT NULL,
	SB_LNG		NUMBER			NOT NULL,
	SB_STATE	NUMBER,						-- 거래중(0), 거래완료(1)
	SB_CATEGORY	NUMBER			REFERENCES CATEGORY(CATEGORY_ID),
	SB_HASHTAG	VARCHAR2(1000)
);

SELECT * FROM SELL_BOARD;
delete from sell_board;

INSERT INTO SELL_BOARD (SB_NO, SB_WRITER, SB_PURCHASE_DATE, SB_TITLE, 
						SB_CONTENT, SB_PRICE, SB_DATE, SB_READCOUNT, SB_LAT, SB_LNG,
						SB_STATE, SB_CATEGORY, SB_HASHTAG) 
VALUES (5, 'admin', sysdate, '크롬하츠 팔찌 팝니다.', '판다고!', 110000, sysdate, 0, 
		37.568301, 126.969727, 0, 1, '#노트 #스마트폰');

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