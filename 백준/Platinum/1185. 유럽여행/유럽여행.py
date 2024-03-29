import sys
input = sys.stdin.readline

def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x = find(x)
    y = find(y)
    
    if x < y:
        parent[y] = x
        return;

    parent[x] = y

n, m = map(int, input().split())

countryCost = [1e9] # 도시방문비용
for _ in range(n):
    countryCost.append(int(input()))

arr = [] #간선
for _ in range(m):
    start, end, weight = map(int, input().split())
    arr.append([start, end, countryCost[start] + countryCost[end] + 2 * weight])
arr.sort(key = lambda x : x[2])

parent = [i for i in range(n + 1)]

ans = min(countryCost) #가장 방문 비용이 적은 곳이 출발점
cnt = 1
for start, end, weight in arr:
    if find(start) != find(end):
        union(start, end)
        ans += weight
        cnt += 1

        if cnt == n:
            break

print(ans)