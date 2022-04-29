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

--�������� ��� ����
purge recycleBIN;

--===���̺� ����=================================
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
 	constraint per_category_ck check(per_category in ('������','�ܼ�Ʈ','����','����'))
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

--���̺� ��ȸ
SELECT * FROM "MEMBER" m;
SELECT * FROM PERFORMANCE ;
SELECT * FROM wishlist;
SELECT * FROM ticket;

--���̺� ����
DROP TABLE MEMBER cascade constraint;
DROP TABLE performance cascade constraint;
DROP TABLE wishlist cascade CONSTRAINT ;
DROP TABLE ticket cascade CONSTRAINT ;


--===������ ����=================================
--SEQUENCE
CREATE SEQUENCE SEQ_PERNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_TICNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_WISHNO INCREMENT BY 1 START WITH 1;

--������ ����
DROP SEQUENCE SEQ_PERNO;
DROP SEQUENCE SEQ_TICNO;
DROP SEQUENCE SEQ_WISHNO;


--===JDBC���α׷� SQL�ۼ�=================================
--������ ���̵� ��� ����
--INSERT INTO MEMBER VALUES('admin','admin1234','������','admin@gmai.com','010-1234-5678');
--�����ϱ�(�ٸ� sql���� �����)
--1.��ȸ(����,  ȸ��, ����) ->��ü��ȸ
SELECT * FROM PERFORMANCE;
SELECT * FROM MEMBER;
SELECT t.TIC_NO, m.M_ID, m.M_NAME, p.PER_NO
FROM WISHLIST w RIGHT OUTER join TICKET t ON w.WISH_NO = t.WISH_NO 
				JOIN PERFORMANCE p ON w.per_no = p.PER_NO 
				JOIN MEMBER m ON w.M_ID = m.M_ID;
--2.���� ���, �¼� �� ����, ����
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'��ų�����̵�','���Ե�������',to_date('2021-10-20 19:00','yyyy-mm-dd hh24:mi'),'170��','150,000��','������','������','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'������Ʈ','�湫��Ʈ����',to_date('2022-04-01 21:00','yyyy-mm-dd hh24:mi'),'160��','150,000��','ȫ��ȣ','������','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'��Ÿ�ϸ�','���Ե�������',to_date('2022-05-28 19:30','yyyy-mm-dd hh24:mi'),'180��','150,000��','������','������','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'���� ����','������ȭȸ��',to_date('2022-06-10 21:00','yyyy-mm-dd hh24:mi'),'180��','150,000��','��ȿ��','������','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'May, Be','����pLay',to_date('2022-04-29 19:00','yyyy-mm-dd hh24:mi'),'120��','990,000��','���̽�','�ܼ�Ʈ','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'�����佺Ƽ��','�ø��Ȱ���',to_date('2022-05-27 13:00','yyyy-mm-dd hh24:mi'),'540��','165,000��','�鿹��','�ܼ�Ʈ','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'��Ʈ ����','���з� 1��',to_date('2022-03-20 20:00','yyyy-mm-dd hh24:mi'),'90��','50,000��','�ű�','����','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'����ŵ�Ͻ�','���� ���з�',to_date('2022-05-31 15:00','yyyy-mm-dd hh24:mi'),'110��','35,000��','���','����','10');
INSERT INTO PERFORMANCE VALUES(seq_perno.nextval,'����','����������',to_date('2022-04-20 19:00','yyyy-mm-dd hh24:mi'),'120��','30,000��','�����߷���','����','10');
UPDATE PERFORMANCE SET per_seat=0 WHERE per_no = 1;
DELETE FROM PERFORMANCE WHERE per_no = 1;

--=====main������==========================================
--(1~5)
		--@@�۾��� �����ϼ���>>
--1. ȸ������
INSERT INTO MEMBER VALUES('id','pw','��','aa','101');
INSERT INTO MEMBER VALUES('id2','pw','��','aa','101');
INSERT INTO MEMBER VALUES('id3','pw','��','aa','101');
INSERT INTO MEMBER VALUES('d','d','��','aa','101');
--2. �α���(success,fail)
SELECT M_ID FROM MEMBER WHERE M_ID ='id' AND M_PW ='pw2';
		--@@.JAVA id->static/�������� ������(String user_id = null)
		--@@[�˸�] **�� �α��εǾ����ϴ�.
		--@@[�˸�] ȸ���� �������� �ʽ��ϴ�.

--3. ������ȸ:�������� �˻�, ī�װ���, ���Ű����� ����, ��ü��ȸ->>���ɵ�� --�ڷΰ���While
---- ���� ������ ���Ű����� ���� ������ ���
SELECT * FROM PERFORMANCE WHERE per_title LIKE '%����%' ORDER BY PER_NO ; --fail
SELECT * FROM PERFORMANCE WHERE per_category = '������' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE per_category = '�ܼ�Ʈ' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE per_category = '����' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE per_category = 'Ŭ����' ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE ORDER BY PER_NO ;
SELECT * FROM PERFORMANCE WHERE PER_DATE > sysdate ORDER BY PER_NO ;
		--@@syso>[�˸�]�α����� �ʿ��մϴ�.
		--@@syso>wishlist�� ���� ������ ��ȣ�� �Է��ϼ���

