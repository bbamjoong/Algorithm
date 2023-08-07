import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
arr = [*map(int, input().split())]

start, end = 0, n * max(arr)

# 총 몇분에 끝났는지 계산 답은 start이다.
while start <= end:
    mid = (start + end) // 2
    cnt = m
    for i in range(m):
        cnt += mid // arr[i]
    if cnt >= n:
        end = mid - 1
    else:
        start = mid + 1

# n-1분에 몇명이 탔을까?
cnt = m
for i in range(m):
    cnt += (start - 1) // arr[i]

# 시간 % 놀이기구 운행시간 == 0 일때마다 그 놀이기구에 사람이 탑승한다.
for i, num in enumerate(arr):
    if start % num == 0:
        cnt += 1
    if cnt == n:
        print(i+1)
        break