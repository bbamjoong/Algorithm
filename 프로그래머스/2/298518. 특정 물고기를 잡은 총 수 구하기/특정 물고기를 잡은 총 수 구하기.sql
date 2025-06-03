select count(fish_name) as fish_count
from fish_info i join fish_name_info n
    on i.fish_type = n.fish_type
where n.fish_name in ('SNAPPER', 'BASS')