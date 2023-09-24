import sys
input = sys.stdin.readline

n = int(input())

check = [list(map(int, input().split())) for _ in range(n)]

distance = [0]
for i in range(n-1):
    dist = abs(check[i + 1][0] - check[i][0]) + abs(check[i + 1][1] - check[i][1])
    distance.append(dist)

ans = sum(distance)

min_dist = 1e9
for i in range(1,n-1):
    dist = ans - (distance[i] + distance[i + 1]) + abs(check[i + 1][0] - check[i - 1][0]) + abs(check[i + 1][1] - check[i - 1][1])
    min_dist = min(min_dist, dist)

print(min_dist)