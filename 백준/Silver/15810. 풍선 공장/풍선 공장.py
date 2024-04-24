import sys
input = sys.stdin.readline

n, m = map(int, input().split())
li = list(map(int, input().split()))

start, end = min(li), m * max(li)

while start <= end:
    mid = (start + end) // 2

    cnt = 0
    for i in li:
        cnt += mid // i

    # 더 많이 만든다면 낮은쪽으로 범위 축소
    # 최소시간을 구해야하므로 under bound
    if cnt >= m:
        end = mid - 1

    elif cnt < m:
        start = mid + 1

print(start)