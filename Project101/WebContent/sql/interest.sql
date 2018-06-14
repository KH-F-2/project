create table interest (
	num		number,
	id			varchar2(100),
	content_num	number,
	board_name	varchar2(50),
	PRIMARY KEY(num)
);



select rownum rnum, NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, lat, lng, IMAGE_URL, BOARD_NAME, id from 

	(select NUM, WRITER, TITLE, content, price, READCOUNT, DDATE, category, hashtag, lat, lng, IMAGE_URL, BOARD_NAME , id from 
			
			(select SB_NO NUM, SB_WRITER WRITER, SB_TITLE TITLE, SB_CONTENT content, sb_price price, SB_READCOUNT READCOUNT, SB_DATE DDATE
			, sb_category category, sb_hashtag hashtag, sb_lat lat, sb_lng lng, IMAGE_URL, BOARD_NAME from 
			
				(select * from SELL_BOARD inner join IMAGE on SELL_BOARD.SB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'SELL_BOARD'))
				, (select num in_num, content_num in_content_num, id from interest where board_name='SELL_BOARD') where num=in_num) 
				
	UNION ALL 
	
		(select PB_NO NUM, PB_WRITER WRITER, PB_TITLE TITLE, pb_content content, pb_price price, PB_READCOUNT READCOUNT, PB_DATE DDATE
		, pb_category category, pb_hashtag hashtag, pb_lat lat, pb_lng lng, IMAGE_URL, BOARD_NAME from 
		 
			
				(select * from PURCHASE_BOARD inner join IMAGE on PURCHASE_BOARD.PB_NO = IMAGE.BOARD_NO where IMAGE.BOARD_NAME = 'PURCHASE_BOARD'))
				, (select num in_num, content_num in_content_num, id from interest where board_name='PURCHASE_BOARD') where num=in_num)
				
) where  id='popf' order by ddate;


