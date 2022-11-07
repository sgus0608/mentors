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

SELECT rnum,post_no,title,category,time_posted,hits,m.nick_name
FROM (
	SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
	category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id 
	FROM qna_board
	WHERE title LIKE '%김돌이%') q
INNER JOIN mentors_member m ON q.id=m.id
WHERE rnum BETWEEN 1 AND 5
ORDER BY post_no DESC














