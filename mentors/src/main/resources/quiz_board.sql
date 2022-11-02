CREATE TABLE quiz_board(
	quiz_no NUMBER PRIMARY KEY,
	quiz_content CLOB NOT NULL,
	question1 VARCHAR2(100) NOT NULL,
	question2 VARCHAR2(100) NOT NULL,
	question3 VARCHAR2(100) NOT NULL,
	question4 VARCHAR2(100) NOT NULL,
	answer VARCHAR2(100) NOT NULL,
	category VARCHAR2(100) NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT quizboard_fk FOREIGN KEY(id) REFERENCES mentors_member(id)
)

CREATE SEQUENCE quiz_board_seq;

COMMIT