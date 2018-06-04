DROP TABLE TRADE_HISTORY;

CREATE TABLE TRADE_HISTORY(
	TH_NO			NUMBER			PRIMARY KEY,		
	TH_BOARD_NO		NUMBER,			
	TH_BOARD_NAME	VARCHAR2(20),
	TH_MEMBER		VARCHAR2(15)	FOREIGN KEY(SH_MEMEBER) REFERENCES MEMBER(ID),	-- 사용자
	TH_DATE			DATE			NOT NULL		-- 거래완료일시 (INSERT시 SYSDATE)
);

-- SELL_HISTORY, PURCHASE_HISTORY 의 STATE 가 거래완료(1)(sell,purchase_his update) 가 될시
-- insert를 같이해줌