package com.project.view;

import java.util.List;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketWishPerVO;
import com.project.dto.WishPerVO;

public class TicketView {

	public static String printLogIn(MemberVO mem) {
		if (mem != null) {
			System.out.println("[알림] "+ mem.getM_id() +"님 로그인되었습니다.");
			return mem.getM_id(); }
		else
			System.out.println("[알림] 로그인 실패");
		System.out.println();
		return null;
	}
	
	public static void printPer(List<PerformanceVO> perlist) {
		if(perlist != null) {
			System.out.println("-------------------------------");
			for(PerformanceVO per:perlist) {
				System.out.println(per);
			}
		}else { System.out.println("검색결과가 없습니다.");}
	}

	public static void printWish(List<WishPerVO> Wishlist) {
		System.out.println("-------------------------------");
		for(WishPerVO wish:Wishlist) {
			System.out.println(wish);
		}
	}
	public static void printWishNull(String id) {
		System.out.println("wishlist를 추가하세요");
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
