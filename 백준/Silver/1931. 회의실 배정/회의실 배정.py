import sys
input = sys.stdin.readline

n = int(input().strip())

arr = [list(map(int, input().split())) for _ in range(n)]
arr.sort(key = lambda x:(x[1],x[0]))

finish = 0
ans = 0

for i in arr:
    if i[0] >= finish:
        finish = i[1]
        ans += 1

print(ans)
