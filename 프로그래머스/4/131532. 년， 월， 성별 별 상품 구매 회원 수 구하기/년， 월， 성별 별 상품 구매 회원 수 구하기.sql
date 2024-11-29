-- 코드를 입력하세요
SELECT year(sales_date) as YEAR, month(sales_date) as MONTH, GENDER, count(distinct a.user_id) as USERS
from user_info a
join online_sale b on a.user_id = b.user_id
where a.gender is not null
group by year(sales_date), month(sales_date), gender
order by year(sales_date), month(sales_date), gender