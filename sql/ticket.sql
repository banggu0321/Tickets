--========================================
--TICKETS
--========================================

--관리자 계정으로 tickets계정 생성
--CREATE USER TICKETS IDENTIFIED BY 0321;

--tickets권한 (모든 권한)
--GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO TICKETS IDENTIFIED BY 0321;
--ALTER USER TICKETS DEFAULT TABLESPACE USERS;
--ALTER USER TICKETS TEMPORARY TABLESPACE TEMP;

--사용자삭제
--SQL> drop user ticket cascade;

--======계정내 테이블확인==============================
SELECT * FROM user_tables;
SELECT * FROM user_sequences;
SELECT * FROM USER_CONSTRAINTS ;

--===테이블 생성=================================
--Member
DROP TABLE MEMBER cascade constraint;
CREATE TABLE MEMBER(
	m_id varchar2(20) CONSTRAINT member_id_pk PRIMARY KEY,
	m_pw varchar2(20) CONSTRAINT member_pw_ck NOT NULL,
	m_name VARCHAR2(20) CONSTRAINT member_name_ck NOT NULL ,
	m_email VARCHAR2(25) CONSTRAINT member_email_ck NOT NULL ,
	m_phone VARCHAR2(15)
);
SELECT * FROM "MEMBER" m;

--Performance
DROP TABLE performance cascade constraint;
CREATE TABLE PERFORMANCE(
	per_no NUMBER PRIMARY KEY,
	per_title VARCHAR2(20) NOT NULL,
	per_location VARCHAR2(20) NOT NULL,
	per_date DATE NOT NULL,
	per_time VARCHAR2(10) NOT NULL,
	per_price VARCHAR2(20) NOT NULL,
	per_cast VARCHAR2(10) NOT NULL,
	per_category VARCHAR2(20) NOT NULL,
	per_seat NUMBER NOT null,
 	constraint per_category_ck check(per_category in ('뮤지컬','콘서트','연극', '클래식'))
);
SELECT * FROM PERFORMANCE ;

--Wishlist
DROP TABLE wishlist cascade CONSTRAINT ;
CREATE TABLE WISHLIST(
	wish_no NUMBER PRIMARY KEY,
	m_id VARCHAR2(20),
	per_no NUMBER ,
	wish_see char(1) DEFAULT 'N',
	constraint wish_see_ck check(wish_see in ('N','Y')),
	constraint wish_mem_id_fk foreign key(m_id) references MEMBER(m_id),
	constraint wish_per_no_fk foreign key(per_no) references PERFORMANCE(per_no)
);
ALTER TABLE WISHLIST ADD UNIQUE (m_id,per_no);
SELECT * FROM wishlist;

--Ticket
DROP TABLE ticket cascade CONSTRAINT ;
CREATE TABLE TICKET(
	tic_no NUMBER PRIMARY KEY,
	wish_no NUMBER,
	tic_date DATE ,
	constraint tic_wish_no_fk foreign key(wish_no) references wishlist(wish_no)
);

SELECT * FROM ticket;


--===시퀀스 생성=================================
--SEQUENCE
CREATE SEQUENCE SEQ_PERNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_TICNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_WISHNO INCREMENT BY 1 START WITH 1;

DROP SEQUENCE SEQ_PERNO;
DROP SEQUENCE SEQ_TICNO;
DROP SEQUENCE SEQ_WISHNO;


--===JDBC프로그램 SQL작성=================================
--관리자 아이디 비번 설정
INSERT INTO MEMBER VALUES('admin','admin1234','관리자','admin@gmai.com','010-1234-5678');
--관리자 계정 접속시
--1.조회(공연,  회원, 예매)
SELECT * FROM PERFORMANCE;
SELECT * FROM MEMBER;
SELECT * FROM TICKET;
--2.공연 등록, 좌석 수 변경, 삭제
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'공연명','장소',to_date('2022-08-01 13:00','yyyy-mm-dd hh24:mi'),'12분','100000원','김모씨','뮤지컬','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'공연명2','장소',to_date('2022-08-01 13:00','yyyy-mm-dd hh24:mi'),'12분','100000원','김모씨','뮤지컬','10');
UPDATE PERFORMANCE SET per_seat=4 WHERE per_no = 1;
DELETE FROM PERFORMANCE WHERE per_no = 1;

--main페이지
--1. 회원가입
INSERT INTO MEMBER VALUES('id','pw','김','aa','101');
INSERT INTO MEMBER VALUES('id2','pw','김','aa','101');
--2. 로그인(success,fail)
SELECT M_ID FROM MEMBER WHERE M_ID ='id';--id:static으로 빼놓기, 
--3. 공연조회(제목별, 카테고리별, 전체조회->>관심등록 --뒤로가기While)
SELECT * FROM PERFORMANCE WHERE per_title = '공연명' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '뮤지컬' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '콘서트' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '연극' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '클래식' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE PER_DATE > sysdate ORDER BY PER_DATE;

INSERT INTO WISHLIST (wish_no, m_id, per_no) values(seq_wishno.nextval, 'id2','1'); --if문으로 wish_see='N'확인
INSERT INTO WISHLIST (wish_no, m_id, per_no) values(seq_wishno.nextval, 'id','2');

--4. 예매하기 (해당 회원의 관심목록 출력) --<**님의 wishlist> OR [알림]wishlist가 비었습니다. 조회후 wish 등록해주세요
SELECT p.PER_NO, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE  
FROM WISHLIST w JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO 
WHERE M_ID = 'id' AND WISH_SEE ='N';
----1.예매하기 (N만 가능함)
INSERT INTO TICKET VALUES(seq_ticno.nextval, 2, sysdate);
UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT-1 WHERE per_no = 2;
UPDATE WISHLIST SET WISH_SEE = 'Y' WHERE per_no = 2;
----2.뒤로가기while
--5. 마이페이지
----1.개인정보수정
UPDATE MEMBER SET m_pw='pw2', m_name='김2', m_email='aa2', m_phone='102' WHERE m_id ='id';
----2.예매확인/취소 - 조회, 삭제
SELECT t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id2';

DELETE FROM TICKET WHERE tic_no = 3 AND TIC_DATE < sysdate;-->날짜가 이후일경우
----3.관심목록조회
SELECT * FROM WISHLIST WHERE M_ID = 'id';
----4.로그아웃 id=null
----5.탈퇴(예매내역 있으면 탈퇴불가)
DELETE FROM "MEMBER" WHERE M_ID = 'id';





