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
	static String id = null;
	static int wish_no = 0;

	public static void main(String[] args) {
		boolean mainFlag = true;
		while (mainFlag) {
			int select_num = displayMain();
			switch (select_num) {
			case 1: memberInsert(); break;
			case 2: memberLogIn(); break;
			case 3: displaySelete(); break;
			case 4: displayTicket(); break;
			case 5: displayMypage(); break;
			case 6: mainFlag = false;
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
		System.out.println(result > 0 ? "[알림]회원가입 되었습니다.\n" : "");
	}

	private static void memberLogIn() {
		System.out.print("ID : ");
		id = sc.next();
		System.out.print("PASSWORD : ");
		String pw = sc.next();
		TicketView.printLogIn(service.memberLogIn(id, pw));
		System.out.println();
	}
	
	private static void displaySelete() {
		System.out.println("1.제목별|2.카테고리별|3.전체 조회|4.예매가능조회|5.뒤로가기");
		System.out.print("선택>>");
		int select = sc.nextInt();
		switch (select) {
			case 1: selectPer_Title(); break;
			case 2: selectPer_Cat(); break;
			case 3: selectAll(); break;
			case 4: selectPossible(); break;
			case 5: break;
		}
	}
	private static void selectPer_Title() {
		System.out.print("제목 : ");
		String title = sc.next();
		int result = service.selectPer_TitleInt(title);
		if(result >= 1) {
			TicketView.printPer(service.selectPer_Title(title));
			wishlistInsert(); 
		}else TicketView.printPerNull(id);
	}

	private static void selectPer_Cat() {
		System.out.println("1)뮤지컬 2)콘서트 3)연극 4)클래식");
		System.out.print("선택>>");
		int select = sc.nextInt();
		String cat = "";
		switch (select) {
			case 1: cat = "뮤지컬"; break;
			case 2: cat = "콘서트"; break;
			case 3: cat = "연극"; break;
			case 4: cat = "클래식"; break;
			}
		int result = service.selectPer_CatInt(cat);
		if(result >= 1) {
			TicketView.printPer(service.selectPer_Cat(cat));
			wishlistInsert(); 
		}else TicketView.printPerNull(id);
	}
	private static void selectAll() {
		int result = service.selectAllInt();
		if(result >= 1) {
			TicketView.printPer(service.selectAll());
			wishlistInsert(); 
		}else TicketView.printPerNull(id);
	}
	private static void selectPossible() {
		int result = service.selectPossibleInt();
		if(result >= 1) {
			TicketView.printPer(service.selectPossible());
			wishlistInsert(); 
		}else TicketView.printPerNull(id);
	}
	private static void wishlistInsert() {
		if(id != null) {
			System.out.println("[1]WISHLIST 추가 [2]뒤로가기");
			int add = sc.nextInt();
			if(add == 1) {
				WishlistVO wishlist = new WishlistVO();
				System.out.print("Per_no : ");
				int per_no = sc.nextInt();
				int result = service.wishlistInsertSearch(wishlist, id, per_no);
				if (result == 0) {
					wishlist.setM_id(id);
					wishlist.setPer_no(per_no);
					int insert = service.wishlistInsert(wishlist);
					System.out.println(insert > 0 ? "[알림] Wishlist에 추가되었습니다.\n" : "");
				} else System.out.println("[알림]Wishlist에 존재하는 공연입니다.\n");
			}else System.out.println();
		}else System.out.println();
	}
	private static void displayTicket() {
		if(id != null) {
		wishForBuy(); 
		}
		else System.out.println("로그인이 필요합니다.");
	}
	private static void wishForBuy() {
		int result = service.selectWish_Forbuy_Int(id);
		if(result >= 1) {
			TicketView.printWish(service.selectWish_Forbuy(id));
			ticketInsert(); 
			ticSeatUpdate(); 
			ticWishUpdate(); 
			wish_no = 0;
		}else TicketView.printWishNull(id);
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
	private static void displayMypage() {
		if(id != null) {
			System.out.println("1.비밀번호변경|2.예매확인|3.예매취소|4.관심목록조회|5.로그아웃|6.회원탈퇴|7.뒤로가기");
			System.out.print("선택>>");
			int select = sc.nextInt();
			switch (select) {
			case 1: pwUpdate();break;
			case 2: selectTicBuy();break;
			case 3: selectTicDel(); ticketDelete();break;
			case 4: selectWish_mypage(); break;
			case 5: logout(); break;
			case 6: memberDelete(); break;
			case 7: break;
			}
		}else {System.out.println("로그인이 필요합니다.");}
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
	private static void selectWish_mypage() {
		TicketView.printWish(service.selectWish_mypage(id));
	}
	private static void logout() {
		id = null;
		System.out.println(id);
	}
	private static void memberDelete() {
		int ticketlist = service.selectMemDel(id);
		if (ticketlist == 0) {
			System.out.print("00님 탈퇴하시겠습니까? ");
			System.out.println("1.네|2.아니오");
			int deleteY = sc.nextInt(); //1.탈퇴 2. 뒤로가기
			if(deleteY == 1) {
				System.out.print("PASSWORD : ");
				String pw = sc.next();
				int confirmPW = service.selectMemPWDel(id, pw);
				if(confirmPW == 1) {
				int result = service.memberDelete(id);
					System.out.println(result > 0 ? "DELETE SUCCESS" : "DELETE FAIL");
				}
			}
			else {
				System.out.println();
			}
		} else {
			System.out.println("예정된 공연이 존재합니다.");
		}
	}
}
