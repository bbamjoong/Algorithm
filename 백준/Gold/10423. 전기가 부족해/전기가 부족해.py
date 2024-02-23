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
    a = find(a);
    b = find(b);

    if a < b:
        parent[b] = a;
        return;
    parent[a] = b;


def isAllConnected():  # 모든 노드의 부모가 0이면 참입니다.
    for i in range(n + 1):
        if (parent[i] != 0):
            return False;
    return True;

parent = [i for i in range(n + 1)]

for i in powerplants:  # 발전소는 노드를 0으로 잡겠습니다.
    parent[i] = 0

ans = 0;
for start, end, weight in arr:
    if (find(start) != find(end)):
        union(start, end)
        ans += weight;

        if isAllConnected(): # 모두 연결이 되어있으면 break 합니다.
            break;
            
print(ans);