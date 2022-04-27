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
	Ticketlist ticketlist = new Ticketlist();

	// 1. 회원가입
	public int memberInsert(MemberVO mem) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_MEM_INSERT);
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
			pst = conn.prepareStatement(TicketSQL.SQL_MEM_LOGIN);
			pst.setString(1, mem_id);
			pst.setString(2, mem_pw);
			rs = pst.executeQuery();
			while(rs.next()) {
				mem = ticketlist.memInfo(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return mem;
	}

	// 3. 공연조회
	// 3-1. 제목별 조회
	public List<PerformanceVO> selectPer_Title(String title) {
		List<PerformanceVO> perlist = new ArrayList<PerformanceVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_TITLE);
			pst.setString(1, title);
			rs = pst.executeQuery();
			while(rs.next()) {
				perlist.add(ticketlist.perlist(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return perlist;
	}
	//제목별 (NULL확인)
	public int selectPer_TitleInt(String title) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_TITLE);
			pst.setString(1, title);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	// 3-2. 카테고리별 조회
	public List<PerformanceVO> selectPer_Cat(String category) {
		List<PerformanceVO> perlist = new ArrayList<PerformanceVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_CAT);
			pst.setString(1, category);
			rs = pst.executeQuery();
			while(rs.next()) {
				perlist.add(ticketlist.perlist(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return perlist;
	}
	//카테고리별 (NULL확인)
	public int selectPer_CatInt(String category) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_CAT);
			pst.setString(1, category);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// 3-3. 전체 조회
	public List<PerformanceVO> selectAll() {
		List<PerformanceVO> perlist = new ArrayList<PerformanceVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_ALL);
			rs = pst.executeQuery();
			while(rs.next()) {
				perlist.add(ticketlist.perlist(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return perlist;
	}
	//전체 (NULL확인)
	public int selectAllInt() {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_ALL);
			rs = pst.executeQuery();
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	// 3-4 예매가능한 공연만 보기
	public List<PerformanceVO> selectPossible() {
		List<PerformanceVO> perlist = new ArrayList<PerformanceVO>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_POSSIBLE);
			rs = pst.executeQuery();
			while(rs.next()) {
				perlist.add(ticketlist.perlist(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return perlist;
	}
	//예매가능 (NULL확인)
	public int selectPossibleInt() {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_POSSIBLE);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	// 3-5. 관심리스트 추가 (조건 확인)
	// 조건확인
	public int wishlistInsertSearch(WishlistVO wish, String id, int per_no) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_INSERT_WISH_SEARCH);
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
	//관심리스트 추가
	public int wishlistInsert(WishlistVO wish) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_INSERT_WISH);
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
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_WISH_FOR_BUY);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				wishperlist.add(ticketlist.wishperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return wishperlist;
	}
	//관심리스트 (NULL확인)
	public int selectWish_Forbuy_Int(String id) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_WISH_FOR_BUY);
			pst.setString(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	// 4-2. insert, update(좌석-1, See->Y)
	//insert
	public int ticketInsert(TicketVO ticket) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_INSERT_TICKET);
			pst.setInt(1, ticket.getWish_no());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//per_no가져오기(update_seat)
	public int ticSeatSelect(int wish_no) {
		int per_no = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_TICKET_SELECT_PER);
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
	//update_seat
	public int ticSeatUpdate(PerformanceVO tic_seat, int per_no) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_TICKET_UPDATE_SEAT);
			pst.setInt(1, per_no);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//update_wish
	public int ticWishUpdate(WishlistVO tic_wish, int wish_no) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_TICKET_UPDATE_WISH);
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
	// 5-1. 비밀번호 수정
	// id, pw다시입력 후 확인
	public int memUpdateSearch(MemberVO mem, String id, String pw) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_UPDATE_MEM_SEARCH);
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
	//비밀번호 수정
	public int pwUpdate(MemberVO mem, String id, String pw) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_UPDATE_MEM);
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
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_TICKET_BUY);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ticperlist.add(ticketlist.ticperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return ticperlist;
	}
	//예매 확인 (NULL확인)
	public int selectTicketBuyInt(String id) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_TICKET_BUY);
			pst.setString(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	
	// 5-3. 예매 취소 -> 가능 조회
	public List<TicketWishPerVO> selectTicketDel(String id) {
		List<TicketWishPerVO> ticperlist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_DELETE_TICKET_SEARCH);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ticperlist.add(ticketlist.ticperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return ticperlist;
	}
	//예매 취소 (NULL확인)
	public int selectTicketDelInt(String id) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_DELETE_TICKET_SEARCH);
			pst.setString(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// delete ticket
	public int ticketDelete(int ticNum) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_DELETE_TICKET);
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
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_WISH_MYPAGE);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				wishperlist.add(ticketlist.wishperlist(rs));
			}
		} catch (SQLException e) {
			System.out.println(id);
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return wishperlist;
	}
	//관심리스트 (NULL확인)
	public int selectWish_mypageInt(String id) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_SELECT_WISH_MYPAGE);
			pst.setString(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}

	// 5-5. 로그아웃 id = null
	// 5-6. 회원탈퇴
	//탈퇴 가능 확인
	public int selectMemDel(String id) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_DELETE_MEM_SEARCH);
			pst.setString(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(id);
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//pw입력 다시입력 후 id, pw확인
	public int selectMemPWDel (String id, String pw) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_UPDATE_MEM_SEARCH);
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

	// delete member
	public int memberDelete(String mem_id) {
		int result = 0;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(TicketSQL.SQL_DELETE_MEM);
			pst.setString(1, mem_id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
}
