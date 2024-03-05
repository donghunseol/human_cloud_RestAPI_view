-- user
-- 개인
insert into user_tb(username, password, name, tel, birth, email, address, image, role, created_at)
values ('ssar', '1234', '쌀', '01012345678', '010101', 'ssar@nate.com', '쌀도 쌀시 쌀구 쌀동', null, 0, now());
insert into user_tb(username, password, name, tel, birth, email, address, image, role, created_at)
values ('cos', '1234', '코스', '01087654321', '020202', 'cos@nate.com', '코스도 코스시 코스구 코스동', null, 0, now());
insert into user_tb(username, password, name, tel, birth, email, address, image, role, created_at)
values ('love', '1234', '러브', '01012341234', '030303', 'love@nate.com', '러브도 러브시 러브구 러브동', null, 0, now());
-- 기업
insert into user_tb(username, password, name, tel, birth, email, address, image, role, created_at)
values ('comssar', '1234', '쌀회사', '01012344321', '111111', 'ssar@naver.com', '쌀도 쌀시 쌀구 쌀동', null, 1, now());
insert into user_tb(username, password, name, tel, birth, email, address, image, role, created_at)
values ('comcos', '1234', '코스회사', '01043211234', '110222', 'cos@naver.com', '코스도 코스시 코스구 코스동', null, 1, now());
insert into user_tb(username, password, name, tel, birth, email, address, image, role, created_at)
values ('comlove', '1234', '러브회사', '01011111111', '130313', 'love@naver.com', '러브도 러브시 러브구 러브동', null, 1, now());


-- skill
-- 이력서 (개인)
insert into skill_tb(resume_id, notice_id, name, role)
values (1, null, 'java', 0);
insert into skill_tb(resume_id, notice_id, name, role)
values (2, null, 'java', 0);
insert into skill_tb(resume_id, notice_id, name, role)
values (2, null, 'html', 0);
-- 공고 (회사)
insert into skill_tb(resume_id, notice_id, name, role)
values (null, 1, 'java', 0);
insert into skill_tb(resume_id, notice_id, name, role)
values (null, 2, 'java', 0);
insert into skill_tb(resume_id, notice_id, name, role)
values (null, 2, 'sql', 0);

-- apply
insert into apply_tb(resume_id, notice_id, created_at)
values (1, 1, now());
insert into apply_tb(resume_id, notice_id, created_at)
values (2, 1, now());
insert into apply_tb(resume_id, notice_id, created_at)
values (2, 2, now());

-- board
insert into board_tb(user_id, title, content, created_at)
values (1, '제목1', '내용1', now());
insert into board_tb(user_id, title, content, created_at)
values (1, '제목2', '내용2', now());
insert into board_tb(user_id, title, content, created_at)
values (2, '제목3', '내용3', now());
insert into board_tb(user_id, title, content, created_at)
values (2, '제목4', '내용4', now());
insert into board_tb(user_id, title, content, created_at)
values (2, '제목5', '내용5', now());
insert into board_tb(user_id, title, content, created_at)
values (2, '제목6', '내용6', now());

-- love
insert into love_tb(user_id, board_id, created_at)
values (1, 1, now());
insert into love_tb(user_id, board_id, created_at)
values (1, 2, now());
insert into love_tb(user_id, board_id, created_at)
values (2, 1, now());

-- reply
insert into reply_tb(user_id, board_id, comment, created_at)
values (1, 1, '안녕하세요', now());
insert into reply_tb(user_id, board_id, comment, created_at)
values (1, 2, '반가워요', now());
insert into reply_tb(user_id, board_id, comment, created_at)
values (2, 1, '졸리네요', now());

-- resume
insert into resume_tb(user_id, career, license, education, major, created_at)
values (1, '1년', '정보처리기사, SQLD', '쌀대학교, 졸업', '컴퓨터공학과', now());
insert into resume_tb(user_id, career, license, education, major, created_at)
values (2, '신입', '정보처리기사', '코스대학교, 졸업', '소프트웨어과', now());
insert into resume_tb(user_id, career, license, education, major, created_at)
values (3, '2년', 'SQLD', '러브대학교, 3학년', '정보처리데이터과', now());

-- notice
insert into notice_tb(user_id, title, type, field, work_place, content, deadline, created_at)
values (4, '공고 제목1', '고용형태1', '개발분야1', '근무지1', '공고 내용1', '20240303',now());
insert into notice_tb(user_id, title, type, field, work_place, content, deadline, created_at)
values (5, '공고 제목2', '고용형태2', '개발분야2', '근무지2', '공고 내용2', '20240404', now());
insert into notice_tb(user_id, title, type, field, work_place, content, deadline, created_at)
values (6, '공고 제목3', '고용형태3', '개발분야3', '근무지3', '공고 내용3', '20240505', now());

-- scrap
insert into scrap_tb(user_id, resume_id, notice_id, role, created_at) values(1, null, 1, 1, now());
insert into scrap_tb(user_id, resume_id, notice_id, role, created_at) values(1, null, 2, 1, now());
insert into scrap_tb(user_id, resume_id, notice_id, role, created_at) values(2, null, 3, 1, now());
insert into scrap_tb(user_id, resume_id, notice_id, role, created_at) values(4, 1, null, 0, now());
insert into scrap_tb(user_id, resume_id, notice_id, role, created_at) values(4, 2, null, 0, now());
insert into scrap_tb(user_id, resume_id, notice_id, role, created_at) values(5, 2, null, 0, now());
insert into scrap_tb(user_id, resume_id, notice_id, role, created_at) values(5, 3, null, 0, now());

