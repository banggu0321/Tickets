package com.project.view;

import java.util.List;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketWishPerVO;
import com.project.dto.WishPerVO;

public class TicketView {

	public static String printLogIn(MemberVO mem) {
		if (mem != null) {
			System.out.println("[�˸�] "+ mem.getM_id() +"�� �α��εǾ����ϴ�.");
			return mem.getM_id(); }
		else
			System.out.println("[�˸�] �α��� ����");
		System.out.println();
		return null;
	}
	
	public static void printPer(List<PerformanceVO> perlist) {
		if(perlist != null) {
			System.out.println("-------------------------------");
			for(PerformanceVO per:perlist) {
				System.out.println(per);
			}
		}else { System.out.println("�˻������ �����ϴ�.");}
	}

	public static void printWish(List<WishPerVO> Wishlist) {
		System.out.println("-------------------------------");
		for(WishPerVO wish:Wishlist) {
			System.out.println(wish);
		}
	}
	public static void printWishNull(String id) {
		System.out.println("wishlist�� �߰��ϼ���");
	}
	

	public static void printTicBuy(List<TicketWishPerVO> ticbuylist) {
		System.out.println("-------------------------------");
		for(TicketWishPerVO tic:ticbuylist) {
			System.out.println(tic);
		}
	}

//	public static void printPer(int wishlistInsertSearch) {
//		System.out.println(wishlistInsertSearch);
//	}
	
}
