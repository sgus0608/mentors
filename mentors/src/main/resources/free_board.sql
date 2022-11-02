CREATE TABLE free_board (
	post_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE NOT NULL,
	hits NUMBER DEFAULT 0,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT freeboard_fk FOREIGN KEY(id) REFERENCES mentors_member(id)
)

DROP TABLE free_board;

DROP SEQUENCE free_board_seq;

CREATE SEQUENCE free_board_seq;

INSERT INTO free_board VALUES(free_board_seq.nextval, '배고파', '너무 배고프다', sysdate, 0, 'java')

commit

SELECT * FROM free_board

