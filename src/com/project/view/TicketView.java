package com.project.view;

import com.project.dto.MemberVO;

public class TicketView {

	public static String printID(MemberVO mem) {
		if (mem != null) {
			System.out.println("[�˸�] "+ mem.getM_id() +"�� �α��εǾ����ϴ�.");
			return mem.getM_id(); }
		else
			System.out.println("[�˸�] �α��� ����");
		System.out.println();
		return null;
	}

	public static void print(String message) {
		System.out.println("****�˸�*************");
		System.out.println(message);
	}
}
