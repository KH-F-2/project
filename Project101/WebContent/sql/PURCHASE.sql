DROP TABLE PURCHASE_BOARD;

CREATE TABLE PURCHASE_BOARD(
	PB_NO NUMBER,							/* 글번호 */
	PB_WRITER VARCHAR2(30),					/* 작성자 */
	PB_PASSWORD VARCHAR2(30),				/* 비밀번호 */
	PB_TITLE VARCHAR2(300),					/* 제목 */
	PB_CONTENT VARCHAR2(4000),				/* 내용 */
	PB_FILE VARCHAR2(50),					/* 첨부파일 */	
	PB_DATE DATE,							/* 작성일 */
	PB_READCOUNT NUMBER						/* 조회수 */
);

SELECT * FROM PURCHASE_BOARD;


select * from (select rownum rnum,pb_no ,pb_writer,pb_title, 
pb_content, pb_file, pb_date,PB_READCOUNT 
from (select * from PURCHASE_BOARD)) where rnum>=0 and rnum<=10 ; 

