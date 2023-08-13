import sys
input = sys.stdin.readline

arr = []
for i in range(10):
    a, b = map(int, input().split())
    arr.append([a,b])

ans = 0
cnt = 0
for i in range(10):
    cnt -= arr[i][0]
    cnt += arr[i][1]
    ans = max(ans, cnt)

print(ans)