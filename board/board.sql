-- page 나누기 할때 rownum 개념 필요.
select rownum, bno, title from spring_board where rownum<=10;

-- 가장 최신글 10개 가져오기
select rownum, bno, title
from (select bno, title from spring_board order by bno desc)
where rownum<=10;

select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum, bno, title
from spring_board
where rownum<=10;

-- 1page => 가장 최신글 10개
select rn, bno, title
from(select rownum rn, bno, title
	 from (select bno, title from spring_board order by bno desc)
	 where rownum<=10)
where rn>0;

select rn, bno, title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title
	  from spring_board where rownum<=10)
where rn>0;

-- 2page => 그 다음 최신글 10개
select rn, bno, title
from(select rownum rn, bno, title
	 from (select bno, title from spring_board order by bno desc)
	 where rownum<=20)
where rn>10;

select rn, bno, title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
	  from spring_board where rownum<=20)
where rn>10;

-- page 번호가 넘어오는 경우(?에 들어가는 숫자)
-- 1을 누르면 => 10(page번호=1*10), 0((page번호=1-1*)10)
-- 2를 누르면 => 20, 10
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
	  from spring_board where rownum<=?)
where rn>?;

-- Dummy 데이터 삽입
insert into spring_board(bno, title, content, writer)
(select seq_board.nextval,title,content,writer from spring_board);

select count(*) from spring_board;

-- 검색
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
	  from spring_board 
	  where (title like '%%') and rownum<=10)
where rn>0;

-- title과 content 검색
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
	  from spring_board 
	  where (title like '%파이썬%' or content like '%파이썬%') and rownum<=10)
where rn>0;

-- title과 content과 writer 검색
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
	  from spring_board 
	  where (title like '%파이썬%' or content like '%파이썬%' or writer like '%파이썬%') and rownum<=10)
where rn>0;


-- 댓글 테이블
create table spring_reply(
	rno number(10, 0) constraint pk_reply primary key,  -- 댓글 글번호
	bno number(10, 0) not null, -- 원본글 글번호
	reply varchar2(1000) not null, -- 댓글
	replyer varchar2(50) not null, -- 댓글 작성자
	replyDate date default sysdate, -- 댓글 작성일
	updateDate date default sysdate, -- 댓글 수정일
	constraint fk_reply_board foreign key(bno) references spring_board(bno) -- 외래키 설정
);
create sequence seq_reply;

insert into spring_reply(rno,bno,reply,replyer) 
values(seq_reply.nextval,3843,'댓글 작성','홍길동');

select * from spring_reply;

--인덱스 생성
create index idx_reply on spring_reply(bno desc, rno asc);


select * from spring_reply;


--댓글 갯수를 저장할 칼럼 생성(spring_board)
alter table spring_board add(replycnt number default 0);

select * from spring_board order by bno desc;


--이미 들어간 댓글 갯수 삽입
update spring_board
set replycnt = (select count(rno) from spring_reply where spring_board.bno=spring_reply.bno);

--파일 첨부 테이블 생성
create table spring_attach(
	uuid varchar2(100) not null,
	uploadPath varchar2(200) not null,
	fileName varchar2(100) not null,
	fileType char(1) not null,
	bno number(10)
);

alter table spring_attach drop constraint pk_attach;
alter table spring_attach add constraint pk_attach primary key(uuid);

alter table spring_attach 
add constraint fk_board_attach foreign key(bno) references spring_board(bno);



select * from spring_attach;

DELETE FROM SPRING_MEMBER_AUTH;
