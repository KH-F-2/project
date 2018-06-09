DROP TABLE COMMENTS;

CREATE TABLE COMMENTS(
   CMT_NO NUMBER PRIMARY KEY,             -- 댓글번호
   CMT_SUBJECT_NO NUMBER NOT NULL,       -- 게시글번호 FK
   CMT_WRITER VARCHAR2(50) ,             -- 작성자     <!--NOT NULL,-->   
   CMT_DATE DATE NOT NULL,             -- 작성일
   CMT_CONTENT VARCHAR2(1000),          -- 댓글 내용
   CMT_RE_REF NUMBER NOT NULL,            -- 댓댓글 작성시 참조 글번호
   CMT_RE_LEV NUMBER NOT NULL,            -- 댓댓글 깊이 
   CMT_RE_SEQ NUMBER NOT NULL,            -- 댓글 순서
   CMT_BOARD_NAME VARCHAR2(50) NOT NULL    -- 게시판 테이블 이름
);

<<<<<<< HEAD
SELECT * FROM COMMENTS;

CREATE SEQUENCE comment_seq start with 1 increment by 1;

select CMT_RE_REF, CMT_RE_LEV, CMT_SEQ from COMMENTS where CMT_NO=5;

select CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_DATE, CMT_CONTENT, CMT_RE_REF, CMT_RE_LEV, CMT_RE_SEQ from 
(select rownum rnum, CMT_NO, CMT_SUBJECT_NO, CMT_WRITER, CMT_DATE, CMT_CONTENT, CMT_RE_REF, CMT_RE_LEV, CMT_RE_SEQ 
FROM (SELECT * FROM COMMENTS where CMT_SUBJECT_NO = 6 order by CMT_RE_REF asc, CMT_RE_SEQ asc )) 
where rnum>= 1 and rnum<= 10 
=======
DROP TABLE BOARD_COMMENT;
select * from BOARD_COMMENT;
ALTER TABLE BOARD_COMMENT MODIFY(COMMENT_CONTENT VARCHAR2(300));
>>>>>>> origin/yeunju
