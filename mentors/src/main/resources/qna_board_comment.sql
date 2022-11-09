create table qna_comment (
	comment_no number primary key,
	comment_content clob not null,
	comment_time_posted date not null,
	post_no number not null,
	id varchar2(100) not null,
	constraint qna_comment_postno_fk foreign key(post_no) references qna_board(post_no) ON DELETE CASCADE,
	constraint qna_comment_id_fk foreign key(id) references mentors_member(id) ON DELETE CASCADE
)
drop table qna_comment
create sequence qna_comment_seq;

drop sequence qna_comment_seq;

insert into qna_comment values(qna_comment_seq.nextval,'댓글테스트',sysdate,63,'java')


commit

select * from qna_comment

select c.comment_no,c.comment_content,to_char(comment_time_posted,'YYYY.MM.DD HH24:MI:SS') as comment_time_posted,m.id,m.nick_name
from qna_comment c
inner join mentors_member m on c.id=m.id
where c.post_no=63
order by c.comment_no asc;

SELECT * FROM qna_comment
--댓글 삭제 sql
delete from qna_comment where comment_no=?

--댓글 수정 sql
update qna_comment set comment_content=? where comment_no=?; 



