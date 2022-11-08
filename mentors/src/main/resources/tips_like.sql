create table tips_like(
	id varchar2(100),
	post_no number,
	constraint tips_like_postno_fk foreign key(post_no) references tips_board(post_no),
	constraint tips_like_id_fk foreign key(id) references mentors_member(id),
	constraint tips_like_pk primary key(post_no, id)
)

commit

select count(*) from tips_like where post_no=? and id=?