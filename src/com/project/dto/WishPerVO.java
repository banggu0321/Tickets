package com.project.dto;

import java.sql.Date;

public class WishPerVO {
	private String m_id ;
	private int wish_no ;
	private String per_title ;
	private String per_location ;
	private Date per_date ;
	private String per_time ;
	private String per_price ;
	private String per_cast ;
	private String per_category ;
	private int per_seat ;
	private String wish_see ;
	
	public WishPerVO() {}
	
	public WishPerVO(String m_id, int wish_no, String per_title, String per_location, Date per_date, String per_time,
			String per_price, String per_cast, String per_category, int per_seat, String wish_see) {
		super();
		this.m_id = m_id;
		this.wish_no = wish_no;
		this.per_title = per_title;
		this.per_location = per_location;
		this.per_date = per_date;
		this.per_time = per_time;
		this.per_price = per_price;
		this.per_cast = per_cast;
		this.per_category = per_category;
		this.per_seat = per_seat;
		this.wish_see = wish_see;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getWish_no() {
		return wish_no;
	}
	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}
	public String getPer_title() {
		return per_title;
	}
	public void setPer_title(String per_title) {
		this.per_title = per_title;
	}
	public String getPer_location() {
		return per_location;
	}
	public void setPer_location(String per_location) {
		this.per_location = per_location;
	}
	public Date getPer_date() {
		return per_date;
	}
	public void setPer_date(Date per_date) {
		this.per_date = per_date;
	}
	public String getPer_time() {
		return per_time;
	}
	public void setPer_time(String per_time) {
		this.per_time = per_time;
	}
	public String getPer_price() {
		return per_price;
	}
	public void setPer_price(String per_price) {
		this.per_price = per_price;
	}
	public String getPer_cast() {
		return per_cast;
	}
	public void setPer_cast(String per_cast) {
		this.per_cast = per_cast;
	}
	public String getPer_category() {
		return per_category;
	}
	public void setPer_category(String per_category) {
		this.per_category = per_category;
	}
	public int getPer_seat() {
		return per_seat;
	}
	public void setPer_seat(int per_seat) {
		this.per_seat = per_seat;
	}
	public String getWish_see() {
		return wish_see;
	}
	public void setWish_see(String wish_see) {
		this.wish_see = wish_see;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WishPerVO [m_id=").append(m_id).append(", wish_no=").append(wish_no).append(", per_title=")
				.append(per_title).append(", per_location=").append(per_location).append(", per_date=").append(per_date)
				.append(", per_time=").append(per_time).append(", per_price=").append(per_price).append(", per_cast=")
				.append(per_cast).append(", per_category=").append(per_category).append(", per_seat=").append(per_seat)
				.append(", wish_see=").append(wish_see).append("]");
		return builder.toString();
	}
}
