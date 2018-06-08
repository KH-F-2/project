DROP TABLE PURCHASE_BOARD;

CREATE TABLE PURCHASE_BOARD(
<<<<<<< HEAD
   PB_NO NUMBER,                     /* 글번호 */
   PB_WRITER VARCHAR2(30),               /* 작성자 */
   PB_TITLE VARCHAR2(300),               /* 제목 */
   PB_CONTENT VARCHAR2(4000),            /* 내용 */
   PB_DATE DATE,                     /* 작성일 */
   PB_READCOUNT NUMBER,               /* 조회수 */
   PB_CATEGORY NUMBER,                  /* 카테고리*/
   PB_HASHTAG VARCHAR2(30),            /* 헤쉬테그*/
   PB_LAT NUMBER,                     /* 위도*/
   PB_LNG NUMBER,                     /* 경도*/
   PB_PRICE NUMBER,                  /* 가격*/
   PB_STATE NUMBER
=======
	PB_NO NUMBER,							/* 글번호 */
	PB_WRITER VARCHAR2(30),					/* 작성자 */
	PB_TITLE VARCHAR2(300),					/* 제목 */
	PB_CONTENT VARCHAR2(4000),				/* 내용 */
	PB_PRICE NUMBER,						/* 가격*/
	PB_READCOUNT NUMBER,					/* 조회수 */
	PB_DATE DATE,							/* 작성일 */
	PB_CATEGORY NUMBER,						/* 카테고리*/
	PB_HASHTAG VARCHAR2(30),				/* 헤쉬테그*/
	PB_STATE NUMBER,						/* 거래상태*/
	PB_LAT NUMBER,							/* 위도*/
	PB_LNG NUMBER							/* 경도*/
>>>>>>> younsik
);

SELECT * FROM PURCHASE_BOARD;

select * from IMAGE;
drop table IMAGE;

select * from (select rownum rnum, NUM, WRITER, TITLE, CONTENT, PRICE, DDATE, CATEGORY, HASHTAG, STATE  
from (select NUM, WRITER, TITLE, CONTENT, PRICE, DDATE, CATEGORY, HASHTAG, STATE from 
(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, PB_CONTENT CONTENT, PB_PRICE PRICE, 
PB_DATE DDATE, PB_CATEGORY CATEGORY, PB_HASHTAG HASHTAG, PB_STATE STATE from purchase_board) 
UNION ALL 
(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT CONTENT, SB_PRICE PRICE, 
SB_DATE DDATE, SB_CATEGORY CATEGORY, SB_HASHTAG HASHTAG, SB_STATE STATE from sell_board)))  
where rnum >= 1 and rnum <= 10 and CATEGORY = 2;

<<<<<<< HEAD
<<<<<<< HEAD
insert into purchase_board values(1, '123', '123', '123', sysdate, 0, 0, 'zz', 37.572743, 126.981493, 100, 0);
insert into purchase_board values(2, '456', '456', '456', sysdate, 0, 0, '2t3t', 37.570838, 126.986117, 4500, 0);
insert into purchase_board values(3, '789', '789', '789', sysdate, 0, 0, 'kggg', 37.569827, 126.981386, 78900, 0);
insert into purchase_board values(4, '1523', '545', '565', sysdate, 0, 0, '5fgg', 37.568253, 126.979380, 78900, 0);
insert into purchase_board values(5, '1523', '4564', '8448', sysdate, 0, 0, 'kfd2g', 37.567318, 126.981729, 78900, 0);
=======
>>>>>>> jusung
=======


select * from (
select rownum rnum, NUM, WRITER, TITLE, CONTENT, PRICE, READCOUNT, CATEGORY, HASHTAG, STATE, DDATE, IMAGE_URL, BOARD_NAME from(
select NUM, WRITER, TITLE, CONTENT, PRICE, READCOUNT, CATEGORY, HASHTAG, STATE, DDATE, IMAGE_URL, BOARD_NAME from 
(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, PB_CONTENT CONTENT, PB_PRICE PRICE, PB_READCOUNT READCOUNT, 
 PB_CATEGORY CATEGORY, PB_HASHTAG HASHTAG, PB_STATE STATE, TO_CHAR(PB_DATE, 'YYYY-MM-DD HH24:MI') DDATE, IMAGE_URL, BOARD_NAME from purchase_board inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')
UNION ALL 
(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT CONTENT, SB_PRICE PRICE, SB_READCOUNT READCOUNT, 
 SB_CATEGORY CATEGORY, SB_HASHTAG HASHTAG, SB_STATE STATE, TO_CHAR(SB_DATE, 'YYYY-MM-DD HH24:MI') DDATE, IMAGE_URL, BOARD_NAME from sell_board inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')))
where rnum >= 1 and rnum <= 5 and category = 0;
>>>>>>> younsik