--wishlist ��������� wish_see = 'Y' �� ��� > @@[�˸�]�߰��Ǿ����ϴ�.
--wishlist ������� ������� > @@[�˸�]�߰��Ǿ����ϴ�.
--�ߺ����� �� ���� �ʰ� -> m_id = ��¼�� && per_no = �ش� && wish_see = 'N' �� �ƴ� ��츸 insert
INSERT INTO WISHLIST
SELECT seq_wishno.nextval, 'id','1', 'N'
FROM dual
WHERE NOT EXISTS (
	SELECT M_ID , PER_NO , WISH_SEE 
	FROM WISHLIST
	WHERE m_id = 'id'
	AND per_no = '1'
	AND wish_see ='N');
-->�и��ؼ� ���ǰ˻� �� result ������ ���ǹ� ����
SELECT M_ID , PER_NO , WISH_SEE 
FROM WISHLIST
WHERE m_id = 'id'''
	AND per_no = '10'
	AND wish_see ='N';
INSERT INTO WISHLIST VALUES(seq_wishno.nextval, 'id','10', 'N');
SELECT * FROM WISHLIST w2;


--4. �����ϱ� (�ش� ȸ���� ���ɸ�� ���)  w.M_ID->string ������ ������ �ٴϱ� ���Ű�����wishlist��
		--@@<**���� wishlist> OR [�˸�]wishlist�� ������ϴ�. ��ȸ �� wish ������ּ��� ->�ڷΰ���
SELECT w.M_ID, w.WISH_NO �����ѹ�, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE  
FROM WISHLIST w INNER JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO 
WHERE w.M_ID = 'id4'
AND p.per_seat <> 0
AND w.WISH_SEE ='N'
AND PER_DATE > sysdate
ORDER BY w.WISH_NO ;--�������� ����
----1.�����ϱ� (N�� ������)		-
		--@@�����ѹ�(NO) �����ϼ��� 
INSERT INTO TICKET VALUES(seq_ticno.nextval, 186, sysdate);
--���ɹ�ȣ�� ������ per_no�����;���..--1
SELECT w.per_no
FROM WISHLIST w JOIN TICKET t ON t.WISH_NO = w.WISH_NO 
WHERE t.WISH_NO = 182;

UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT-1 WHERE PER_NO = 11;

--���ɹ�ȣ������ �;���--2
UPDATE WISHLIST SET WISH_SEE = 'Y' WHERE WISH_NO = 166;
		--@@[�˸�]���Ű� �Ϸ�Ǿ����ϴ�.
----2.�ڷΰ���while
--5. ����������
----1.������������(��й�ȣ��)- id, pw �ٽ��Է�
SELECT * FROM "MEMBER" m WHERE m_id ='id' AND M_PW = 'pw2';
UPDATE MEMBER SET m_pw='pw3' WHERE m_id ='id';
		--@@[�˸�] ��й�ȣ�� �����Ǿ����ϴ�.
----2.����Ȯ�� - ��ȸ
		--@@<**���� ���� ����>  w.M_ID->string ������ ������ �ٴϱ�
SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id4'
ORDER BY t.TIC_NO;
--3.������� - ����
		--@@syso>> ���� ��Ҵ� ���� ������ ���������� �����մϴ�. \n [��� ������ ���ų���]
SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY 
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id4'
AND p.per_date - 1 > sysdate
ORDER BY t.TIC_NO;
		--syso>>���Ź�ȣ�Է��ϼ���
DELETE FROM TICKET WHERE tic_no = 3;

--tic_no ������ wish_no�����;���.
SELECT WISH_NO FROM TICKET WHERE TIC_NO = 3; --SELECT_WISHNO_TICNO
UPDATE WISHLIST SET WISH_SEE = 'N' WHERE WISH_NO = 1; --UPDATE_DEL_TICKET_WISH
SELECT PER_NO FROM WISHLIST WHERE WISH_NO = 1; --SELECT_PERNO_WISHNO
UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT + 1 WHERE PER_NO = 5; --UPDATE_DEL_TICKET_SEAT

----4.���ɸ����ȸ
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
----5.�α׾ƿ� id=null
----6.Ż��(���ų��� ������ Ż��Ұ�)	>@@[�˸�]Ż���� �� �����ϴ�.
SELECT t.TIC_NO, p.PER_DATE
FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)
				JOIN PERFORMANCE p USING(per_no)
WHERE M_ID = 'id4' AND p.PER_DATE > sysdate;
DELETE FROM "MEMBER" WHERE M_ID = 'id';





