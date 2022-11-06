CREATE TABLE free_comment (
	comment_no NUMBER PRIMARY KEY,
	comment_content CLOB NOT NULL,
	comment_time_posted DATE NOT NULL,
	post_no number not null,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT freeboard_comment_postno_fk FOREIGN KEY(post_no) REFERENCES free_board(post_no),
	CONSTRAINT freeboard_comment_id_fk FOREIGN KEY(id) REFERENCES mentors_member(id)
)

CREATE SEQUENCE free_comment_seq;

insert into free_comment values(free_comment_seq.nextval,'댓글입니다',sysdate,40,'java')

commit

select * from free_comment

select c.comment_content, 
to_char(comment_time_posted,'YYYY.MM.DD HH24:MI:SS') as comment_time_posted, 
m.nick_name, m.id
from free_comment c
inner join mentors_member m on c.id=m.id
where c.post_no=40
order by c.comment_no asc



















