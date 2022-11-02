CREATE TABLE mentoring_board (
	post_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE NOT NULL,
	hits NUMBER DEFAULT 0,
	category VARCHAR2(100) NOT NULL,
	role VARCHAR2(100) NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT mentoringboard_fk FOREIGN KEY(id) REFERENCES mentors_member(id)
)

DROP TABLE mentoring_board;

CREATE SEQUENCE mentoring_board_seq;

DROP SEQUENCE mentoring_board_seq;

INSERT INTO mentoring_board VALUES(mentoring_board_seq.nextval, '재밌다', '너무재밌다', sysdate, 0, '자바', '멘토', 'java');

SELECT * FROM mentoring_board;
