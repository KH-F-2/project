DROP TABLE SELL_HISTORY;

CREATE TABLE SELL_HISTORY(
	SH_NO			NUMBER			PRIMARY KEY,		-- 거래번호
	SH_BOARD_NO		NUMBER, 	-- 판매게시글 번호
	SH_MEMBER		VARCHAR2(15)	REFERENCES MEMBER(ID),	-- 사용자
	SH_OPPONENT		VARCHAR2(15)	REFERENCES MEMBER(ID),	-- 거래상대
	SH_DATE			DATE			NOT NULL,		-- 거래완료일시 (INSERT시 SYSDATE)
	SH_STATE		NUMBER							-- 거래중(0), 거래완료(1)
);

select * from sell_history;

delete from sell_history