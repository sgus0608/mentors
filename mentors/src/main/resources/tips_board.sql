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


















