with ans as (
    select * from first_half
union all
select * from july
)
    
select flavor
from ans
group by flavor
order by sum(total_order) desc
limit 3;