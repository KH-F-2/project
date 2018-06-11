create table message(
   MS_NO NUMBER,
   MS_SEND VARCHAR2(50) not null, --작성자
   MS_TO VARCHAR2(50) not null, --받는사람
   MS_DATE DATE, --보내는날짜
   MS_TITLE VARCHAR2(100), --제목
   MS_CONTENT VARCHAR2(500) --내용   
)