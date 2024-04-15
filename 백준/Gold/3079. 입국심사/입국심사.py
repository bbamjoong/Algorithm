import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = [int(input()) for _ in range(n)]

start, end = min(arr), max(arr) * m

while start <= end:
    total = 0
    mid = (start + end) // 2

    for i in range(n):
        total += mid // arr[i]

    if total >= m:
        end = mid - 1

    else:
        start = mid + 1

print(start)