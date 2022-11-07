CREATE TABLE tips_comment (
	comment_no NUMBER PRIMARY KEY,
	comment_content CLOB NOT NULL,
	comment_time_posted DATE NOT NULL,
	post_no number not null,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT tips_comment_postno_fk FOREIGN KEY(post_no) REFERENCES tips_board(post_no),
	CONSTRAINT tips_comment_id_fk FOREIGN KEY(id) REFERENCES mentors_member(id)
)

CREATE SEQUENCE tips_comment_seq;

drop table tips_comment

drop sequence tips_comment_seq

insert into tips_comment values(tips_comment_seq.nextval,'댓글댓글',sysdate,26,'java');

select*from tips_comment;

commit

select c.comment_no, c.comment_content, to_char(comment_time_posted, 'YYYY.MM.DD HH24:MI:SS') as comment_time_posted, m.nick_name, m.id
from tips_comment c
inner join mentors_member m on c.id=m.id
where c.post_no=26
order by  c.comment_no asc;


