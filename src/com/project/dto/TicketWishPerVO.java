package com.project.dto;

import java.sql.Date;

public class TicketWishPerVO {
	private String m_id;
	private int tic_no;
	private Date tic_date;
	private String per_title;
	private String per_location ;
	private Date per_date ;
	private String per_time ;
	private String per_price ;
	private String per_cast ; 
	private String per_category ;
	
	public TicketWishPerVO() {}
	
	public TicketWishPerVO(String m_id, int tic_no, Date tic_date, String per_title, String per_location, Date per_date,
			String per_time, String per_price, String per_cast, String per_category) {
		super();
		this.m_id = m_id;
		this.tic_no = tic_no;
		this.tic_date = tic_date;
		this.per_title = per_title;
		this.per_location = per_location;
		this.per_date = per_date;
		this.per_time = per_time;
		this.per_price = per_price;
		this.per_cast = per_cast;
		this.per_category = per_category;
	}
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getTic_no() {
		return tic_no;
	}
	public void setTic_no(int tic_no) {
		this.tic_no = tic_no;
	}
	public Date getTic_date() {
		return tic_date;
	}
	public void setTic_date(Date tic_date) {
		this.tic_date = tic_date;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketWishPerVO [m_id=").append(m_id).append(", tic_no=").append(tic_no).append(", tic_date=")
				.append(tic_date).append(", per_title=").append(per_title).append(", per_location=")
				.append(per_location).append(", per_date=").append(per_date).append(", per_time=").append(per_time)
				.append(", per_price=").append(per_price).append(", per_cast=").append(per_cast)
				.append(", per_category=").append(per_category).append("]");
		return builder.toString();
	}
}
