-- CREATE database IF NOT EXISTS dw DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- USE dw;

-- 학생 테이블
CREATE TABLE IF NOT EXISTS students(
    students_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '학생 아이디',
    students_name VARCHAR(20) COMMENT '학생 이름',
    students_password VARCHAR(200) COMMENT '학생 비밀번호',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입 날짜'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 게시판 테이블
CREATE TABLE IF NOT EXISTS board
(
    board_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '게시판 아이디',
    students_id INTEGER(4) COMMENT '학생 아이디',
    title VARCHAR(50) COMMENT '제목',
    content VARCHAR(100) COMMENT '글 내용',
    update_at DATETIME COMMENT '수정 날짜',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '작성 날짜',
    CONSTRAINT board_students_id_fk FOREIGN KEY (students_id) REFERENCES students(students_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 컬럼 추가
-- ALTER TABLE board ADD COLUMN cnt INTEGER(4) DEFAULT 0;
-- root 계정이 계정을 생성함
-- '%' 모든 IP허용
-- create user sangwon@'%' identified by '123';

-- grant로 권한 부여
-- on 데이터베이스이름.테이블이름 (dw.*)
-- grant select,insert,update on dw.* to sangwon@'%';