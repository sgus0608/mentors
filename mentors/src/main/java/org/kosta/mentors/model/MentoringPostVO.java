package org.kosta.mentors.model;

public class MentoringPostVO extends PostVO {
	private String category;
	private String role;

	public MentoringPostVO() {
		super();
	}
	
	//멘토링 게시글 등록시 사용할 생성자
	public MentoringPostVO(String title, String content, String category, String role, MemberVO memberVO) {
		super(title, content, memberVO);
		this.category = category;
		this.role = role;
	}
	
	//멘토링 게시판 리스트 조회시 사용할 생성자
	public MentoringPostVO(long no, String title, long hits, String timePosted, String category, String role, MemberVO memberVO) {
		super(no, title, hits, timePosted, memberVO);
		this.category = category;
		this.role = role;
	}
	
	//멘토링 게시판 상세글보기
	public MentoringPostVO(long no, String title, String content, long hits, String timePosted, String category, String role, MemberVO memberVO) {
		super(no, title, content, hits, timePosted, memberVO);
		this.category = category;
		this.role = role;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MentoringPostVO [postNo=" + super.getPostNo() + ", title=" + super.getTitle() + ", content=" + super.getContent() + ", timePosted=" + super.getTimePosted()
				+ ", hits=" + super.getHits() + ", category=" + category + ", role=" + role + ", memberVO=" + super.getMemberVO() + "]";
	}
	
	

}
