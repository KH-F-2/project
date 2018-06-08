create table notice(
notice_no number,
notice_Q varchar2(2500),
notice_A varchar2(2500),
notice_category number,
primary key(notice_no)
);

create sequence notice_seq;

DROP SEQUENCE notice_seq;

select * from notice;

SELECT  LAST_NUMBER 
FROM  USER_SEQUENCES 
WHERE SEQUENCE_NAME = 'notice_seq';

update notice
set notice_Q = '판매 금지 품목이 목록에서 보여요~',
notice_A = '판매 금지 품목을 발견하시는 경우 신고 부탁드립니다. 신고해주시면 운영정책에 따라 경고 및 제재 처리됩니다.'
where notice_no=2;

insert into notice values (notice_seq.nextval,'사기등의 이유로 이용 제재된 사용자가 다시 가입할 경우 사용할 수 있나요?','이용 정지 상태라면 탈퇴하고 재가입을 하더라도 이용정지 상태는 이어집니다. 전화번호를 변경하여 다시 가입하더라도 마찬가지입니다.',1 );
insert into notice values (notice_seq.nextval, '반려동물 무료 분양 가능한가요?', '마이페이지 > 우측 상단 설정>탈퇴하기 메뉴를 이용하실 수 있어요.'|| chr(10)||'탈퇴를 하시면 2주일 간 재가입이 불가능하니 참고해주세요.',4);
insert into notice values (notice_seq.nextval, '우리 지금 만나에서 지켜야 할 거래 매너', '기본 매너는 이웃 사이에 당연한 예외범절입니다. 꼭 지켜주실 거라 믿어요:)',5);
insert into notice values (notice_seq.nextval, '거래 약속을 안 지키는 거래 상대방을 만나면 어떻게 해야 하나요?','거래 약속을 사전 연락없이 불이행 하는 상대방을 만난 경우 신고하기 게시판을 통해 평가를 해주세요. 거래 약속 불이행 관련된 비매너 평가를 반복적으로 받는 경우 경고 및 이용정지 제재를 받게 됩니다. ',5);
insert into notice values (notice_seq.nextval, '거래는 직접 만나서 하나요?','거래는 거래 당사자분들끼리 협의하셔서 하시면 되세요. 우리 지금 만나는 가까운 동네분들끼리 거래를 연결해드리다 보니 보통 직거래로 많이 하시고요, 부득이한 경우에는 택배거래를 하시는 경우도 있답니다.',4);