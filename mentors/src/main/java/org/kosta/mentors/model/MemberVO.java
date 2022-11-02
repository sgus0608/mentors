package org.kosta.mentors.model;

import java.io.Serializable;

public class MemberVO implements Serializable{
	
	private static final long serialVersionUID = 6079982092097564268L;
	private String id;
	private String password;
	private String nickName;
	private String email;
	private String address;
	private String interest;
	private String signupDate;
	private String MemberType;
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String id, String password, String nickName, String email, String address, String interest,
			String signupDate, String memberType) {
		super();
		this.id = id;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
		this.address = address;
		this.interest = interest;
		this.signupDate = signupDate;
		MemberType = memberType;
	}
	
	public MemberVO(String id, String password, String nickName) {
		super();
		this.id = id;
		this.password = password;
		this.nickName = nickName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getSignupDate() {
		return signupDate;
	}
	public void setSignupDate(String signupDate) {
		this.signupDate = signupDate;
	}
	public String getMemberType() {
		return MemberType;
	}
	public void setMemberType(String memberType) {
		MemberType = memberType;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", nickName=" + nickName + ", email=" + email
				+ ", address=" + address + ", interest=" + interest + ", signupDate=" + signupDate + ", MemberType="
				+ MemberType + "]";
	}
	
	
	
}
