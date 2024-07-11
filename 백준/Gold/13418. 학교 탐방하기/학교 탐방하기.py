import sys

input = sys.stdin.readline


# 학교들을 탐방해야하니까 mst를 구성해본다.

# 단, 내리막길을 위주로 연결을 하도록 한다.
# 최악의 경우는 오르막길을 위주로 연결을 하도록 한다.

# 내리막길, 오르막길을 가중치로 가정한다.

def find(x):
    if x == parent[x]:
        return parent[x]

    parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a

    else:
        parent[a] = b


# 건물, 도로의 개수
n, m = map(int, input().split())
edge = [list(map(int, input().split())) for _ in range(m + 1)]

# 최소 비용
edge.sort(key=lambda x: x[2])
parent = [_ for _ in range(n + 1)]

max_ans = 0
for start, end, weight in edge:

    a = find(start)
    b = find(end)

    if a != b:
        union(a, b)
        if weight == 0:
            max_ans += 1

# 최대 비용
edge.sort(key=lambda x: -x[2])
parent = [_ for _ in range(n + 1)]

min_ans = 0
for start, end, weight in edge:

    a = find(start)
    b = find(end)

    if a != b:
        union(a, b)
        if weight == 0:
            min_ans += 1

print(max_ans ** 2 - min_ans ** 2)