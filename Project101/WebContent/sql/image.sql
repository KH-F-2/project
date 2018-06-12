DROP TABLE IMAGE

CREATE TABLE IMAGE(
	BOARD_NO		NUMBER NOT NULL,
	BOARD_NAME	VARCHAR2(20) NOT NULL,
	IMAGE_URL VARCHAR2(1000)
);

select * from image;

insert into image values(3, 'PURCHASE_BOARD', 'hjifgoihkfiogjhiofgh');
select sb_no from sell_board where sb_writer = 'admin'

select image_url
from image
where board_no in(select sb_no from sell_board where sb_writer = 'admin')

select * from member;
select * from sell_board;

select SB_TITLE, SB_TITLE, IMAGE_URL, SB_NO
from image, sell_board
where sb_no = board_no and sb_writer = 'admin';


