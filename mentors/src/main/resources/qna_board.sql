CREATE TABLE qna_board (
	post_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE NOT NULL,
	hits NUMBER DEFAULT 0,
	category VARCHAR2(100) NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT qnaboard_fk FOREIGN KEY(id) REFERENCES mentors_member(id)
)


CREATE SEQUENCE qna_board_seq;


INSERT INTO qna_board VALUES(qna_board_seq.nextval, '질문이요', '자바를 어떻게 해야 쉽게 배울까요', sysdate, 0, '프로그래밍', 'java');


COMMIT

SELECT * FROM qna_board;