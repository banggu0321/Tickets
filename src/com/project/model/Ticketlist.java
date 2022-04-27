package com.project.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.dto.MemberVO;
import com.project.dto.PerformanceVO;
import com.project.dto.TicketWishPerVO;
import com.project.dto.WishPerVO;

public class Ticketlist {
	MemberVO memInfo(ResultSet rs) throws SQLException {
		MemberVO mem = new MemberVO();
		mem.setM_id(rs.getString("M_ID"));
		mem.setM_pw(rs.getString("M_PW"));
		mem.setM_name(rs.getString("M_NAME"));
		mem.setM_email(rs.getString("M_EMAIL"));
		mem.setM_phone(rs.getString("M_PHONE"));
		return mem;
	}
	PerformanceVO perlist(ResultSet rs) throws SQLException {
		PerformanceVO per = new PerformanceVO();
		per.setPer_no(rs.getInt("PER_NO"));
		per.setPer_title(rs.getString("PER_TITLE"));
		per.setPer_location(rs.getString("PER_LOCATION"));
		per.setPer_date(rs.getDate("PER_DATE"));
		per.setPer_time(rs.getString("PER_TIME"));
		per.setPer_price(rs.getString("PER_PRICE"));
		per.setPer_cast(rs.getString("PER_CAST"));
		per.setPer_category(rs.getString("PER_CATEGORY"));
		per.setPer_seat(rs.getInt("PER_SEAT"));
		return per;
	}
	WishPerVO wishperlist(ResultSet rs) throws SQLException {
		WishPerVO wish = new WishPerVO();
		wish.setM_id(rs.getString("M_ID"));
		wish.setWish_no(rs.getInt("WISH_NO"));
		wish.setPer_title(rs.getString("PER_TITLE"));
		wish.setPer_location(rs.getString("PER_LOCATION"));
		wish.setPer_date(rs.getDate("PER_DATE"));
		wish.setPer_time(rs.getString("PER_TIME"));
		wish.setPer_price(rs.getString("PER_PRICE"));
		wish.setPer_cast(rs.getString("PER_CAST"));
		wish.setPer_category(rs.getString("PER_CATEGORY"));
		wish.setPer_seat(rs.getInt("PER_SEAT"));
		wish.setWish_see(rs.getString("WISH_SEE"));
		return wish;
	}

	TicketWishPerVO ticperlist(ResultSet rs) throws SQLException {
		TicketWishPerVO per = new TicketWishPerVO();
		per.setM_id(rs.getString(1));
		per.setTic_no(rs.getInt(2));
		per.setTic_date(rs.getDate(3));
		per.setPer_title(rs.getString(4));
		per.setPer_location(rs.getString(5));
		per.setPer_date(rs.getDate(6));
		per.setPer_time(rs.getString(7));
		per.setPer_price(rs.getString(8));
		per.setPer_cast(rs.getString(9));
		per.setPer_category(rs.getString(10));
		return per;
	}
}
