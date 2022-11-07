package org.kosta.mentors.model;

public class CommentVO {
	private long commentNo;
	private String commentContent;
	private String commentTimePosted;
	private long PostNo;
	private MemberVO memberVO;
	
	public CommentVO() {
		super();
	}
	// 댓글 등록시 사용할 생성자 
	public CommentVO(String commentContent, long postNo, MemberVO memberVO) {
		super();
		this.commentContent = commentContent;
		this.PostNo = postNo;
		this.memberVO = memberVO;
	}
	
	// 댓글 리스트 조회시 사용할 생성자
	public CommentVO(long commentNo, String commentContent, String commentTimePosted, long postNo, MemberVO memberVO) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentTimePosted = commentTimePosted;
		PostNo = postNo;
		this.memberVO = memberVO;
	}
	
	public long getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(long commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentTimePosted() {
		return commentTimePosted;
	}
	public void setCommentTimePosted(String commentTimePosted) {
		this.commentTimePosted = commentTimePosted;
	}
	public long getPostNo() {
		return PostNo;
	}
	public void setPostNo(long postNo) {
		PostNo = postNo;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	
	@Override
	public String toString() {
		return "CommentVO [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentTimePosted="
				+ commentTimePosted + ", PostNo=" + PostNo + ", memberVO=" + memberVO + "]";
	}
}
