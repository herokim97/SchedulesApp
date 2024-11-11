-- 1. create, 테이블 생성 쿼리
    CREATE TABLE SCHEDULES (
        title varchar(100) not null,
        work varchar(200) not null,
        id BIGINT PRIMARY KEY auto_increment,
        user_id varchar(50) not null,
        passWord varchar(50) not null,
        cratedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updatedAt TIMESTAMP
    );

-- 2. insert, 일정 생성 쿼리
    INSERT INTO SCHEDULES (title, work, user_id, passWord)
    VALUES ('제목 예제 1', '내용 예제 1', 'user_id 예제 1', '1234');

-- 3. select, 전체 일정 조회 쿼리
    SELECT *
    FROM SCHEDULES;

-- 4. select, 특정 일정 조회 쿼리
    SELECT *
    FROM SCHEDULES
    WHERE id = 1;

    SELECT *
    FROM SCHEDULES
    WHERE createdAt = '2024-11-11';

-- 5. update, 특정 일정 수정 쿼리
    UPDATE SCHEDULES
    SET title ='수정된 제목 1', work = '수정된 예제 1'
    WHERE id = 1;

-- 6. delete, 특정 일정 삭제 쿼리
    DELETE FROM SCHEDULES WHERE id = 1;