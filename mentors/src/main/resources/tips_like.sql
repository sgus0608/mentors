create table tips_like(
	id varchar2(100),
	post_no number,
	constraint tips_like_postno_fk foreign key(post_no) references tips_board(post_no) on delete cascade,
	constraint tips_like_id_fk foreign key(id) references mentors_member(id) on delete cascade,
	constraint tips_like_pk primary key(post_no, id)
)

commit

select count(*) from tips_like;

drop table tips_like;