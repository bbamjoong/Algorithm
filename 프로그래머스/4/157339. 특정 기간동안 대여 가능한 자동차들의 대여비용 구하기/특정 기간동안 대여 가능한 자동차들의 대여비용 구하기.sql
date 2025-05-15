select c.car_id, c.car_type, 
    round(c.daily_fee * 30 * (100 - dp.discount_rate) / 100) as fee
from car_rental_company_car c
join car_rental_company_discount_plan dp
on c.car_type = dp.car_type
where c.car_id not in
    (select car_id
     from car_rental_company_rental_history
     where end_date >= '2022-11-01' and start_date <= '2022-12-01'
    )
    and c.car_type in ('세단', 'suv')
    and dp.duration_type like "30%"
    and round(c.daily_fee * 30 * (100 - dp.discount_rate) / 100) >= 500000
    and round(c.daily_fee * 30 * (100 - dp.discount_rate) / 100) <= 2000000