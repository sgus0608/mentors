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


-- 게시글 리스트

select post_no,title,m.nick_name,category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits
from tips_board t
inner join mentors_member m on m.id=t.id
order by post_no DESC

-- 상세글보기

select post_no,title,m.nick_name,category,content,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits, m.id
from tips_board t
inner join mentors_member m on m.id=t.id
where post_no=1
order by post_no desc


-- 글등록
insert into tips_board(post_no,title,content,time_posted,category,id)
values(tips_board_seq.nextval,'안녕하세요','반갑습니다',sysdate,'정처기','java');

select*from tips_board

commit

--글 삭제
delete from tips_board where post_no='1';

-- 글 수정
update tips_board set category='채용', title='안녕하세요', content='잘부탁드립니다' where post_no='3';

-- row number() over()
select row_number() over(order by post_no desc) as rnum, post_no, title, 
category, TO_char(time_posted,'YYYY.MM.DD') as time_posted, hits, id
from tips_board


--
INSERT INTO tips_board(post_no,title,category,content,time_posted,id)
SELECT tips_board_seq.nextval, title, category, content, sysdate, id FROM tips_board


-- sub query 이용해서 rnum 1-5 까지 출력
select rnum , post_no, title, category, time_posted, hits, m.nick_name
from(
	SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC)AS rnum, post_no, title, category,
	TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits, id
	FROM tips_board
) t
inner join mentors_member m on t.id=m.id
where rnum between 1 and 5
order by post_no desc

select count(*) from tips_board

commit


-- 조회수 증가
update tips_board set hits=hits+1 where post_no=2;


-- 검색 제목 조회
SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC)AS rnum, post_no, title, category,
TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits, id
FROM tips_board
where title like '%앙녕%'

-- 검색 제목 총 게시물 수
select count(*) from tips_board where title like  '%앙녕%';


--검색 제목 조회( 페이지네이션 포함)
select rnum , post_no, title, category, time_posted, hits, m.nick_name
from(
	SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC)AS rnum, post_no, title, category,
	TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits, id
	FROM tips_board
	where title like '%앙녕%'
) t
inner join mentors_member m on t.id=m.id
where rnum between 1 and 5
order by post_no desc;


