import sys
input = sys.stdin.readline

n = int(input())
friends = [list(input()) for _ in range(n)]

floyd = [[0] * n for _ in range(n)]

for k in range(n):
    for i in range(n):
        for j in range(n):
            if i == j: # 자기자신으로 갈 필요 없음
                continue
            if friends[i][j] == "Y" or (friends[i][k] == "Y" and friends[k][j] == "Y"):
                floyd[i][j] = 1

ans = 0
for i in floyd:
    ans = max(ans, sum(i))
print(ans)