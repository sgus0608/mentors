package org.kosta.mentors.model;

public class QnAPostVO extends PostVO {
	private String category;

	public QnAPostVO() {
		super();
	}
	
	public QnAPostVO(long postNo, String title, long hits, String timePosted,String category, MemberVO memberVO) {
		super(postNo, title, hits, timePosted, memberVO);
		this.category=category;
	}
	
	public QnAPostVO(long postNo, String title, String content, long hits, String timePosted,String category, MemberVO memberVO) {
		super(postNo, title, content, hits, timePosted, memberVO);
		this.category=category;
	}
	
	public QnAPostVO(String title, String content,String category, MemberVO memberVO) {
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
		return "QnAPostVO[postNO= "+super.getPostNo()+", title= "+super.getTitle()+", content= "+super.getContent()+", hits= "+super.getHits()
		+", timePosted= "+super.getTimePosted()+", category= "+category+", memberVO= "+super.getMemberVO()+"]";
	}
	
}
