select it.item_id, ii.item_name, ii.rarity
from item_info as ii
join item_tree as it
  on ii.item_id = it.item_id
where it.parent_item_id in (
    select item_id
    from item_info
    where rarity = 'rare'
)
order by it.item_id desc;