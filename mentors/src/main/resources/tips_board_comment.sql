CREATE TABLE tips_comment (
	comment_no NUMBER PRIMARY KEY,
	comment_content CLOB NOT NULL,
	comment_time_posted DATE NOT NULL,
	post_no number not null,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT tips_comment_postno_fk FOREIGN KEY(post_no) REFERENCES tips_board(post_no) on delete cascade,
	CONSTRAINT tips_comment_id_fk FOREIGN KEY(id) REFERENCES mentors_member(id)  on delete cascade
)

CREATE SEQUENCE tips_comment_seq;

drop table tips_comment

drop sequence tips_comment_seq

insert into tips_comment values(tips_comment_seq.nextval,'댓글댓글',sysdate,26,'java');
insert into tips_comment values(tips_comment_seq.nextval,'내가 짱이야',sysdate,26,'spring');


select*from tips_comment;

commit

select c.comment_no, c.comment_content, to_char(comment_time_posted, 'YYYY.MM.DD HH24:MI:SS') as comment_time_posted, m.nick_name, m.id
from tips_comment c
inner join mentors_member m on c.id=m.id
where c.post_no=26
order by  c.comment_no asc;

delete from tips_comment where comment_no='1';

update tips_comment set comment_content='내가 진짜로 짱이야' where comment_no='4';

