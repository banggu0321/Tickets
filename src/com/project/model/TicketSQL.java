package com.project.model;

public class TicketSQL {
	static final String SQL_MEM_INSERT = "INSERT INTO MEMBER VALUES(?,?,?,?,?)";
	static final String SQL_MEM_LOGIN = "SELECT * FROM MEMBER WHERE M_ID =? AND M_PW =?";
	static final String SQL_SELECT_TITLE = "SELECT * FROM PERFORMANCE WHERE per_title = ?  ORDER BY PER_NO";
	static final String SQL_SELECT_CAT = "SELECT * FROM PERFORMANCE WHERE per_category = ?  ORDER BY PER_NO";
	static final String SQL_SELECT_ALL = "SELECT * FROM PERFORMANCE ORDER BY PER_NO";
	static final String SQL_SELECT_POSSIBLE = "SELECT * FROM PERFORMANCE WHERE PER_DATE > sysdate ORDER BY PER_NO";
	static final String SQL_INSERT_WISH_SEARCH = "SELECT M_ID , PER_NO , WISH_SEE FROM WISHLIST WHERE m_id = ? AND per_no = ? AND wish_see ='N'";
	static final String SQL_INSERT_WISH = "INSERT INTO WISHLIST VALUES(seq_wishno.nextval, ?, ?, 'N')";
	static final String SQL_SELECT_WISH_FOR_BUY = 
					" SELECT w.M_ID, w.WISH_NO, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE "
					+ " FROM WISHLIST w INNER JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO"
					+ " WHERE w.M_ID = ?"
					+ " AND p.per_seat <> 0"
					+ " AND w.WISH_SEE ='N'"
					+ " AND PER_DATE > sysdate"
					+ " ORDER BY w.WISH_NO";
	static final String SQL_INSERT_TICKET = "INSERT INTO TICKET VALUES(seq_ticno.nextval, ?, sysdate)";
	static final String SQL_TICKET_SELECT_PER = 
					"SELECT w.per_no"
					+ " FROM WISHLIST w JOIN TICKET t ON t.WISH_NO = w.WISH_NO"
					+ " WHERE t.WISH_NO = ?";
	static final String SQL_TICKET_UPDATE_SEAT = "UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT-1 WHERE PER_NO = ?";//À¸¾Æ¾Æ¾Æ¾Ç
	static final String SQL_TICKET_UPDATE_WISH = "UPDATE WISHLIST SET WISH_SEE = 'Y' WHERE WISH_NO = ?";
	static final String SQL_UPDATE_MEM_SEARCH = "SELECT * FROM MEMBER WHERE m_id =? AND M_PW = ?";
	static final String SQL_UPDATE_MEM = "UPDATE MEMBER SET m_pw=? WHERE m_id =?";
	static final String SQL_SELECT_TICKET_BUY = 
					"SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY"
					+ " FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)"
					+ "					JOIN PERFORMANCE p USING(per_no)"
					+ " WHERE M_ID = ?"
					+ " ORDER BY t.TIC_NO";
	static final String SQL_DELETE_TICKET_SEARCH = 
					"SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY"
					+ " FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)"
					+ "					JOIN PERFORMANCE p USING(per_no)"
					+ " WHERE M_ID = ?"
					+ " AND p.per_date - 1 > sysdate"
					+ " ORDER BY t.TIC_NO";
	static final String SQL_DELETE_TICKET = "DELETE FROM TICKET WHERE tic_no = ?";
	static final String SQL_SELECT_WISH_MYPAGE = 
					"SELECT w.M_ID, w.WISH_NO, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE"
					+ " FROM WISHLIST w INNER JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO"
					+ " WHERE M_ID = ?"
					+ " ORDER BY w.WISH_NO";
	static final String SQL_DELETE_MEM_SEARCH = ""
					+ "SELECT t.TIC_NO, p.PER_DATE"
					+ " FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)"
					+ "					JOIN PERFORMANCE p USING(per_no)"
					+ " WHERE M_ID = ? AND p.PER_DATE > sysdate";
	static final String SQL_DELETE_MEM = "DELETE FROM MEMBER WHERE M_ID = ?";
}
