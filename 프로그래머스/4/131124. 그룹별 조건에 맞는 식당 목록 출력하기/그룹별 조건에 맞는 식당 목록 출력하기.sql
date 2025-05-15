select p.member_name, r.review_text, date_format(r.review_date, "%Y-%m-%d") as review_date
from member_profile p join rest_review r on p.member_id = r.member_id
where p.member_id = (
    select member_id
    from rest_review
    group by member_id
    order by count(member_id) desc limit 1)

order by review_date, review_text