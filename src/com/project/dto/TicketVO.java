package com.project.dto;

import java.sql.Date;

public class TicketVO {
	private int tic_no ;
	private int wish_no ;
	private Date tic_date ;
	
	public int getTic_no() {
		return tic_no;
	}
	public void setTic_no(int tic_no) {
		this.tic_no = tic_no;
	}
	public int getWish_no() {
		return wish_no;
	}
	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}
	public Date getTic_date() {
		return tic_date;
	}
	public void setTic_date(Date tic_date) {
		this.tic_date = tic_date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketVO [tic_no=").append(tic_no).append(", wish_no=").append(wish_no).append(", tic_date=")
				.append(tic_date).append("]");
		return builder.toString();
	}
}
