package org.kosta.mentors.model;

public class QuizVO {
	private long no;
	private String content;
	private String question1;
	private String question2;
	private String question3;
	private String question4;
	private String answer;
	private String category;
	private long countLike;
	private int likeFlag;
	
	public QuizVO(long no, String content, String question1, String question2, String question3, String question4, String answer,
			String category) {
		super();
		this.no = no;
		this.content = content;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.answer = answer;
		this.category = category;
	}
	
	public QuizVO(long no, String content, String question1, String question2, String question3, String question4,
			String answer, String category, long countLike) {
		super();
		this.no = no;
		this.content = content;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.answer = answer;
		this.category = category;
		this.countLike = countLike;
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

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getQuestion4() {
		return question4;
	}

	public void setQuestion4(String question4) {
		this.question4 = question4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getCountLike() {
		return countLike;
	}

	public void setCountLike(long countLike) {
		this.countLike = countLike;
	}
	
	public int getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(int likeFlag) {
		this.likeFlag = likeFlag;
	}

	@Override
	public String toString() {
		return "QuizVO [no=" + no + ", content=" + content + ", question1=" + question1 + ", question2=" + question2
				+ ", question3=" + question3 + ", question4=" + question4 + ", answer=" + answer + ", category="
				+ category + ", countLike=" + countLike + "]";
	}
	
}
