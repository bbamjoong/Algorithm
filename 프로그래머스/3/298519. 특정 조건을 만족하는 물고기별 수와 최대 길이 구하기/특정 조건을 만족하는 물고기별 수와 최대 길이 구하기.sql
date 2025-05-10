with normalized as (
  select
    fish_type,
    ifnull(length,10) as len
  from fish_info
)

select count(*) as fish_count, max(len) as max_length, fish_type
from normalized
group by fish_type
having avg(len)>=33
order by fish_type;
