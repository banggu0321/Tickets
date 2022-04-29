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
	m_email VARCHAR2(30) CONSTRAINT member_email_ck NOT NULL ,
	m_phone VARCHAR2(20)
);

--Performance
CREATE TABLE PERFORMANCE(
	per_no NUMBER PRIMARY KEY,
	per_title VARCHAR2(30) NOT NULL,
	per_location VARCHAR2(50) NOT NULL,
	per_date DATE NOT NULL,
	per_time VARCHAR2(10) NOT NULL,
	per_price VARCHAR2(20) NOT NULL,
	per_cast VARCHAR2(20) NOT NULL,
	per_category VARCHAR2(20) NOT NULL,
	per_seat NUMBER NOT null,
 	constraint per_category_ck check(per_category in ('뮤지컬','콘서트','연극','무용'))
);

--Wishlist
CREATE TABLE WISHLIST(
	wish_no NUMBER PRIMARY KEY,
	m_id VARCHAR2(20) NOT NULL,
	per_no NUMBER  NOT NULL,
	wish_see VARCHAR2(3) DEFAULT 'N',
	constraint wish_see_ck check(wish_see in ('N','Y')),
	constraint wish_mem_id_fk foreign key(m_id) references MEMBER(m_id) ON DELETE CASCADE,
	constraint wish_per_no_fk foreign key(per_no) references PERFORMANCE(per_no) ON DELETE CASCADE
);
--X ALTER TABLE WISHLIST ADD UNIQUE (m_id,per_no);

--Ticket
CREATE TABLE TICKET(
	tic_no NUMBER PRIMARY KEY,
	wish_no NUMBER NOT NULL,
	tic_date DATE  NOT NULL,
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
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'지킬앤하이드','샤롯데씨어터',to_date('2021-10-20 19:00','yyyy-mm-dd hh24:mi'),'170분','150,000원','박은태','뮤지컬','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'데스노트','충무아트센터',to_date('2022-04-01 21:00','yyyy-mm-dd hh24:mi'),'160분','150,000원','홍광호','뮤지컬','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'마타하리','샤롯데씨어터',to_date('2022-05-28 19:30','yyyy-mm-dd hh24:mi'),'180분','150,000원','옥주현','뮤지컬','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'웃는 남자','세종문화회관',to_date('2022-06-10 21:00','yyyy-mm-dd hh24:mi'),'180분','150,000원','박효신','뮤지컬','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'May, Be','신한pLay',to_date('2022-04-29 19:00','yyyy-mm-dd hh24:mi'),'120분','990,000원','케이시','콘서트','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'재즈페스티벌','올림픽공원',to_date('2022-05-27 13:00','yyyy-mm-dd hh24:mi'),'540분','165,000원','백예린','콘서트','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'라스트 세션','대학로 1관',to_date('2022-03-20 20:00','yyyy-mm-dd hh24:mi'),'90분','50,000원','신구','연극','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'쉬어매드니스','서울 대학로',to_date('2022-05-31 15:00','yyyy-mm-dd hh24:mi'),'110분','35,000원','방모씨','연극','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'해적','예술의전당',to_date('2022-04-20 19:00','yyyy-mm-dd hh24:mi'),'120분','30,000원','국립발레단','무용','10');
UPDATE PERFORMANCE SET per_seat=0 WHERE per_no = 1;
DELETE FROM PERFORMANCE WHERE per_no = 1;

--=====main페이지==========================================
--(1~5)
		--@@작업을 선택하세요>>
--1. 회원가입
INSERT INTO MEMBER VALUES('id','pw','김','aa','101');
INSERT INTO MEMBER VALUES('id2','pw','김','aa','101');
INSERT INTO MEMBER VALUES('id3','pw','김','aa','101');
INSERT INTO MEMBER VALUES('d','d','김','aa','101');
--2. 로그인(success,fail)
SELECT M_ID FROM MEMBER WHERE M_ID ='id' AND M_PW ='pw2';
		--@@.JAVA id->static/변수으로 빼놓기(String user_id = null)
		--@@[알림] **님 로그인되었습니다.
		--@@[알림] 회원이 존재하지 않습니다.

--3. 공연조회:제목별으로 검색, 카테고리별, 예매가능한 공연, 전체조회->>관심등록 --뒤로가기While
---- 지난 공연과 예매가능한 공연 나눠서 출력
SELECT * FROM PERFORMANCE WHERE per_title LIKE '%아이%' ORDER BY PER_NO ; --fail
SELECT * FROM PERFORMANCE WHERE per_category = '뮤지컬' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE per_category = '콘서트' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE per_category = '연극' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE per_category = '클래식' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE PER_DATE > sysdate ORDER BY PER_NO ;
		--@@syso>[알림]로그인이 필요합니다.
		--@@syso>wishlist에 담을 공연의 번호를 입력하세요

