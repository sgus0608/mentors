create table mentors_member (
	id VARCHAR2(100) PRIMARY KEY,
	password VARCHAR2(100) NOT NULL,
	nick_name VARCHAR2(100) NOT NULL,
	email VARCHAR2(100) NOT NULL,
	address VARCHAR2(100) NOT NULL,
	interest VARCHAR2(100) NOT NULL,
	signup_date DATE NOT NULL,
	member_type varchar2(100) not null
)

INSERT INTO mentors_member values('java','a','아이유','iu1004@naver.com','오리','자바',sysdate,'mento');