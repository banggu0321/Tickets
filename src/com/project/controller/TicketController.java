package com.project.controller;

import java.util.Scanner;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketVO;
import com.project.dto.WishlistVO;
import com.project.model.TicketService;
import com.project.view.TicketView;

public class TicketController {
	static TicketService service = new TicketService();
	static Scanner sc = new Scanner(System.in);
	static String id = "id";
	static int wish_no = 181;

	public static void main(String[] args) {
		boolean mainFlag = true;
		while (mainFlag) {
			int select_num = displayMain();
			switch (select_num) {
			case 1:
				memberInsert();
				break;
			case 2:
				memberLogIn();
				break;
			case 3:
				break;// memberLogIn(); break;
			case 31:
				selectPer_Title();
				break; // 조회성공
			case 32:
				selectPer_Cat();
				break;
			case 33:
				selectAll();
				break;
			case 34:
				wishlistInsert();
				break;
			case 4:
				wishForBuy();
				break; // ticketS1();ticketS2();ticketS3();
			case 41:
				ticketInsert();
				break;
			case 42:
				ticSeatUpdate();
				break;
			case 43:
				ticWishUpdate();
				break;
			case 5:
				break;
			case 51:
				pwUpdate();
				break;
			case 52:
				selectTicBuy();
				break;
			case 53:
				selectTicDel();
				ticketDelete();
				break;
			// case 54: selectWish_mypage(); break;
			// case 55: logout(); break;
			// case 56: memberDelete(); break;
			case 6:
				mainFlag = false;
			}
		}
	}

	private static int displayMain() {
		System.out.println("|TICKET PROGRAM|===================================");
		System.out.print("1.회원가입|");
		System.out.print("2.로그인|");
		System.out.print("3.공연 조회|");
		System.out.print("4.예매하기|");
		System.out.print("5.마이페이지|");
		System.out.println("6.종료");
		System.out.println("===================================================");
		System.out.print("작업을 선택하세요>>");
		return sc.nextInt();
	}

	private static void memberInsert() {
		MemberVO mem = new MemberVO();
		System.out.print("ID : ");
		mem.setM_id(sc.next());
		System.out.print("PASSWORD : ");
		mem.setM_pw(sc.next());
		System.out.print("Name : ");
		mem.setM_name(sc.next());
		System.out.print("E-mail : ");
		mem.setM_email(sc.next());
		System.out.print("Phone : ");
		mem.setM_phone(sc.next());
		int result = service.memberInsert(mem);
		System.out.println(result > 0 ? "[알림]회원가입 되었습니다." : "[알림]회원가입 실패");
	}

	private static void memberLogIn() {
		System.out.print("ID : ");
		id = sc.next();
		System.out.print("PASSWORD : ");
		String pw = sc.next();
		TicketView.printLogIn(service.memberLogIn(id, pw));
		System.out.println();
	}

	private static void selectPer_Title() {
		System.out.print("제목별 검색>> ");
		TicketView.printPer(service.selectPer_Title(sc.next()));
	}

	private static void selectPer_Cat() {
		System.out.print("카테고리별 검색>> ");
		TicketView.printPer(service.selectPer_Cat(sc.next()));
	}

	private static void selectAll() {
		TicketView.printPer(service.selectAll());
	}

	private static void wishForBuy() {
		TicketView.printWishForBuy(service.selectWish_Forbuy(id));
	}

	private static void wishlistInsert() {
		WishlistVO wishlist = new WishlistVO();
		System.out.print("Per_no : ");
		int per_no = sc.nextInt();
		int result = service.wishlistInsertSearch(wishlist, id, per_no);
		if (result == 0) {
			wishlist.setM_id(id);
			wishlist.setPer_no(per_no);
			int insert = service.wishlistInsert(wishlist);
			System.out.println(insert > 0 ? "[알림]success " : "[알림]실패");
		} else {
			System.out.println("Wishlist에 존재하는 공연입니다.");
		}
	}

	private static void ticketInsert() {
		TicketVO ticket = new TicketVO();
		System.out.print("공연 넘버(NO)>>");
		wish_no = sc.nextInt();
		ticket.setWish_no(wish_no);
		int result = service.ticketInsert(ticket);
		System.out.println(result > 0 ? "INSERT SUCCESS" : "INSERT FAIL");
	}

	private static void ticSeatUpdate() {
		PerformanceVO perlist = new PerformanceVO();
		int per_no = service.ticSeatSelect(wish_no);
		System.out.println(per_no);
		int update = service.ticSeatUpdate(perlist, per_no);
		System.out.println(update > 0 ? "[알림]success " : "[알림]실패");
	}

	private static void ticWishUpdate() {
		WishlistVO wishlist = new WishlistVO();
		int result = service.ticWishUpdate(wishlist, wish_no);
		System.out.println(result > 0 ? "UPDATE SUCCESS" : "UPDATE FAIL");
		System.out.println();
	}

	private static void pwUpdate() {
		id = null;
		MemberVO memlist = new MemberVO();
		System.out.print("ID : ");
		id = sc.next();
		System.out.print("PASSWORD : ");
		String pw = sc.next();
		int result = service.memUpdateSearch(memlist, id, pw);
		if (result == 1) {
			System.out.print("NEW PASSWORD : ");
			pw = sc.next();
			int update = service.pwUpdate(memlist, id, pw);
			System.out.println(update > 0 ? "[알림]success " : "[알림]실패");
		} else {
			System.out.println("[알림] 오류");
		}
	}

	private static void selectTicBuy() {
		TicketView.printTicBuy(service.selectTicketBuy(id));
	}

	private static void selectTicDel() {
		TicketView.printTicBuy(service.selectTicketDel(id));
	}

	private static void ticketDelete() {
		System.out.print("티켓번호 : ");
		int result = service.ticketDelete(sc.nextInt());
		System.out.println(result > 0 ? "DELETE SUCCESS" : "DELETE FAIL");

	}
}
