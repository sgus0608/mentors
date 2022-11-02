package org.kosta.mentors.model;

public class TipsPostVO extends PostVO{
	private String category;

	public TipsPostVO() {
		super();
	}

	public TipsPostVO(long no, String title, long hits, String timePosted, String category, MemberVO memberVO) {
		super(no, title, hits, timePosted, memberVO);
		this.category=category;
	}

	public TipsPostVO(long no, String title, String content, long hits, String timePosted, String category, MemberVO memberVO) {
		super(no, title, content, hits, timePosted, memberVO);
		this.category=category;
	}

	public TipsPostVO(String title, String content, String category, MemberVO memberVO) {
		super(title, content, memberVO);
		this.category=category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "TipsPostVO[postNo=" + super.getPostNo() + ", title="  +super.getTitle() + ", content=" + super.getContent() + ", hits=" + super.getHits() + ", timePosted="
				+ super.getTimePosted() +" ,category="+category+ ", memberVO=" +super.getMemberVO() + "]";
	}
	  
	
}
