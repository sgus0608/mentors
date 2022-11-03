package org.kosta.mentors.model;

public class QuizVO {
	private long no;
	private String content;
	private String que1;
	private String que2;
	private String que3;
	private String que4;
	private String answer;
	private String Category;
	public QuizVO(long no, String content, String que1, String que2, String que3, String que4, String answer,
			String category) {
		super();
		this.no = no;
		this.content = content;
		this.que1 = que1;
		this.que2 = que2;
		this.que3 = que3;
		this.que4 = que4;
		this.answer = answer;
		Category = category;
	}
	public QuizVO() {
		super();
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQue1() {
		return que1;
	}
	public void setQue1(String que1) {
		this.que1 = que1;
	}
	public String getQue2() {
		return que2;
	}
	public void setQue2(String que2) {
		this.que2 = que2;
	}
	public String getQue3() {
		return que3;
	}
	public void setQue3(String que3) {
		this.que3 = que3;
	}
	public String getQue4() {
		return que4;
	}
	public void setQue4(String que4) {
		this.que4 = que4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	@Override
	public String toString() {
		return "QuizVO [no=" + no + ", content=" + content + ", que1=" + que1 + ", que2=" + que2 + ", que3=" + que3
				+ ", que4=" + que4 + ", answer=" + answer + ", Category=" + Category + "]";
	}
	
	
}
