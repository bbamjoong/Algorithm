with front as (
    select sum(code) from skillcodes where category = 'front end'

)

select d2.grade, d2.id, d2.email
from
    (select
        case
            when skill_code & (select * from front)
                and skill_code & (select code from skillcodes where name = 'python')
            then 'A'

            when skill_code & (select code from skillcodes where name = 'C#')
            then 'B'

            when skill_code & (select * from front)
            then 'C'
        end as grade,
        id,
        email
    from developers) as d2
where d2.grade is not null
order by d2.grade, d2.id;