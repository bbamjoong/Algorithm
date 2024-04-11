import sys
input = sys.stdin.readline

n = int(input())
parents = [0, 1] + list(map(int, input().split()))
values = list(map(int, input().split()))

values.sort(reverse=True)

ans = 0
for i in range(n):
    ans += values[i]
    print(ans)