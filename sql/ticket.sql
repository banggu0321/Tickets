--========================================
--TICKETS
--========================================

--������ �������� tickets���� ����
--CREATE USER TICKETS IDENTIFIED BY 0321;

--tickets���� (��� ����)
--GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO TICKETS IDENTIFIED BY 0321;
--ALTER USER TICKETS DEFAULT TABLESPACE USERS;
--ALTER USER TICKETS TEMPORARY TABLESPACE TEMP;

--����ڻ���
--SQL> drop user ticket cascade;

--======������ ���̺�Ȯ��==============================
SELECT * FROM user_tables;
SELECT * FROM user_sequences;
SELECT * FROM USER_CONSTRAINTS ;

--===���̺� ����=================================
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
 	constraint per_category_ck check(per_category in ('������','�ܼ�Ʈ','����', 'Ŭ����'))
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


--===������ ����=================================
--SEQUENCE
CREATE SEQUENCE SEQ_PERNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_TICNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_WISHNO INCREMENT BY 1 START WITH 1;

DROP SEQUENCE SEQ_PERNO;
DROP SEQUENCE SEQ_TICNO;
DROP SEQUENCE SEQ_WISHNO;


--===JDBC���α׷� SQL�ۼ�=================================
--������ ���̵� ��� ����
INSERT INTO MEMBER VALUES('admin','admin1234','������','admin@gmai.com','010-1234-5678');
--������ ���� ���ӽ�
--1.��ȸ(����,  ȸ��, ����)
SELECT * FROM PERFORMANCE;
SELECT * FROM MEMBER;
SELECT * FROM TICKET;
--2.���� ���, �¼� �� ����, ����
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'������','���',to_date('2022-08-01 13:00','yyyy-mm-dd hh24:mi'),'12��','100000��','���','������','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'������2','���',to_date('2022-08-01 13:00','yyyy-mm-dd hh24:mi'),'12��','100000��','���','������','10');
UPDATE PERFORMANCE SET per_seat=4 WHERE per_no = 1;
DELETE FROM PERFORMANCE WHERE per_no = 1;

--main������
--1. ȸ������
INSERT INTO MEMBER VALUES('id','pw','��','aa','101');
INSERT INTO MEMBER VALUES('id2','pw','��','aa','101');
--2. �α���(success,fail)
SELECT M_ID FROM MEMBER WHERE M_ID ='id';--id:static���� ������, 
--3. ������ȸ(����, ī�װ���, ��ü��ȸ->>���ɵ�� --�ڷΰ���While)
SELECT * FROM PERFORMANCE WHERE per_title = '������' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '������' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '�ܼ�Ʈ' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = '����' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE per_category = 'Ŭ����' AND PER_DATE > sysdate ORDER BY PER_DATE ;
SELECT * FROM PERFORMANCE WHERE PER_DATE > sysdate ORDER BY PER_DATE;

INSERT INTO WISHLIST (wish_no, m_id, per_no) values(seq_wishno.nextval, 'id2','1'); --if������ wish_see='N'Ȯ��
INSERT INTO WISHLIST (wish_no, m_id, per_no) values(seq_wishno.nextval, 'id','2');

--4. �����ϱ� (�ش� ȸ���� ���ɸ�� ���) --<**���� wishlist> OR [�˸�]wishlist�� ������ϴ�. ��ȸ�� wish ������ּ���
SELECT p.PER_NO, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE  
FROM WISHLIST w JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO 
WHERE M_ID = 'id' AND WISH_SEE ='N';
----1.�����ϱ� (N�� ������)
INSERT INTO TICKET VALUES(seq_ticno.nextval, 2, sysdate);
UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT-1 WHERE per_no = 2;
UPDATE WISHLIST SET WISH_SEE = 'Y' WHERE per_no = 2;
----2.�ڷΰ���while
--5. ����������
----1.������������
UPDATE MEMBER SET m_pw='pw2', m_name='��2', m_email='aa2', m_phone='102' WHERE m_id ='id';
----2.����Ȯ��/��� - ��ȸ, ����
SELECT t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id2';

DELETE FROM TICKET WHERE tic_no = 3 AND TIC_DATE < sysdate;-->��¥�� �����ϰ��
----3.���ɸ����ȸ
SELECT * FROM WISHLIST WHERE M_ID = 'id';
----4.�α׾ƿ� id=null
----5.Ż��(���ų��� ������ Ż��Ұ�)
DELETE FROM "MEMBER" WHERE M_ID = 'id';





