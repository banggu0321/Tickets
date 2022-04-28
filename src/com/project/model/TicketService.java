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
	//1. ȸ������
	public int memberInsert(MemberVO mem) {
		return ticketDAO.memberInsert(mem);
	}
	//2. �α���
	public MemberVO memberLogIn(String mem_id, String mem_pw) {
		return ticketDAO.memberLogIn(mem_id, mem_pw);
	}
	//3. ������ȸ
	//3-1. ���� ��ȸ
	public List<PerformanceVO> selectPer_Title(String title) {
		return ticketDAO.selectPer_Title(title);
	}
	public int selectPer_TitleInt(String title) {
		return ticketDAO.selectPer_TitleInt(title);
	}
	//3-2. ī�װ��� ��ȸ
	public List<PerformanceVO> selectPer_Cat(String category) {
		return ticketDAO.selectPer_Cat(category);
	}
	public int selectPer_CatInt(String category) {
		return ticketDAO.selectPer_CatInt(category);
	}
	//3-3. ��ü ��ȸ
	public List<PerformanceVO> selectAll() {
		return ticketDAO.selectAll();
	}
	public int selectAllInt() {
		return ticketDAO.selectAllInt();
	}
	// 3-4 ���Ű����� ������ ����
	public List<PerformanceVO> selectPossible() {
		return ticketDAO.selectPossible();
	}
	public int selectPossibleInt() {
		return ticketDAO.selectPossibleInt();
	}
	//3-5. ���ɸ���Ʈ �߰�
	public int wishlistInsertSearch(WishlistVO wish, String id, int per_no) {
		return ticketDAO.wishlistInsertSearch(wish, id, per_no);
	}
	public int wishlistInsert(WishlistVO wish) {
		return ticketDAO.wishlistInsert(wish);
	}
	//4. �����ϱ�
	//4-1. ���Ű��� ���ɸ���Ʈ ��ȸ WishPerVO
	public List<WishPerVO> selectWish_Forbuy(String id) {
		return ticketDAO.selectWish_Forbuy(id);
	}
	public int selectWish_Forbuy_Int(String id) {
		return ticketDAO.selectWish_Forbuy_Int(id);
	}
	//4-2. insert, update(�¼�-1, See->Y)
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
	//5. ����������
	//5-1. ��й�ȣ ���� (id�ٽ��Է�)
	public int memUpdateSearch(MemberVO mem, String id, String pw) {
		return ticketDAO.memUpdateSearch(mem, id, pw);
	}
	public int pwUpdate(MemberVO mem, String id, String pw) {
		return ticketDAO.pwUpdate(mem, id, pw);
	}
	//5-2. ���� Ȯ��
	public List<TicketWishPerVO> selectTicketBuy(String id) {
		return ticketDAO.selectTicketBuy(id);
	}
	public int selectTicketBuyInt(String id) {
		return ticketDAO.selectTicketBuyInt(id);
	}
	//5-3. ���� ��� -> ���� ��ȸ
	public List<TicketWishPerVO> selectTicketDel(String id) {
		return ticketDAO.selectTicketDel(id);
	}
	public int selectTicketDelInt(String id) {
		return ticketDAO.selectTicketDelInt(id);
	}
	//5-3. ���� delete
	public int ticketDelete(int ticNum) {
		return ticketDAO.ticketDelete(ticNum);
	}
	public int ticDelWishno(int tic_no) {
		return ticketDAO.ticDelWishno(tic_no);
	}
	public int ticDelUpdateWish(int wish_no) {
		return ticketDAO.ticDelUpdateWish(wish_no);
	}
	public int ticDelPerno(int wish_no) {
		return ticketDAO.ticDelPerno(wish_no);
	}
	public int ticDelUpdateSeat(int per_no) {
		return ticketDAO.ticDelUpdateSeat(per_no);
	}
	//5-4. ���ɸ���Ʈ ��ȸ
	public List<WishPerVO> selectWish_mypage(String id) {
		return ticketDAO.selectWish_mypage(id);
	}
	public int selectWish_mypageInt(String id) {
		return ticketDAO.selectWish_mypageInt(id);
	}
	//5-5. �α׾ƿ� id = null
	//5-6. Ż�� ���� Ȯ��
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
