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
	public List<MemberVO> memberLogIn(String mem_id, String mem_pw) {
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
	//3-4. 관심리스트 추가
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
	public int ticSeatUpdate(PerformanceVO tic_seat) {
		return ticketDAO.ticSeatUpdate(tic_seat);
	}
	public int ticWishUpdate(WishlistVO tic_wish) {
		return ticketDAO.ticWishUpdate(tic_wish);
	}
	//5. 마이페이지
	//5-1. 비밀번호 수정 (id다시입력)
	public int pwUpdate(MemberVO mem) {
		return ticketDAO.pwUpdate(mem);
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
	//5-5. 로그아웃==?
	//5-6. 탈퇴 가능 확인
	public List<TicketWishPerVO> selectMemDel(String id) {
		return ticketDAO.selectMemDel(id);
	}
	//5-6. delete member
	public int memberDelete(int mem_id) {
		return ticketDAO.memberDelete(mem_id);
	}
}
