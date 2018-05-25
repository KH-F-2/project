CREATE TABLE SELL_BOARD(
	SB_NO		NUMBER,
	SB_WRITER	VARCHAR2(15),
	SB_BDATE	DATE,
	SB_TITLE	VARCHAR2(30),
	SB_CONTENT	VARCHAR2(200),
	SB_PRICE	NUMBER,
	SB_DATE		DATE,
	SB_READCOUNT	NUMBER,
	PRIMARY KEY(SB_NO)
);

DROP TABLE SELL_BOARD;

SELECT * FROM SELL_BOARD;