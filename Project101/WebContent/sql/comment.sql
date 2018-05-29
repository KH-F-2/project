DROP TABLE COMMENTS;

CREATE TABLE COMMENTS(
	CMT_NO NUMBER, 				-- 댓글번호
	CMT_SUBJECT_NO NUMBER, 		-- 게시글번호 FK
	CMT_WRITER VARCHAR2(30), 	-- 작성자
	CMT_DATE DATE, 				-- 작성일
	CMT_CONTENT VARCHAR2(1000), -- 댓글 내용
	CMT_RE_REF NUMBER, 			-- 댓댓글 작성시 참조 글번호
	CMT_RE_LEV NUMBER, 			-- 댓댓글 깊이 
	CMT_SEQ NUMBER				-- 댓글 순서
);

SELECT * FROM COMMENTS;

CREATE SEQUENCE comment_seq start with 1 increment by 1;
