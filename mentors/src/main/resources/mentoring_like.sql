create table mentoring_like(
   post_no number,
   id varchar2(100),
   constraint mentoring_like_postno_fk foreign key(post_no) references mentoring_board(post_no) ON DELETE CASCADE,
   constraint mentoring_like_id_fk foreign key(id) references mentors_member(id) ON DELETE CASCADE,
   constraint mentoring_like_pk primary key(post_no, id)
)

COMMIT

drop table mentoring_like;

select * from mentoring_like;