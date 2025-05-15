# -- 코드를 입력하세요
# SELECT PROD.PRODUCT_ID, PROD.PRODUCT_NAME, SUM(PROD.PRICE * ORD.AMOUNT) TOTAL_SALES
# FROM FOOD_PRODUCT PROD JOIN FOOD_ORDER ORD ON PROD.PRODUCT_ID = ORD.PRODUCT_ID

# WHERE PRODUCE_DATE LIKE "2022-05%"

# GROUP BY PROD.PRODUCT_ID, PROD.PRODUCT_NAME
# ORDER BY TOTAL_SALES DESC, PROD.PRODUCT_ID ASC


select p.product_id, p.product_name, sum(p.price * o.amount) as total_sales
from food_product p join food_order o on p.product_id = o.product_id
where o.produce_date like "2022-05%"
group by p.product_id, p.product_name
order by total_sales desc, product_id asc