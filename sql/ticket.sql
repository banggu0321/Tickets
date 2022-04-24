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

--제약조건 기록 삭제
purge recycleBIN;

--===테이블 생성=================================
--Member
CREATE TABLE MEMBER(
	m_id varchar2(20) CONSTRAINT member_id_pk PRIMARY KEY,
	m_pw varchar2(20) CONSTRAINT member_pw_ck NOT NULL,
	m_name VARCHAR2(20) CONSTRAINT member_name_ck NOT NULL ,
	m_email VARCHAR2(25) CONSTRAINT member_email_ck NOT NULL ,
	m_phone VARCHAR2(15)
);

--Performance
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

--Wishlist
CREATE TABLE WISHLIST(
	wish_no NUMBER PRIMARY KEY,
	m_id VARCHAR2(20) ,
	per_no NUMBER ,
	wish_see char(1) DEFAULT 'N',
	constraint wish_see_ck check(wish_see in ('N','Y')),
	constraint wish_mem_id_fk foreign key(m_id) references MEMBER(m_id) ON DELETE CASCADE,
	constraint wish_per_no_fk foreign key(per_no) references PERFORMANCE(per_no) ON DELETE CASCADE
);
ALTER TABLE WISHLIST ADD UNIQUE (m_id,per_no);

--Ticket
CREATE TABLE TICKET(
	tic_no NUMBER PRIMARY KEY,
	wish_no NUMBER,
	tic_date DATE ,
	constraint tic_wish_no_fk foreign key(wish_no) references wishlist(wish_no) ON DELETE CASCADE
);

--테이블 조회
SELECT * FROM "MEMBER" m;
SELECT * FROM PERFORMANCE ;
SELECT * FROM wishlist;
SELECT * FROM ticket;

--테이블 삭제
DROP TABLE MEMBER cascade constraint;
DROP TABLE performance cascade constraint;
DROP TABLE wishlist cascade CONSTRAINT ;
DROP TABLE ticket cascade CONSTRAINT ;


--===시퀀스 생성=================================
--SEQUENCE
CREATE SEQUENCE SEQ_PERNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_TICNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_WISHNO INCREMENT BY 1 START WITH 1;

--시퀀스 삭제
DROP SEQUENCE SEQ_PERNO;
DROP SEQUENCE SEQ_TICNO;
DROP SEQUENCE SEQ_WISHNO;


--===JDBC프로그램 SQL작성=================================
--관리자 아이디 비번 설정
--INSERT INTO MEMBER VALUES('admin','admin1234','관리자','admin@gmai.com','010-1234-5678');
--관리하기(다른 sql파일 만들기)
--1.조회(공연,  회원, 예매) ->전체조회
SELECT * FROM PERFORMANCE;
SELECT * FROM MEMBER;
SELECT t.TIC_NO, m.M_ID, m.M_NAME, p.PER_NO
FROM WISHLIST w RIGHT OUTER join TICKET t ON w.WISH_NO = t.WISH_NO 
				JOIN PERFORMANCE p ON w.per_no = p.PER_NO 
				JOIN MEMBER m ON w.M_ID = m.M_ID;
--2.공연 등록, 좌석 수 변경, 삭제
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'공연명','장소',to_date('2022-08-01 13:00','yyyy-mm-dd hh24:mi'),'12분','100000원','김모씨','뮤지컬','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'공연명2','장소',to_date('2022-08-01 13:00','yyyy-mm-dd hh24:mi'),'12분','100000원','김모씨','뮤지컬','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'공연명3','장소',to_date('2022-12-01 21:00','yyyy-mm-dd hh24:mi'),'60분','100000원','박모씨','콘서트','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'공연명3','장소',to_date('2022-04-01 21:00','yyyy-mm-dd hh24:mi'),'60분','100000원','방모씨','연극','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'공연명100','장소',to_date('2022-04-24 21:00','yyyy-mm-dd hh24:mi'),'60분','100000원','방모씨','연극','10');
UPDATE PERFORMANCE SET per_seat=12 WHERE per_no = 1;
DELETE FROM PERFORMANCE WHERE per_no = 1;

--main페이지
--1. 회원가입
INSERT INTO MEMBER VALUES('id','pw','김','aa','101');
INSERT INTO MEMBER VALUES('id2','pw','김','aa','101');
--2. 로그인(success,fail)
SELECT M_ID FROM MEMBER WHERE M_ID ='id';--id:static/변수으로 빼놓기
--3. 공연조회(제목별, 카테고리별, 전체조회->>관심등록 --뒤로가기While)
SELECT * FROM PERFORMANCE WHERE per_title = '공연명' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '뮤지컬' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '콘서트' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '연극' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '클래식' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE PER_DATE > sysdate ORDER BY PER_DATE;

--syso>wishlist에 담을 공연의 번호를 입력하세요
--이미 wishlist에 담겨있을경우 (m_id = 어쩌구 && per_no=해당 && wish_see = 'N') > [알림]이미 담겨있습니다.
----wishlist 담겨있지만 wish_see = 'Y' 인 경우 > [알림]추가되었습니다. -> wish_see = 'N'로 바꾸기
----wishlist 담겨있지 않을경우 > [알림]추가되었습니다.
--> 중복 추가하도록 변경 && per_no = 1 && wish_see = 'N'
INSERT INTO WISHLIST
SELECT seq_wishno.nextval, 'id','10', 'N'
FROM dual
WHERE NOT EXISTS (
	SELECT M_ID , PER_NO , WISH_SEE 
	FROM WISHLIST
	WHERE m_id = 'id'
	AND per_no = '10'
	AND wish_see ='N');
SELECT * FROM WISHLIST w2;

--4. 예매하기 (해당 회원의 관심목록 출력) --<**님의 wishlist> OR [알림]wishlist가 비었습니다. 조회후 wish 등록해주세요
SELECT w.M_ID, w.WISH_NO "공연넘버(NO)", p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE  
FROM WISHLIST w JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO 
WHERE M_ID = 'id' 
AND WISH_SEE ='N';
----1.예매하기 (N만 가능함)->공연넘버(NO) 선택하세요
INSERT INTO TICKET VALUES(seq_ticno.nextval, 166, sysdate);
UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT-1 WHERE per_no = 10;
UPDATE WISHLIST SET WISH_SEE = 'Y' WHERE WISH_NO = 166;
----2.뒤로가기while
--5. 마이페이지
----1.개인정보수정(비밀번호만)
UPDATE MEMBER SET m_pw='pw2' WHERE m_id ='id';
----2.예매확인 - 조회
SELECT t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id';
--2.예매취소 - 삭제
--syso>> 예매 취소는 공연 당일의 전날까지만 가능합니다. \n [취소 가능한 예매내역]
SELECT t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id'
AND p.per_date - 1 > sysdate;
--syso>>예매번호입력하세요
DELETE FROM TICKET WHERE tic_no = 3;
----3.관심목록조회
SELECT * FROM WISHLIST WHERE M_ID = 'id';
----4.로그아웃 id=null
----5.탈퇴(예매내역 있으면 탈퇴불가)>[알림]탈퇴할 수 없습니다.
SELECT t.TIC_NO, p.PER_DATE
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id' AND p.PER_DATE > sysdate;
DELETE FROM "MEMBER" WHERE M_ID = 'id';





