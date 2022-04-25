package com.project.view;

import com.project.dto.MemberVO;

public class TicketView {

	public static String printID(MemberVO mem) {
		if (mem != null) {
			System.out.println("[알림] "+ mem.getM_id() +"님 로그인되었습니다.");
			return mem.getM_id(); }
		else
			System.out.println("[알림] 로그인 실패");
		System.out.println();
		return null;
	}

	public static void print(String message) {
		System.out.println("****알림*************");
		System.out.println(message);
	}
}