--wishlist 담겨있지만 wish_see = 'Y' 인 경우 > @@[알림]추가되었습니다.
--wishlist 담겨있지 않을경우 > @@[알림]추가되었습니다.
--중복으로 값 들어가지 않게 -> m_id = 어쩌구 && per_no = 해당 && wish_see = 'N' 가 아닌 경우만 insert
INSERT INTO WISHLIST
SELECT seq_wishno.nextval, 'id','1', 'N'
FROM dual
WHERE NOT EXISTS (
	SELECT M_ID , PER_NO , WISH_SEE 
	FROM WISHLIST
	WHERE m_id = 'id'
	AND per_no = '1'
	AND wish_see ='N');
-->분리해서 조건검색 후 result 값으로 조건문 만듬
SELECT M_ID , PER_NO , WISH_SEE 
FROM WISHLIST
WHERE m_id = 'id'''
	AND per_no = '10'
	AND wish_see ='N';
INSERT INTO WISHLIST VALUES(seq_wishno.nextval, 'id','10', 'N');
SELECT * FROM WISHLIST w2;


--4. 예매하기 (해당 회원의 관심목록 출력)  w.M_ID->string 값으로 가지고 다니기 예매가능한wishlist만
		--@@<**님의 wishlist> OR [알림]wishlist가 비었습니다. 조회 후 wish 등록해주세요 ->뒤로가기
SELECT w.M_ID, w.WISH_NO 공연넘버, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE  
FROM WISHLIST w INNER JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO 
WHERE w.M_ID = 'id4'
AND p.per_seat <> 0
AND w.WISH_SEE ='N'
AND PER_DATE > sysdate
ORDER BY w.WISH_NO ;--지난공연 제외
----1.예매하기 (N만 가능함)		-
		--@@공연넘버(NO) 선택하세요 
INSERT INTO TICKET VALUES(seq_ticno.nextval, 186, sysdate);
--관심번호를 가지고 per_no가져와야함..--1
SELECT w.per_no
FROM WISHLIST w JOIN TICKET t ON t.WISH_NO = w.WISH_NO 
WHERE t.WISH_NO = 182;

UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT-1 WHERE PER_NO = 11;

--관심번호가지고 와야함--2
UPDATE WISHLIST SET WISH_SEE = 'Y' WHERE WISH_NO = 166;
		--@@[알림]예매가 완료되었습니다.
----2.뒤로가기while
--5. 마이페이지
----1.개인정보수정(비밀번호만)- id, pw 다시입력
SELECT * FROM "MEMBER" m WHERE m_id ='id' AND M_PW = 'pw2';
UPDATE MEMBER SET m_pw='pw3' WHERE m_id ='id';
		--@@[알림] 비밀번호가 수정되었습니다.
----2.예매확인 - 조회
		--@@<**님의 예매 내역>  w.M_ID->string 값으로 가지고 다니기
SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id4'
ORDER BY t.TIC_NO;
--3.예매취소 - 삭제
		--@@syso>> 예매 취소는 공연 당일의 전날까지만 가능합니다. \n [취소 가능한 예매내역]
SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id4'
AND p.per_date - 1 > sysdate
ORDER BY t.TIC_NO;
		--syso>>예매번호입력하세요
DELETE FROM TICKET WHERE tic_no = 3;

--tic_no 가지고 wish_no가져와야함.
SELECT WISH_NO FROM TICKET WHERE TIC_NO = 3; --SELECT_WISHNO_TICNO
UPDATE WISHLIST SET WISH_SEE = 'N' WHERE WISH_NO = 1; --UPDATE_DEL_TICKET_WISH
SELECT PER_NO FROM WISHLIST WHERE WISH_NO = 1; --SELECT_PERNO_WISHNO
UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT + 1 WHERE PER_NO = 5; --UPDATE_DEL_TICKET_SEAT

----4.관심목록조회
SELECT w.M_ID, w.WISH_NO, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE  
FROM PERFORMANCE p inner JOIN (
	SELECT *
	FROM (
		SELECT wf.* , ROW_NUMBER() OVER(PARTITION BY PER_NO ORDER BY wish_NO desc) AS num
		FROM WISHLIST wf) wj
	WHERE wj.num = 1
	AND wj.M_ID = 'd' 
	ORDER BY wish_no) w
	ON w.PER_NO = p.PER_NO 
ORDER BY w.WISH_NO;

SELECT *
FROM (SELECT wf.* , ROW_NUMBER() OVER(PARTITION BY PER_NO ORDER BY wish_NO desc) AS num
		FROM WISHLIST wf) wj
WHERE wj.num = 1
AND M_ID =104 'd' 
ORDER BY wish_no;

WHERE M_ID = 'd' 
;
----5.로그아웃 id=null
----6.탈퇴(예매내역 있으면 탈퇴불가)	>@@[알림]탈퇴할 수 없습니다.
SELECT t.TIC_NO, p.PER_DATE
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id4' AND p.PER_DATE > sysdate;
DELETE FROM "MEMBER" WHERE M_ID = 'id';





