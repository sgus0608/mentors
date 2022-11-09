CREATE TABLE qna_board (
	post_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	time_posted DATE NOT NULL,
	hits NUMBER DEFAULT 0,
	category VARCHAR2(100) NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT qnaboard_fk FOREIGN KEY(id) REFERENCES mentors_member(id) ON DELETE CASCADE
)

drop TABLE qna_board

CREATE SEQUENCE qna_board_seq;
drop sequence qna_board_seq

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

-- 게시글 리스트
SELECT post_no,title,m.nick_name,category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits
FROM qna_board q
INNER JOIN mentors_member m ON m.id=q.id
ORDER BY post_no DESC

-- 상세글 보기
SELECT post_no,title,m.nick_name,category,content,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,m.id
FROM qna_board q
INNER JOIN mentors_member m ON m.id=q.id
WHERE post_no=1
ORDER BY post_no DESC

-- 글등록
insert into qna_board(post_no,title,content,time_posted,category,id) 
values (qna_board_seq.nextval,?,?,sysdate,?,?)
-- 글삭제
DELETE from qna_board where post_no=?

-- 글수정
update qna_board set category='취업',title='질문질문',content='제목수정했습니다~' where post_no=1

-- row number() over()
SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id FROM qna_board
-- 게시글 증가
INSERT INTO qna_board(post_no,title,category,content,time_posted,id)
SELECT qna_board_seq.nextval,title,category,content,sysdate,id FROM qna_board;

--sub query 이용해서 rnum 1~5  까지 출력
SELECT rnum,post_no,category,title,time_posted,hits
FROM(
SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
		  category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id FROM qna_board
)
WHERE rnum BETWEEN 1AND 5

-- step3 : step2 조회 결과에 더해서 게시물 리스트에는 회원명 즉 작성자명이 필요하다
--			JOIN 을 이용해 id가 일치하는 회원의 회원 name 을 함께 조회되도록 한다

SELECT rnum,post_no,title,category,time_posted,hits,m.nick_name
FROM (
	SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
	category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id 
	FROM qna_board) q
INNER JOIN mentors_member m ON q.id=m.id
WHERE rnum BETWEEN 1 AND 5
ORDER BY post_no DESC

COMMIT

SELECT COUNT(*) FROM qna_board

update qna_board set hits=hits+1 WHERE post_no=?

-- 검색 제목 총 게시물 수 
SELECT COUNT(*) FROM qna_board WHERE title LIKE '%질문%'

-- 검색 제목 조회
SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
	category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id 
	FROM qna_board
	WHERE title LIKE '%질문%'

-- 검색 제목 조회 (페이징네이션 포함)
SELECT rnum,post_no,title,category,time_posted,hits,m.nick_name
FROM (
	SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
	category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id 
	FROM qna_board
	WHERE title LIKE '%질문%') q
INNER JOIN mentors_member m ON q.id=m.id
WHERE rnum BETWEEN 1 AND 5
ORDER BY post_no DESC

-- 내용 검색 총 게시물 수 
SELECT COUNT(*) FROM qna_board WHERE content LIKE '%나이%'

-- 내용 검색 조회 (페이징네이션 포함)
SELECT rnum,post_no,title,category,time_posted,hits,m.nick_name
FROM (
	SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
	category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id 
	FROM qna_board
	WHERE content LIKE '%나이%') q
INNER JOIN mentors_member m ON q.id=m.id
WHERE rnum BETWEEN 1 AND 5
ORDER BY post_no DESC

-- 작성자 검색시 총게시물 수 
SELECT COUNT(*)
FROM qna_board q
INNER JOIN mentors_member m ON m.id=q.id
WHERE m.nick_name='아이유'

-- 작성자 검색시 조회
SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
	category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,m.nick_name
	FROM qna_board q
	INNER JOIN mentors_member m ON m.id=q.id
	WHERE m.nick_name='아이유'

-- 작성자 검색시 총게시물수 (페이징네이션포함)
SELECT rnum,post_no,title,category,time_posted,hits,nick_name
FROM (SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title,
category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,m.nick_name
FROM qna_board q
INNER JOIN mentors_member m ON m.id=q.id
WHERE m.nick_name='아이유')
WHERE rnum BETWEEN 1 AND 5
ORDER BY post_no DESC






