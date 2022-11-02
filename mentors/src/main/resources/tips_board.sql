CREATE TABLE tips_board (
	post_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE NOT NULL,
	hits NUMBER DEFAULT 0,
	category VARCHAR2(100) NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT tipsboard_fk FOREIGN KEY(id) REFERENCES mentors_member(id)
)


CREATE SEQUENCE tips_board_seq;


INSERT INTO tips_board VALUES(tips_board_seq.nextval, '자바개꿀팁', '열심히하세요', sysdate, 0, '프로그래밍', 'java');


COMMIT

SELECT * FROM tips_board;