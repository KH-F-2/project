<<<<<<< HEAD
<<<<<<< HEAD
drop table image;
=======
DROP TABLE IMAGE
>>>>>>> jusung
=======
DROP TABLE IMAGE
>>>>>>> younsik

CREATE TABLE IMAGE(
	BOARD_NO		NUMBER NOT NULL,
	BOARD_NAME	VARCHAR2(20) NOT NULL,
<<<<<<< HEAD
<<<<<<< HEAD
	IMAGE_URL VARCHAR2(150)
=======
	IMAGE_URL VARCHAR2(1000)
>>>>>>> younsik
=======
	IMAGE_URL VARCHAR2(1000)
>>>>>>> origin/seungwoo
);

select * from image;

insert into image values(3, 'PURCHASE_BOARD', 'hjifgoihkfiogjhiofgh');