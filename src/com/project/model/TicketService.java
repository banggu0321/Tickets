package com.project.model;

import java.util.List;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketVO;
import com.project.dto.TicketWishPerVO;
import com.project.dto.WishPerVO;
import com.project.dto.WishlistVO;

public class TicketService {
	TicketDAO ticketDAO = new TicketDAO();
	//1. 회원가입
	public int memberInsert(MemberVO mem) {
		return ticketDAO.memberInsert(mem);
	}
	//2. 로그인
	public MemberVO memberLogIn(String mem_id, String mem_pw) {
		return ticketDAO.memberLogIn(mem_id, mem_pw);
	}
	//3. 공연조회
	//3-1. 제목별 조회
	public List<PerformanceVO> selectPer_Title(String title) {
		return ticketDAO.selectPer_Title(title);
	}
	//3-2. 카테고리별 조회
	public List<PerformanceVO> selectPer_Cat(String category) {
		return ticketDAO.selectPer_Cat(category);
	}
	//3-3. 전체 조회
	public List<PerformanceVO> selectAll() {
		return ticketDAO.selectAll();
	}
	// 3-4 예매가능한 공연만 보기
	public List<PerformanceVO> selectPossible() {
		return ticketDAO.selectPossible();
	}
	//3-5. 관심리스트 추가
	public int wishlistInsertSearch(WishlistVO wish, String id, int per_no) {
		return ticketDAO.wishlistInsertSearch(wish, id, per_no);
	}
	public int wishlistInsert(WishlistVO wish) {
		return ticketDAO.wishlistInsert(wish);
	}
	//4. 예매하기
	//4-1. 예매가능 관심리스트 조회 WishPerVO
	public List<WishPerVO> selectWish_Forbuy(String id) {
		return ticketDAO.selectWish_Forbuy(id);
	}
	//4-2. insert, update(좌석-1, See->Y)
	public int ticketInsert(TicketVO ticket) {
		return ticketDAO.ticketInsert(ticket);
	}
	public int ticSeatSelect(int wish_no) {
		return ticketDAO.ticSeatSelect( wish_no);
	}
	public int ticSeatUpdate(PerformanceVO tic_seat, int per_no) {
		return ticketDAO.ticSeatUpdate(tic_seat, per_no);
	}
	public int ticWishUpdate(WishlistVO tic_wish, int wish_no) {
		return ticketDAO.ticWishUpdate(tic_wish, wish_no);
	}
	//5. 마이페이지
	//5-1. 비밀번호 수정 (id다시입력)
	public int memUpdateSearch(MemberVO mem, String id, String pw) {
		return ticketDAO.memUpdateSearch(mem, id, pw);
	}
	public int pwUpdate(MemberVO mem, String id, String pw) {
		return ticketDAO.pwUpdate(mem, id, pw);
	}
	//5-2. 예매 확인
	public List<TicketWishPerVO> selectTicketBuy(String id) {
		return ticketDAO.selectTicketBuy(id);
	}
	//5-3. 예매 취소 -> 가능 조회
	public List<TicketWishPerVO> selectTicketDel(String id) {
		return ticketDAO.selectTicketDel(id);
	}
	//5-3. 예매 delete
	public int ticketDelete(int ticNum) {
		return ticketDAO.ticketDelete(ticNum);
	}
	//5-4. 관심리스트 조회
	public List<WishPerVO> selectWish_mypage(String id) {
		return ticketDAO.selectWish_mypage(id);
	}
	//5-5. 로그아웃 id = null
	//5-6. 탈퇴 가능 확인
	public int selectMemDel(String id) {
		return ticketDAO.selectMemDel(id);
	}
	public int selectMemPWDel (String id, String pw) {
		return ticketDAO.selectMemPWDel(id, pw);
	}
	//5-6. delete member
	public int memberDelete(String id) {
		return ticketDAO.memberDelete(id);
	}
}
