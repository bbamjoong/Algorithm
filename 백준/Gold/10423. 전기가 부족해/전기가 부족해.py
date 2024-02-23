import sys

input = sys.stdin.readline

n, m, k = map(int, input().split())
powerplants = list(map(int, input().split()))

arr = [list(map(int, input().split())) for _ in range(m)]
arr.sort(key=lambda x: x[2])  # 가중치를 기준으로 정렬


# 크루스칼을 이용해보자 -> union - find
def find(x):
    if parent[x] == x:
        return x;
    parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    global cnt
    a = find(a);
    b = find(b);

    if a == b:
        return;

    # 어떤 노드의 부모가 0으로 바뀌게 되면 cnt를 증가시켜줍니다.
    if a == 0 or b == 0:
        cnt += 1

    if a < b:
        parent[b] = a;
    elif a > b:
        parent[a] = b;


parent = [i for i in range(n + 1)]
for i in powerplants:  # 발전소는 노드를 0으로 잡겠습니다.
    parent[i] = 0

cnt = len(powerplants)  # cnt는 parent[index] = 0인 곳들의 count
ans = 0;
for start, end, weight in arr:
    if find(start) != find(end):
        union(start, end)
        ans += weight;

        if cnt == n:  # 모두 연결이 되어있으면 break 합니다.
            break;

print(ans);
