import sys
input = sys.stdin.readline

n, m = map(int, input().split())
li = list(map(int, input().split()))

start, end = 1, max(li)

while start <= end:
    mid = (start + end) //2

    cnt = 0
    for i in li:
        if i >= mid:
            cnt += i // mid

    # 최대 값을 구해야 하니 upper bound
    if cnt >= n:
        start = mid + 1
    elif cnt < n:
        end = mid - 1

print(end)