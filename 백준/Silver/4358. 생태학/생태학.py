import sys
input = sys.stdin.readline


species_map = dict()

def init():
    global species_map

    cnt = 0
    while True:
        tree = input().rstrip()
        if tree == '':
            break
        cnt += 1

        if tree in species_map:
            species_map[tree] += 1
        else:
            species_map[tree] = 1
    return cnt


total_cnt = init()

# 딕녀서리 정렬
res = dict(sorted(species_map.items()))

for i in res:
    tree_cnt = res[i]
    percentage = (tree_cnt / total_cnt * 100)

    print(f"{i} {percentage:.4f}")