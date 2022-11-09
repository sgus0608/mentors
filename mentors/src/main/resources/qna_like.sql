create table qna_like(
   id varchar2(100),
   post_no number,
   constraint qna_like_postno_fk foreign key(post_no) references qna_board(post_no) ON DELETE CASCADE,
   constraint qna_like_id_fk foreign key(id) references mentors_member(id) ON DELETE CASCADE,
   constraint qna_like_pk primary key(post_no, id)
)

SELECT COUNT(*) FROM qna_like WHERE post_no=61 AND id='java'

drop table qna_like

commit
ON DELETE CASCADE