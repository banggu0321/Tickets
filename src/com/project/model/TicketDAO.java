package com.project.model;

import java.util.List;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketVO;
import com.project.dto.TicketWishPerVO;
import com.project.dto.WishPerVO;
import com.project.dto.WishlistVO;

public class TicketDAO {
	//1. ȸ������
	public int memberInsert(MemberVO mem) {
		return 0;
	}
	//2. �α���
	public List<MemberVO> memberLogIn(String mem_id, String mem_pw) {
		return null;
	}
	//3. ������ȸ
	//3-1. ���� ��ȸ
	public List<PerformanceVO> selectPer_Title(String title) {
		return null;
	}
	//3-2. ī�װ��� ��ȸ
	public List<PerformanceVO> selectPer_Cat(String category) {
		return null;
	}
	//3-3. ��ü ��ȸ
	public List<PerformanceVO> selectAll() {
		return null;
	}
	//3-4. ���ɸ���Ʈ �߰�
	public int wishlistInsert(WishlistVO wish) {
		return 0;
	}
	//4. �����ϱ�
	//4-1. ���Ű��� ���ɸ���Ʈ ��ȸ WishPerVO
	public List<WishPerVO> selectWish_Forbuy(String id) {
		return null;
	}
	//4-2. insert, update(�¼�-1, See->Y)
	public int ticketInsert(TicketVO ticket) {
		return 0;
	}
	public int ticSeatUpdate(PerformanceVO tic_seat) {
		return 0;
	}
	public int ticWishUpdate(WishlistVO tic_wish) {
		return 0;
	}
	//5. ����������
	//5-1. ��й�ȣ ���� (id�ٽ��Է�)
	public int pwUpdate(MemberVO mem) {
		return 0;
	}
	//5-2. ���� Ȯ��
	public List<TicketWishPerVO> selectTicketBuy(String id) {
		return null;
	}
	//5-3. ���� ��� -> ���� ��ȸ
	public List<TicketWishPerVO> selectTicketDel(String id) {
		return null;
	}
	//5-3. ���� delete
	public int ticketDelete(int ticNum) {
		return 0;
	}
	//5-4. ���ɸ���Ʈ ��ȸ
	public List<WishPerVO> selectWish_mypage(String id) {
		return null;
	}
	//5-5. �α׾ƿ�==?
	//5-6. Ż�� ���� Ȯ��
	public List<TicketWishPerVO> selectMemDel(String id) {
		return null;
	}
	//5-6. delete member
	public int memberDelete(int mem_id) {
		return 0;
	}
}
