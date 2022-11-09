CREATE TABLE mentoring_comment (
	comment_no NUMBER PRIMARY KEY,
	comment_content CLOB NOT NULL,
	comment_time_posted DATE NOT NULL,
	post_no NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT mentoring_comment_postno_fk FOREIGN KEY(post_no) REFERENCES mentoring_board(post_no) ON DELETE CASCADE,
	CONSTRAINT mentoring_comment_id_fk FOREIGN KEY(id) REFERENCES mentors_member(id) ON DELETE CASCADE
)

DROP TABLE mentoring_comment;

CREATE SEQUENCE mentoring_comment_seq;

DROP SEQUENCE mentoring_comment_seq;

INSERT INTO mentoring_comment VALUES(mentoring_comment_seq.nextval, '댓글댓글', sysdate, 35, 'java');

COMMIT

SELECT * FROM mentoring_comment;

SELECT c.comment_no, c.comment_content, TO_CHAR(comment_time_posted, 'YYYY.MM.DD HH24:MI:SS') as comment_time_posted, m.id, m.nick_name
FROM mentoring_comment c
INNER JOIN mentors_member m ON c.id=m.id
WHERE c.post_no=35
ORDER BY c.comment_no ASC;

