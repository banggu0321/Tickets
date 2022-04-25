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

	// 3-4. 관심리스트 추가
	public int wishlistInsert(WishlistVO wish) {
		return 0;
	}

	// 4. 예매하기
	// 4-1. 예매가능 관심리스트 조회 WishPerVO
	public List<WishPerVO> selectWish_Forbuy(String id) {
		return null;
	}

	// 4-2. insert, update(좌석-1, See->Y)
	public int ticketInsert(TicketVO ticket) {
		return 0;
	}

	public int ticSeatUpdate(PerformanceVO tic_seat) {
		return 0;
	}

	public int ticWishUpdate(WishlistVO tic_wish) {
		return 0;
	}

	// 5. 마이페이지
	// 5-1. 비밀번호 수정 (id다시입력)
	public int pwUpdate(MemberVO mem) {
		return 0;
	}

	// 5-2. 예매 확인
	public List<TicketWishPerVO> selectTicketBuy(String id) {
		return null;
	}

	// 5-3. 예매 취소 -> 가능 조회
	public List<TicketWishPerVO> selectTicketDel(String id) {
		return null;
	}

	// 5-3. 예매 delete
	public int ticketDelete(int ticNum) {
		return 0;
	}

	// 5-4. 관심리스트 조회
	public List<WishPerVO> selectWish_mypage(String id) {
		return null;
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
