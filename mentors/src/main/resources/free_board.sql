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

-- 페이지네이션을 위한 sql
select post_no, title, m.nick_name,time_posted, hits
from(
	select row_number() over(order by post_no desc) as rnum, 
	post_no, title,
	to_char(time_posted,'YYYY.MM.DD') as time_posted, 
	id , hits from free_board
) f
inner join mentors_member m on f.id=m.id
where rnum between 1 and 5
order by f.post_no desc

commit

insert into free_board(post_no, title, content, time_posted,id)
select free_board_seq.nextval, title, content, sysdate, id from free_board





