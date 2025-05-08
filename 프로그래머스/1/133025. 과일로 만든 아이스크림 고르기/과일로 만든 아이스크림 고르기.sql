SELECT f.flavor as "FLAVOR"
from first_half f join icecream_info i
on f.flavor = i.flavor
where f.total_order > 3000 and i.ingredient_type = 'fruit_based'