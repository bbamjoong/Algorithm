select a.author_id, a.author_name, b.category, sum(s.sales * b.price) as total_sales
from book b join author a
    on b.author_id = a.author_id
    join book_sales s
    on b.book_id = s.book_id
where sales_date between '2022-01-01' and '2022-01-31'
group by a.author_name, b.category
order by a.author_id asc, b.category desc