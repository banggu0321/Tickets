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
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
	m_id varchar2(20) CONSTRAINT member_id_pk PRIMARY KEY,
	m_pw varchar2(20) CONSTRAINT member_pw_ck NOT NULL,
	m_name VARCHAR2(20) CONSTRAINT member_name_ck NOT NULL ,
	m_email VARCHAR2(25) CONSTRAINT member_email_ck NOT NULL ,
	m_phone VARCHAR2(15)
);
SELECT * FROM "MEMBER" m;

--Performance
DROP TABLE performance;
CREATE TABLE PERFORMANCE(
	per_no NUMBER PRIMARY KEY,
	per_title VARCHAR2(20) NOT NULL,
	per_location VARCHAR2(20) NOT NULL,
	per_date DATE NOT NULL,
	per_time VARCHAR2(10) NOT NULL,
	per_price VARCHAR2(20) NOT NULL,
	per_cast VARCHAR2(10) NOT NULL,
	per_category VARCHAR2(20) NOT NULL,
	per_seat NUMBER,
 	constraint per_category_ck check(per_category in ('뮤지컬','콘서트','연극', '클래식'))
);
SELECT * FROM PERFORMANCE ;

--Ticket
DROP TABLE ticket;
CREATE TABLE TICKET(
	tic_no NUMBER PRIMARY KEY,
	m_id VARCHAR2(20),
	per_no NUMBER,
	tic_date DATE
);
SELECT * FROM ticket;

--Wishlist
DROP TABLE wishlist;
CREATE TABLE WISHLIST(
	wish_no NUMBER PRIMARY KEY,
	m_id VARCHAR2(20),
	per_no NUMBER,
	wish_see char(1) DEFAULT 'N'
);
SELECT * FROM wishlist;

--SEQUENCE
CREATE SEQUENCE SEQ_PERNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_TICNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_WISHNO INCREMENT BY 1 START WITH 1;






