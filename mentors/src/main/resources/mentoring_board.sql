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

COMMIT

-- 리스트 목록 조회
SELECT b.post_no, b.title, b.hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted, b.category, b.role, m.nick_name
FROM mentoring_board b
INNER JOIN mentors_member m ON b.id=m.id
ORDER BY b.post_no DESC;

-- 상세글 보기
SELECT b.post_no, b.title, b.content, b.hits, TO_CHAR(time_posted, 'YYYY.MM.DD HH24:MI:SS') as time_posted, b.category, b.role, m.id, m.nick_name
FROM mentoring_board b
INNER JOIN mentors_member m ON b.id=m.id
WHERE b.post_no=1;

-- 글등록
INSERT INTO mentoring_board(post_no, title, content, time_posted, category, role, id)
VALUES(mentoring_board_seq.nextval, '테스트임다', '테스트에요~~~~', sysdate, '자바', '멘토', 'java');

-- 글수정
UPDATE mentoring_board SET category='파이썬', role='멘티', title='테스투', content='테' WHERE post_no=2;

-- 글삭제
DELETE FROM mentoring_board WHERE post_no=2;


-- 글 복제
INSERT INTO mentoring_board(post_no, title, content, time_posted, category, role, id)
SELECT mentoring_board_seq.nextval, title, content, sysdate, category, role, id FROM mentoring_board

-- row_number() over()
SELECT row_number() over(ORDER BY post_no DESC) as rnum, post_no, title, hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted, category, role, id
FROM mentoring_board

-- sub query 이용해서 rnum 1 ~ 5 까지 출력
SELECT b.rnum, b.post_no, b.title, b.hits, b.time_posted, b.category, b.role, m.id, m.nick_name
FROM(
	SELECT row_number() over(ORDER BY post_no DESC) as rnum,
	post_no,title, hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted,
	category, role, id
	FROM mentoring_board
) b INNER JOIN mentors_member m ON b.id=m.id
WHERE rnum BETWEEN 1 AND 5
ORDER BY b.post_no DESC;


COMMIT


SELECT * FROM mentoring_board

















