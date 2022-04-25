package com.project.dto;

public class MemberVO {
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_email;
	private String m_phone;
	
	public MemberVO() {}
	
	public MemberVO(String m_id, String m_pw, String m_name, String m_email, String m_phone) {
		super();
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_phone = m_phone;
	}

	public MemberVO(String m_id, String m_pw) {
		super();
		this.m_id = m_id;
		this.m_pw = m_pw;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberVO [m_id=").append(m_id).append(", m_pw=").append(m_pw).append(", m_name=").append(m_name)
				.append(", m_email=").append(m_email).append(", m_phone=").append(m_phone).append("]");
		return builder.toString();
	}
}
