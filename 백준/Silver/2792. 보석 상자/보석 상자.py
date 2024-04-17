import sys
input = sys.stdin.readline

n, m = map(int, input().split())
li = [int(input()) for _ in range(m)]

start, end = 1, max(li)

ans = int(1e9)

while start <= end:
    mid = (start + end) //2

    cnt = 0
    for i in li:
        cnt += i // mid
        if i % mid != 0:
            cnt += 1

    # 최소 값을 구해야 하니 under bound
    if cnt > n:
        start = mid + 1

    else:
        end = mid - 1
        # ans = mid


print(start)