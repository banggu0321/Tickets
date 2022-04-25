package com.project.view;

import java.util.List;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;

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
		System.out.println("-------------------------------");
		for(PerformanceVO per:perlist) {
			System.out.println(per);
		}
	}
}
