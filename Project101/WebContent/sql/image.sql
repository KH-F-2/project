DROP TABLE IMAGE

CREATE TABLE IMAGE(
	BOARD_NO		NUMBER NOT NULL,
	BOARD_NAME	VARCHAR2(20) NOT NULL,
	IMAGE_URL VARCHAR2(1000)
);

select * from image;

insert into image values(3, 'PURCHASE_BOARD', 'hjifgoihkfiogjhiofgh');