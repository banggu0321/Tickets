package com.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketVO;
import com.project.dto.TicketWishPerVO;
import com.project.dto.WishPerVO;
import com.project.dto.WishlistVO;
import com.project.util.DBUtil;

public class TicketDAO {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;

	static final String SQL_MEM_INSERT = "INSERT INTO MEMBER VALUES(?,?,?,?,?)";
	static final String SQL_MEM_LOGIN = "SELECT * FROM MEMBER WHERE M_ID =? AND M_PW =?";
	static final String SQL_SELECT_TITLE = "SELECT * FROM PERFORMANCE WHERE per_title = ? AND PER_DATE > sysdate ORDER BY PER_DATE";
	static final String SQL_SELECT_CAT = "SELECT * FROM PERFORMANCE WHERE per_category = ? AND PER_DATE > sysdate ORDER BY PER_DATE";
	static final String SQL_SELECT_ALL = "SELECT * FROM PERFORMANCE WHERE PER_DATE > sysdate ORDER BY PER_DATE";
	static final String SQL_INSERT_WISH_SEARCH = "SELECT M_ID , PER_NO , WISH_SEE FROM WISHLIST WHERE m_id = ? AND per_no = ? AND wish_see ='N'";
	static final String SQL_INSERT_WISH = "INSERT INTO WISHLIST VALUES(seq_wishno.nextval, ?, ?, 'N')";
	static final String SQL_SELECT_WISH_FOR_BUY = 
					" SELECT w.M_ID, w.WISH_NO, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE "
					+ " FROM WISHLIST w INNER JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO"
					+ " WHERE w.M_ID = ?"
					+ " AND p.per_seat <> 0"
					+ " AND w.WISH_SEE ='N'"
					+ " ORDER BY w.WISH_NO";
	static final String SQL_INSERT_TICKET = "INSERT INTO TICKET VALUES(seq_ticno.nextval, ?, sysdate)";
	static final String SQL_TICKET_SELECT_PER = 
					"SELECT w.per_no"
					+ " FROM WISHLIST w JOIN TICKET t ON t.WISH_NO = w.WISH_NO"
					+ " WHERE t.WISH_NO = ?";
	static final String SQL_TICKET_UPDATE_SEAT = "UPDATE PERFORMANCE SET PER_SEAT = PER_SEAT-1 WHERE PER_NO = ?";//으아아아악
	static final String SQL_TICKET_UPDATE_WISH = "UPDATE WISHLIST SET WISH_SEE = 'Y' WHERE WISH_NO = ?";
	static final String SQL_UPDATE_MEM_SEARCH = "SELECT * FROM MEMBER WHERE m_id =? AND M_PW = ?";
	static final String SQL_UPDATE_MEM = "UPDATE MEMBER SET m_pw=? WHERE m_id =?";
	static final String SQL_SELECT_TICKET_BUY = 
					"SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY"
					+ " FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)"
					+ "					JOIN PERFORMANCE p USING(per_no)"
					+ " WHERE M_ID = ?"
					+ " ORDER BY t.TIC_NO";
	static final String SQL_SELECT_TICKET_DEL_SEARCH = 
					"SELECT w.M_ID, t.TIC_NO , t.TIC_DATE, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY"
					+ " FROM WISHLIST w RIGHT OUTER join TICKET t using(wish_no)"
					+ "					JOIN PERFORMANCE p USING(per_no)"
					+ " WHERE M_ID = ?"
					+ " AND p.per_date - 1 > sysdate"
					+ " ORDER BY t.TIC_NO";
	static final String SQL_SELECT_TICKET_DEL = "DELETE FROM TICKET WHERE tic_no = ?";
	static final String SQL_SELECT_WISH_MYPAGE = 
					"SELECT w.M_ID, w.WISH_NO, p.PER_TITLE , p.PER_LOCATION , p.PER_DATE ,p.PER_TIME ,p.PER_PRICE ,p.PER_CAST , p.PER_CATEGORY ,p.PER_SEAT , w.WISH_SEE"
					+ " FROM WISHLIST w INNER JOIN PERFORMANCE p ON w.PER_NO = p.PER_NO"
					+ " WHERE M_ID = ?"
					+ " ORDER BY w.WISH_NO";
	
