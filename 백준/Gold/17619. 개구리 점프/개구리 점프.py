import sys

input = sys.stdin.readline


def find(x):
    if x == parent[x]:
        return x;
    parent[x] = find(parent[x])
    return parent[x];


def union(a, b):
    a = find(a);
    b = find(b);

    if a > b:
        parent[b] = a;
        return;
    parent[a] = b;


n, q = map(int, input().split());
logs = []
for i in range(n):
    x1, x2, y = map(int, input().split())
    logs.append([x1, x2, y, i + 1])  # 통나무에 index 부여

logs.sort(key=lambda x: x[0])  # 시작점을 기준으로 정렬

parent = [i for i in range(n + 1)]

first_x1, first_x2, first_y, first_idx = logs[0];  # 시작점

# 1. 겹친다. -> 나아갈 수 있다.
# 2. 겹친다. -> 나아갈 수 없다. (다음게 더 빨리 끝나버림)
# 3. 겹치지 않는다. -> 나아갈 수 없다.

for next_x1, next_x2, next_y, next_idx in logs:
    if next_x1 <= first_x2:  # 겹치면
        union(first_idx, next_idx)

        # 정보 갱신
        # 1. 다음으로 나아갈 수 있는 조건이면
        if next_x2 >= first_x2:
            first_x1, first_x2, first_y, first_idx = next_x1, next_x2, next_y, next_idx

    # 2. 다음으로 나아갈 수 없으면 first정보 유지

    # 3. 겹치지 않는다. 나아갈 수 없다.
    else:
        first_x1, first_x2, first_y, first_idx = next_x1, next_x2, next_y, next_idx

for i in range(q):
    loga, logb = map(int, input().split())
    if find(loga) == find(logb):
        print(1);
        continue;
    print(0);