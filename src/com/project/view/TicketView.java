package com.project.view;

import java.text.SimpleDateFormat;
import java.util.List;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketWishPerVO;
import com.project.dto.WishPerVO;

public class TicketView {
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	

	public static String printLogIn(MemberVO mem, String id) {
		if (mem != null) {
			System.out.println("[알림] "+ mem.getM_id() +"님 로그인되었습니다.");
			return mem.getM_id(); 
		}else {
			System.out.println("[알림] 로그인 실패");
			id = null;
			return id;
		}
	}

	public static void printNeedLogin() {
		System.out.println("[알림]로그인이 필요합니다.\n");
	}
	
	public static void printPer(List<PerformanceVO> perlist) {
		System.out.println("---------------------------------------------------");
		for(PerformanceVO per:perlist) {
			System.out.print("공연번호: " + per.getPer_no() + "\t");
			System.out.println("공연명: " + per.getPer_title());
			System.out.print("장소: " + per.getPer_location() + "\t");
			System.out.println("날짜: " + formatter.format(per.getPer_date()));
			System.out.print("공연시간: " + per.getPer_time() + "\t");
			System.out.print("가격: " + per.getPer_price() + "\t");
			System.out.println("출연진: " + per.getPer_cast());
			System.out.print("카테고리: " + per.getPer_category() + "\t");
			System.out.println("남은좌석: " + per.getPer_seat());
			System.out.println("---------------------------------------------------");
		}
	}
	public static void printPerNull() {
		System.out.println("[알림]검색결과가 없습니다.\n");
	}

	public static void printWish(List<WishPerVO> Wishlist) {
		System.out.println("---------------------------------------------------");
		for(WishPerVO wish:Wishlist) {
			System.out.print("선택번호: " + wish.getWish_no() + "\t");
			System.out.println("공연명: " + wish.getPer_title());
			System.out.print("장소: " + wish.getPer_location() + "\t");
			System.out.println("날짜: " + formatter.format(wish.getPer_date()));
			System.out.print("공연시간: " + wish.getPer_time() + "\t");
			System.out.print("가격: " + wish.getPer_price() + "\t");
			System.out.println("출연진: " + wish.getPer_cast());
			System.out.print("카테고리: " + wish.getPer_category() + "\t");
			System.out.print("남은좌석: " + wish.getPer_seat() + "\t");
			System.out.println("예매여부: " + wish.getWish_see());
			System.out.println("---------------------------------------------------");
		}
	}
	public static void printWishNull() {
		System.out.println("[알림]wishlist를 추가하세요\n");
	}
	

	public static void printTicBuy(List<TicketWishPerVO> ticbuylist, String id) {
		System.out.println("["+id+"님의 예매내역]-----------------------------------");
		for(TicketWishPerVO tic:ticbuylist) {
			System.out.print("티켓번호: " + tic.getTic_no() + "\t");
			System.out.println("예매일: " + tic.getTic_date());
			System.out.print("공연명: " + tic.getPer_title() + "\t");
			System.out.println("장소: " + tic.getPer_location());
			System.out.print("날짜: " + formatter.format(tic.getPer_date()) + "\t");
			System.out.println("공연시간: " + tic.getPer_time());
			System.out.print("가격: " + tic.getPer_price() + "\t");
			System.out.print("출연진: " + tic.getPer_cast() + "\t");
			System.out.println("카테고리: " + tic.getPer_category() + "\t");
			System.out.println("---------------------------------------------------");
		}
	}

	public static void printTicBuyNull(String id) {
		System.out.println("[알림]"+id+"님의 예매내역이 없습니다.\n");
		System.out.println();
	}

	public static void printTicDelNull(String id) {
		System.out.println("[알림]취소가능한 예매내역이 없습니다.\n");
		System.out.println();
	}

	public static void printWishMyNull(String id) {
		System.out.println("[알림]"+id+"wishlist가 비었습니다.\n");
		System.out.println();
	}

}
