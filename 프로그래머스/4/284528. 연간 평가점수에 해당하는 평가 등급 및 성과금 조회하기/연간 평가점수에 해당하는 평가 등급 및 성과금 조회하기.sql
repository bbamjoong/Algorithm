select e.emp_no, e.emp_name,
    case
        when avg(g.score) >= 96 then 'S'
        when avg(g.score) >= 90 then 'A'
        when avg(g.score) >= 80 then 'B'
        else 'C'
    end as grade,
    case
        when avg(g.score) >= 96 then e.sal * 0.2
        when avg(g.score) >= 90 then e.sal * 0.15
        when avg(g.score) >= 80 then e.sal * 0.1
        else 0
    end as bonus
             
             
from hr_employees e
    join hr_grade g on e.emp_no = g.emp_no
group by e.emp_no
order by e.emp_no