create table quiz_board(
	quiz_no number primary key,
	quiz_content clob not null,
	question1 varchar2(100) not null, 
	question2 varchar2(100) not null, 
	question3 varchar2(100) not null, 
	question4 varchar2(100) not null,
	answer varchar2(100) not null,
	category varchar2(100) not null
)

insert into QUIZ_BOARD
values(quiz_board_seq.nextval, '이순신은 조선 중기의 무신이었다. 본관은 덕수, 자는 여해, 시호는 충무였으며, 한성 출신이었다. 문반 가문 출신으로 1576년 무과에 급제하여 그 관직이 동구비보 권관, 훈련원 봉사, 발포진 수군만호, 조산보 만호, 전라좌도수사를 거쳐 정헌대부 삼도수군통제사에 이르렀다. 그래서 김훈진의 나이는?','25','125','24','14','14','JAVA')

insert into QUIZ_BOARD
values(quiz_board_seq.nextval,'제임스 아서 고슬링은 캐나다의 소프트웨어 개발자이다. 자바를 최초 개발하여 자바의 아버지라 불리며, 가장 영향력 있는 프로그래머들 가운데 한 사람이다. 자바 이외에도 다중 프로세서용 유닉스와 컴파일러, 메일 시스템, 데이터 인식 시스템 등을 개발하였다. 그래서 박진수의 키는?','289','9999','1890','189','189','자격증')

insert into QUIZ_BOARD
values(quiz_board_seq.nextval,'법정은 대한민국의 불교 승려이자 수필가다. 무소유의 정신으로 널리 알려져 있으며 수십 권이 넘는 저서를 통해 자신의 철학을 널리 전파했다. 류은진의 직업은?','스님','개발자','사업가','축구선수','개발자','백엔드')

insert into QUIZ_BOARD
values(quiz_board_seq.nextval,'손흥민은 대한민국의 축구 선수이다. 현재 잉글랜드 프리미어리그 토트넘 홋스퍼에서 윙어로 활약하고 있으며 대한민국 축구 국가대표팀의 주장으로도 활약하고 있다. 천하개발조의 주장인 김동훈의 키는?','188','208','140','2','188','프론트엔드')

CREATE SEQUENCE quiz_board_seq;
update QUIZ_BOARD set answer = '24' where question4='14'
commit
select * from QUIZ_BOARD
COMMIT

select quiz_no, quiz_content,question1,question2,question3,question4,answer,category from quiz_board