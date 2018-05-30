DROP TABLE COMMENTS;

CREATE TABLE COMMENTS(
	CMT_NO NUMBER PRIMARY KEY, 				-- 댓글번호
	CMT_SUBJECT_NO NUMBER NOT NULL, 		-- 게시글번호 FK
	CMT_WRITER VARCHAR2(30) , 				-- 작성자  	<!--NOT NULL,-->	
	CMT_DATE DATE NOT NULL, 				-- 작성일
	CMT_CONTENT VARCHAR2(1000), 			-- 댓글 내용
	CMT_RE_REF NUMBER NOT NULL,				-- 댓댓글 작성시 참조 글번호
	CMT_RE_LEV NUMBER NOT NULL,				-- 댓댓글 깊이 
	CMT_SEQ NUMBER NOT NULL,				-- 댓글 순서
	CMT_BOARD_NAME VARCHAR2(30) NOT NULL 	-- 게시판 테이블 이름
);

SELECT * FROM COMMENTS;

CREATE SEQUENCE comment_seq start with 1 increment by 1;

