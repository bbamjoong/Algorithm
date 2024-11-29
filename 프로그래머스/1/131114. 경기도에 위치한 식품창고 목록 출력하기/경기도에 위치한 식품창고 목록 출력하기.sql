-- 코드를 입력하세요
SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, ifnull(FREEZER_YN, "N") as FREEZER_YN from food_warehouse
where address like "%경기%"