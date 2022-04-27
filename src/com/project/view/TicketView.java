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
		return null;
	}
	
	public static void printPer(List<PerformanceVO> perlist) {
		System.out.println("-------------------------------");
		for(PerformanceVO per:perlist) {
			System.out.print("������ȣ: " + per.getPer_no() + "\t");
			System.out.println("������: " + per.getPer_title());
			System.out.print("���: " + per.getPer_location() + "\t");
			System.out.println("��¥: " + per.getPer_date());
			System.out.print("�����ð�: " + per.getPer_time() + "\t");
			System.out.print("����: " + per.getPer_price() + "\t");
			System.out.println("�⿬��: " + per.getPer_cast());
			System.out.print("ī�װ�: " + per.getPer_category() + "\t");
			System.out.println("�����¼�: " + per.getPer_seat());
			System.out.println("-------------------------------");
		}
	}
	public static void printPerNull(String id) {
		System.out.println("[�˸�]�˻������ �����ϴ�.\n");
	}

	public static void printWish(List<WishPerVO> Wishlist) {
		System.out.println("-------------------------------");
		for(WishPerVO wish:Wishlist) {
			System.out.print("���ù�ȣ:" + wish.getWish_no() + "\t");
			System.out.println("������: " + wish.getPer_title());
			System.out.print("���: " + wish.getPer_location() + "\t");
			System.out.println("��¥: " + wish.getPer_date());
			System.out.print("�����ð�: " + wish.getPer_time() + "\t");
			System.out.print("����: " + wish.getPer_price() + "\t");
			System.out.println("�⿬��: " + wish.getPer_cast());
			System.out.print("ī�װ�: " + wish.getPer_category() + "\t");
			System.out.print("�����¼�: " + wish.getPer_seat() + "\t");
			System.out.println("��������: " + wish.getWish_see());
			System.out.println("-------------------------------");
		}
	}
	public static void printWishNull(String id) {
		System.out.println("[�˸�]wishlist�� �߰��ϼ���\n");
	}
	

	public static void printTicBuy(List<TicketWishPerVO> ticbuylist) {
		System.out.println("-------------------------------");
		for(TicketWishPerVO tic:ticbuylist) {
			System.out.print("���ù�ȣ: " + tic.getTic_no() + "\t");
			System.out.println("������: " + tic.getTic_date());
			System.out.print("������: " + tic.getPer_title() + "\t");
			System.out.println("���: " + tic.getPer_location());
			System.out.print("��¥: " + tic.getPer_date() + "\t");
			System.out.print("�����ð�: " + tic.getPer_time());
			System.out.println("����: " + tic.getPer_price() + "\t");
			System.out.print("�⿬��: " + tic.getPer_cast() + "\t");
			System.out.println("ī�װ�: " + tic.getPer_category() + "\t");
			System.out.println("-------------------------------");
		}
	}

	public static void printTicBuyNull(String id) {
		System.out.println("[�˸�]���ų����� �����ϴ�.\n");
	}

	public static void printTicDelNull(String id) {
		System.out.println("[�˸�]��Ұ����� ���ų����� �����ϴ�.\n");
	}

	public static void printWishMyNull(String id) {
		System.out.println("[�˸�]wishlist�� ������ϴ�.\n");
	}

//	public static void printPer(int wishlistInsertSearch) {
//		System.out.println(wishlistInsertSearch);
//	}
	
}
