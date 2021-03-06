package com.project.dto;

public class WishlistVO {
	private int wish_no ;
	private String m_id ;
	private int per_no ;
	private String wish_see ;
	
	public WishlistVO() {}

	public WishlistVO(int per_no) {
		super();
		this.per_no = per_no;
	}
	
	public WishlistVO(int wish_no, String m_id, int per_no, String wish_see) {
		super();
		this.wish_no = wish_no;
		this.m_id = m_id;
		this.per_no = per_no;
		this.wish_see = wish_see;
	}
	public int getWish_no() {
		return wish_no;
	}
	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getPer_no() {
		return per_no;
	}
	public void setPer_no(int per_no) {
		this.per_no = per_no;
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
		builder.append("WishlistVO [wish_no=").append(wish_no).append(", m_id=").append(m_id).append(", per_no=")
				.append(per_no).append(", wish_see=").append(wish_see).append("]");
		return builder.toString();
	}
	
}