	// 1. 회원가입
	public int memberInsert(MemberVO mem) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_MEM_INSERT);
			pst.setString(1, mem.getM_id());
			pst.setString(2, mem.getM_pw());
			pst.setString(3, mem.getM_name());
			pst.setString(4, mem.getM_email());
			pst.setString(5, mem.getM_phone());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("❌이미 존재하는 아이디거나 양식에 맞지 않습니다.❌");
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// 2. 로그인
	public MemberVO memberLogIn(String mem_id, String mem_pw) {
		MemberVO mem = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_MEM_LOGIN);
			pst.setString(1, mem_id);
			pst.setString(2, mem_pw);
			rs = pst.executeQuery();
			while(rs.next()) {
				mem = memInfo(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return mem;
	}

	private MemberVO memInfo(ResultSet rs) throws SQLException {
		MemberVO mem = new MemberVO();
		mem.setM_id(rs.getString("M_ID"));
		mem.setM_pw(rs.getString("M_PW"));
		mem.setM_name(rs.getString("M_NAME"));
		mem.setM_email(rs.getString("M_EMAIL"));
		mem.setM_phone(rs.getString("M_PHONE"));
		return mem;
	}

	// 3. 공연조회
	private PerformanceVO perlist(ResultSet rs) throws SQLException {
		PerformanceVO per = new PerformanceVO();
		per.setPer_no(rs.getInt("PER_NO"));
		per.setPer_title(rs.getString("PER_TITLE"));
		per.setPer_location(rs.getString("PER_LOCATION"));
		per.setPer_date(rs.getDate("PER_DATE"));
		per.setPer_time(rs.getString("PER_TIME"));
		per.setPer_price(rs.getString("PER_PRICE"));
		per.setPer_cast(rs.getString("PER_CAST"));
		per.setPer_category(rs.getString("PER_CATEGORY"));
		per.setPer_seat(rs.getInt("PER_SEAT"));
		return per;
	}

	// 3-1. 제목별 조회
	public List<PerformanceVO> selectPer_Title(String title) {
		List<PerformanceVO> perlist = new ArrayList<PerformanceVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_TITLE);
			pst.setString(1, title);
			rs = pst.executeQuery();
			while(rs.next()) {
				perlist.add(perlist(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return perlist;
	}

	// 3-2. 카테고리별 조회
	public List<PerformanceVO> selectPer_Cat(String category) {
		List<PerformanceVO> perlist = new ArrayList<PerformanceVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_CAT);
			pst.setString(1, category);
			rs = pst.executeQuery();
			while(rs.next()) {
				perlist.add(perlist(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return perlist;
	}

	// 3-3. 전체 조회
	public List<PerformanceVO> selectAll() {
		List<PerformanceVO> perlist = new ArrayList<PerformanceVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pst.executeQuery();
			while(rs.next()) {
				perlist.add(perlist(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return perlist;
	}

	// 3-4. 관심리스트 추가 (조건 확인)
	public int wishlistInsertSearch(WishlistVO wish, String id, int per_no) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_WISH_SEARCH);
			pst.setString(1, id);
			pst.setInt(2, per_no);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(id);
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	public int wishlistInsert(WishlistVO wish) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_WISH);
			pst.setString(1, wish.getM_id());
			pst.setInt(2, wish.getPer_no());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// 4. 예매하기
	// 4-1. 예매가능 관심리스트 조회 WishPerVO
	public List<WishPerVO> selectWish_Forbuy(String id) {
		List<WishPerVO> wishperlist = new ArrayList<WishPerVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_WISH_FOR_BUY);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				wishperlist.add(wishperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return wishperlist;
	}

	private WishPerVO wishperlist(ResultSet rs) throws SQLException {
		WishPerVO wish = new WishPerVO();
		wish.setM_id(rs.getString("M_ID"));
		wish.setWish_no(rs.getInt("WISH_NO"));
		wish.setPer_title(rs.getString("PER_TITLE"));
		wish.setPer_location(rs.getString("PER_LOCATION"));
		wish.setPer_date(rs.getDate("PER_DATE"));
		wish.setPer_time(rs.getString("PER_TIME"));
		wish.setPer_price(rs.getString("PER_PRICE"));
		wish.setPer_cast(rs.getString("PER_CAST"));
		wish.setPer_category(rs.getString("PER_CATEGORY"));
		wish.setPer_seat(rs.getInt("PER_SEAT"));
		wish.setWish_see(rs.getString("WISH_SEE"));
		return wish;
	}

	// 4-2. insert, update(좌석-1, See->Y)
	public int ticketInsert(TicketVO ticket) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT_TICKET);
			pst.setInt(1, ticket.getWish_no());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	public int ticSeatSelect(int wish_no) {
		int per_no = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_TICKET_SELECT_PER);
			pst.setInt(1, wish_no);
			rs = pst.executeQuery();
			while(rs.next()) {
				per_no = rs.getInt("PER_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return per_no;
	}
	
	public int ticSeatUpdate(PerformanceVO tic_seat, int per_no) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_TICKET_UPDATE_SEAT);
			pst.setInt(1, per_no);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	public int ticWishUpdate(WishlistVO tic_wish, int wish_no) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_TICKET_UPDATE_WISH);
			pst.setInt(1, wish_no);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// 5. 마이페이지
	// 5-1. 비밀번호 수정 (id, pw다시입력)
	public int memUpdateSearch(MemberVO mem, String id, String pw) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_MEM_SEARCH);
			pst.setString(1, id);
			pst.setString(2, pw);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(id);
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//SQL_UPDATE_MEM
	public int pwUpdate(MemberVO mem, String id, String pw) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE_MEM);
			pst.setString(1, pw);
			pst.setString(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// 5-2. 예매 확인
	public List<TicketWishPerVO> selectTicketBuy(String id) {
		List<TicketWishPerVO> ticperlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_TICKET_BUY);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ticperlist.add(ticperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return ticperlist;
	}
	
	// 5-3. 예매 취소 -> 가능 조회
	public List<TicketWishPerVO> selectTicketDel(String id) {
		List<TicketWishPerVO> ticperlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_TICKET_DEL_SEARCH);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ticperlist.add(ticperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return ticperlist;
	}
	
	private TicketWishPerVO ticperlist(ResultSet rs) throws SQLException {
		TicketWishPerVO per = new TicketWishPerVO();
		per.setM_id(rs.getString(1));
		per.setTic_no(rs.getInt(2));
		per.setTic_date(rs.getDate(3));
		per.setPer_title(rs.getString(4));
		per.setPer_location(rs.getString(5));
		per.setPer_date(rs.getDate(6));
		per.setPer_time(rs.getString(7));
		per.setPer_price(rs.getString(8));
		per.setPer_cast(rs.getString(9));
		per.setPer_category(rs.getString(10));
		return per;
	}

	// 5-3. 예매 delete
	public int ticketDelete(int ticNum) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_TICKET_DEL);
			pst.setInt(1, ticNum);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// 5-4. 관심리스트 조회 SQL_SELECT_WISH_MYPAGE
	public List<WishPerVO> selectWish_mypage(String id) {
		List<WishPerVO> wishperlist = new ArrayList<WishPerVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_WISH_MYPAGE);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				wishperlist.add(wishperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return wishperlist;
	}

	// 5-5. 로그아웃==?
	// 5-6. 탈퇴 가능 확인
	public List<TicketWishPerVO> selectMemDel(String id) {
		return null;
	}

	// 5-6. delete member
	public int memberDelete(int mem_id) {
		return 0;
	}

	

	
}
