CREATE TABLE BOARD_COMMENT(
	COMMENT_NO		NUMBER NOT NULL,
	BOARD_NO		NUMBER NOT NULL,
	BOARD_NAME	VARCHAR2(15) NOT NULL,
	COMMENT_WRITER VARCHAR2(20) NOT NULL,
	COMMENT_DATE DATE NOT NULL,
	COMMENT_CONTENT	VARCHAR2(100),
	PRIMARY KEY(COMMENT_NO)
);

DROP TABLE BOARD_COMMENT;