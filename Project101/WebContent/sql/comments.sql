DROP TABLE COMMENTS;

CREATE TABLE COMMENTS(
	CMT_NO NUMBER PRIMARY KEY, 				-- 댓글번호
	CMT_SUBJECT_NO NUMBER NOT NULL, 		
	CMT_WRITER VARCHAR2(30) REFERENCES MEMBER(ID), 		-- 작성자
	CMT_CONTENT VARCHAR2(1000), 			-- 댓글 내용
	CMT_DATE DATE NOT NULL, 				-- 작성일
	CMT_RE_REF NUMBER NOT NULL,				-- 댓댓글 작성시 참조 글번호
	CMT_RE_LEV NUMBER NOT NULL,				-- 댓댓글 깊이 
	CMT_RE_SEQ NUMBER NOT NULL,				-- 댓글 순서
	CMT_BOARD_NAME VARCHAR2(30) NOT NULL 	-- 게시판 이름
);

SELECT * FROM COMMENTS;

CREATE SEQUENCE comment_seq start with 1 increment by 1;
drop sequence comment_seq;


select * from (select rownum rnum, CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_CONTENT, 
					TO_CHAR(CMT_DATE, 'YYYY-MM-DD HH24:MI:SS') as CMT_DATE, CMT_RE_REF, CMT_RE_LEV, 
					CMT_RE_SEQ, CMT_BOARD_NAME FROM (SELECT * FROM COMMENTS 
					where CMT_SUBJECT_NO = 2 AND CMT_BOARD_NAME = 'SELL_BOARD' 
					order by CMT_NO asc, CMT_RE_REF desc, CMT_RE_SEQ asc)) 
					where rnum >= 1 and rnum <= 10
					
select * from (select rownum rnum, CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_CONTENT, 
					TO_CHAR(CMT_DATE, 'YYYY-MM-DD HH24:MI:SS') as CMT_DATE, CMT_RE_REF, CMT_RE_LEV, 
					CMT_RE_SEQ, CMT_BOARD_NAME FROM (SELECT * FROM COMMENTS 
					where CMT_SUBJECT_NO = 2 AND CMT_BOARD_NAME = 'SELL_BOARD' 
					order by CMT_RE_REF asc, CMT_NO asc, CMT_RE_SEQ desc, CMT_RE_LEV asc)) 
					where rnum >= 1 and rnum <= 10
					
delete from COMMENTS where CMT_SUBJECT_NO>3 and CMT_BOARD_NAME='SELL_BOARD'