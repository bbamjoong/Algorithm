import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))

start, end = 0, max(arr)

while start <= end:
    mid = (start + end) // 2

    ans = 0
    for i in arr:
        if i - mid > 0:
            ans += i - mid

    if ans >= m :
        start = mid + 1

    else:
        end = mid - 1

print(end)
