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
			System.out.println("[�˸�] "+ mem.getM_id() +"�� �α��εǾ����ϴ�.");
			return mem.getM_id(); 
		}else {
			System.out.println("[�˸�] �α��� ����");
			id = null;
			return id;
		}
	}

	public static void printNeedLogin() {
		System.out.println("[�˸�]�α����� �ʿ��մϴ�.\n");
	}
	
	public static void printPer(List<PerformanceVO> perlist) {
		System.out.println("---------------------------------------------------");
		for(PerformanceVO per:perlist) {
			System.out.print("������ȣ: " + per.getPer_no() + "\t");
			System.out.println("������: " + per.getPer_title());
			System.out.print("���: " + per.getPer_location() + "\t");
			System.out.println("��¥: " + formatter.format(per.getPer_date()));
			System.out.print("�����ð�: " + per.getPer_time() + "\t");
			System.out.print("����: " + per.getPer_price() + "\t");
			System.out.println("�⿬��: " + per.getPer_cast());
			System.out.print("ī�װ�: " + per.getPer_category() + "\t");
			System.out.println("�����¼�: " + per.getPer_seat());
			System.out.println("---------------------------------------------------");
		}
	}
	public static void printPerNull() {
		System.out.println("[�˸�]�˻������ �����ϴ�.\n");
	}

	public static void printWish(List<WishPerVO> Wishlist) {
		System.out.println("---------------------------------------------------");
		for(WishPerVO wish:Wishlist) {
			System.out.print("���ù�ȣ: " + wish.getWish_no() + "\t");
			System.out.println("������: " + wish.getPer_title());
			System.out.print("���: " + wish.getPer_location() + "\t");
			System.out.println("��¥: " + formatter.format(wish.getPer_date()));
			System.out.print("�����ð�: " + wish.getPer_time() + "\t");
			System.out.print("����: " + wish.getPer_price() + "\t");
			System.out.println("�⿬��: " + wish.getPer_cast());
			System.out.print("ī�װ�: " + wish.getPer_category() + "\t");
			System.out.print("�����¼�: " + wish.getPer_seat() + "\t");
			System.out.println("���ſ���: " + wish.getWish_see());
			System.out.println("---------------------------------------------------");
		}
	}
	public static void printWishNull() {
		System.out.println("[�˸�]wishlist�� �߰��ϼ���\n");
	}
	

	public static void printTicBuy(List<TicketWishPerVO> ticbuylist, String id) {
		System.out.println("["+id+"���� ���ų���]-----------------------------------");
		for(TicketWishPerVO tic:ticbuylist) {
			System.out.print("Ƽ�Ϲ�ȣ: " + tic.getTic_no() + "\t");
			System.out.println("������: " + tic.getTic_date());
			System.out.print("������: " + tic.getPer_title() + "\t");
			System.out.println("���: " + tic.getPer_location());
			System.out.print("��¥: " + formatter.format(tic.getPer_date()) + "\t");
			System.out.println("�����ð�: " + tic.getPer_time());
			System.out.print("����: " + tic.getPer_price() + "\t");
			System.out.print("�⿬��: " + tic.getPer_cast() + "\t");
			System.out.println("ī�װ�: " + tic.getPer_category() + "\t");
			System.out.println("---------------------------------------------------");
		}
	}

	public static void printTicBuyNull(String id) {
		System.out.println("[�˸�]"+id+"���� ���ų����� �����ϴ�.\n");
		System.out.println();
	}

	public static void printTicDelNull(String id) {
		System.out.println("[�˸�]��Ұ����� ���ų����� �����ϴ�.\n");
		System.out.println();
	}

	public static void printWishMyNull(String id) {
		System.out.println("[�˸�]"+id+"wishlist�� ������ϴ�.\n");
		System.out.println();
	}

}
