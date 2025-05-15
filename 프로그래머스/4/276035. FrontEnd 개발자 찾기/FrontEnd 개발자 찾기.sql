select distinct d.id, d.email, d.first_name, d.last_name
from developers d join skillcodes s 
    on s.code = (d.skill_code & s.code)
where s.category='Front End' order by d.id;
