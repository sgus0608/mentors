package org.kosta.mentors.model;

public class PostVO {
	private long postNo;
	private String title;
	private String content;
	private String timePosted;
	private long hits;
	private MemberVO memberVO;
	public PostVO() {
		super();
	}
	//게시글 등록시 사용할 생성자
	public PostVO(String title, String content, MemberVO memberVO) {
		super();
		this.title = title;
		this.content = content;
		this.memberVO = memberVO;
	}
	//자유게시판 조회시 사용할 생성자 
	public PostVO(long no, String title, long hits, String timePosted, MemberVO memberVO) {
		super();
		this.postNo = no;
		this.title = title;
		this.hits = hits;
		this.timePosted = timePosted;
		this.memberVO = memberVO;
	}
	//자유게시판 상세글보기
	public PostVO(long no, String title, String content, long hits, String timePosted, MemberVO memberVO) {
		super();
		this.postNo = no;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.timePosted = timePosted;
		this.memberVO = memberVO;
	}
	public long getPostNo() {
		return postNo;
	}
	public void setPostNo(long postNo) {
		this.postNo = postNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public long getHits() {
		return hits;
	}
	public void setHits(long hits) {
		this.hits = hits;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	@Override
	public String toString() {
		return "PostVO [postNo=" + postNo + ", title=" + title + ", content=" + content + ", timePosted=" + timePosted
				+ ", hits=" + hits + ", memberVO=" + memberVO + "]";
	}
}















