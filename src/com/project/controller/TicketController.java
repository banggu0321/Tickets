package com.project.controller;

import java.util.Scanner;

import com.project.dto.MemberVO;
import com.project.model.TicketService;
import com.project.view.TicketView;

public class TicketController {
	static TicketService service = new TicketService();
	static Scanner sc = new Scanner(System.in);
	static String id = null;
	
	public static void main(String[] args) {
		boolean mainFlag = true;
		while(mainFlag) {
			int select_num = displayMain();
			switch(select_num) {
			case 1: memberInsert(); break;
			case 2: memberLogIn(); break;
			case 3: break;//memberLogIn(); break;
			case 4: break;//memberLogIn(); break;
			case 5: break;//memberLogIn(); break;
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
		System.out.println(result>0 ? 
				"[알림]회원가입 되었습니다.":"[알림]회원가입 실패");
		System.out.println();
	}

	private static void memberLogIn() {
		System.out.print("ID : ");
		id = sc.next();
		System.out.print("PASSWORD : ");
		String pw = sc.next();
		TicketView.printID(service.memberLogIn(id,pw));
		System.out.println();
	}
}
