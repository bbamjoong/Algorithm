SELECT DATE_FORMAT(s.sales_date,'%Y-%m-%d') AS sales_date,
       s.product_id,
       NULL AS user_id,
       s.sales_amount
FROM offline_sale s
WHERE s.sales_date BETWEEN '2022-03-01' AND '2022-03-31'
UNION ALL
SELECT DATE_FORMAT(o.sales_date,'%Y-%m-%d') AS sales_date,
       o.product_id,
       o.user_id,
       o.sales_amount
FROM online_sale o
WHERE o.sales_date BETWEEN '2022-03-01' AND '2022-03-31'
ORDER BY sales_date, product_id, user_id;
