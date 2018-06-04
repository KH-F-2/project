DROP TABLE PURCHASE_BOARD;

CREATE TABLE PURCHASE_BOARD(
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
);

SELECT * FROM PURCHASE_BOARD;

delete from purchase_board

insert into purchase_board values(1, '123', '123', '123', sysdate, 0, 0, 'zz', 37.572743, 126.981493, 100, 0);
insert into purchase_board values(2, '456', '456', '456', sysdate, 0, 0, '2t3t', 37.570838, 126.986117, 4500, 0);
insert into purchase_board values(3, '789', '789', '789', sysdate, 0, 0, 'kggg', 37.569827, 126.981386, 78900, 0);
insert into purchase_board values(4, '1523', '545', '565', sysdate, 0, 0, '5fgg', 37.568253, 126.979380, 78900, 0);
insert into purchase_board values(5, '1523', '4564', '8448', sysdate, 0, 0, 'kfd2g', 37.567318, 126.981729, 78900, 0);

insert into SELL_BOARD values(1, 'admin', sysdate, '545', '565', 0, sysdate, 0, 37.570674, 126.980263, 78900, 1, '5fgg');
insert into SELL_BOARD values(2, 'admin', sysdate, '324234', 'gsdg', 0, sysdate, 0, 37.571176, 126.983203, 78900, 1, 'sdfhdsf4');


select * from (select rownum rnum, SB_NO as NUM, SB_WRITER as WRITER, SB_TITLE as TITLE, SB_READCOUNT as READCOUNT, SB_DATE as DDATE, SB_PRICE as PRICE, IMAGE_URL, BOARD_NAME from 
	(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD')) where rnum>=1 and rnum<=5;
	
select * from (select rownum rnum, PB_NO as NUM, PB_WRITER as WRITER, PB_TITLE as TITLE, PB_READCOUNT as READCOUNT, PB_DATE as DDATE, PB_PRICE as PRICE, sqrt(power((0-PB_LAT),2) + power((0-PB_LNG),2)) as distance, IMAGE_URL, BOARD_NAME from 
	(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')) where rnum>=1 and rnum<=5;
	
	
select * from (select rownum rnum, NUM, WRITER, TITLE, READCOUNT, DDATE, PRICE, distance, IMAGE_URL, BOARD_NAME from (select NUM, WRITER, TITLE, READCOUNT, DDATE, PRICE, distance, IMAGE_URL, BOARD_NAME from 
	(select SB_NO as NUM, SB_WRITER as WRITER, SB_TITLE as TITLE, SB_READCOUNT as READCOUNT, SB_DATE as DDATE, SB_PRICE as PRICE, sqrt(power((37.570158-SB_LAT),2) + power((126.982899-SB_LNG),2)) as distance, IMAGE_URL, BOARD_NAME from 
	(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD'))
	UNION ALL (select PB_NO as NUM, PB_WRITER as WRITER, PB_TITLE as TITLE, PB_READCOUNT as READCOUNT, PB_DATE as DDATE, PB_PRICE as PRICE, sqrt(power((37.570158-PB_LAT),2) + power((126.982899-PB_LNG),2)) as distance, IMAGE_URL, BOARD_NAME from 
	(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')))) where rnum >= 1 and rnum <= 5 order by distance
	
	
select * from (select rownum rnum, NUM, WRITER, TITLE, CONTENT, READCOUNT, DDATE, CATEGORY, HASHTAG, STATE, distance, IMAGE_URL, BOARD_NAME from 
	(select NUM, WRITER, TITLE, CONTENT, READCOUNT, DDATE, CATEGORY, HASHTAG, STATE, distance, IMAGE_URL, BOARD_NAME from 
	(select SB_NO as NUM, SB_WRITER as WRITER, SB_TITLE as TITLE, SB_CONTENT as CONTENT, SB_READCOUNT as READCOUNT, SB_DATE as DDATE, SB_CATEGORY as CATEGORY, 
	SB_HASHTAG as HASHTAG, SB_STATE as STATE,  sqrt(power((37.570158-SB_LAT),2) + power((126.982899-SB_LNG),2)) as distance, IMAGE_URL, BOARD_NAME from 
	(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD'))
	UNION ALL (select PB_NO as NUM, PB_WRITER as WRITER, PB_TITLE as TITLE, PB_CONTENT as CONTENT, PB_READCOUNT as READCOUNT, PB_DATE as DDATE, PB_CATEGORY as CATEGORY, 
	PB_HASHTAG as HASHTAG, PB_STATE as STATE, sqrt(power((37.570158-PB_LAT),2) + power((126.982899-PB_LNG),2)) as distance, IMAGE_URL, BOARD_NAME from 
	(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD')))) 
	where rnum >= 1 and rnum <= 5 order by distance
	
	sqrt(power((?-LAT),2) + power((?-LNG),2)) as distance
	order by distance
	
	
	
select * from (select rownum rnum,pb_no ,pb_writer,pb_title, 
pb_content, pb_file, pb_date,PB_READCOUNT 
from (select * from PURCHASE_BOARD)) where rnum>=0 and rnum<=10 ; 

