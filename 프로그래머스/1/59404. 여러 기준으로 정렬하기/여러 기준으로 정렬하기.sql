
-- 코드를 입력하세요
-- 아이디, 이름, 보호 시작일을 이름 순으로 조회하는 SQL문을 작성
-- 이름이 같으면 보호를 나중에 시작한 동물
SELECT ANIMAL_ID, NAME, DATETIME from animal_ins
order by name asc, datetime desc