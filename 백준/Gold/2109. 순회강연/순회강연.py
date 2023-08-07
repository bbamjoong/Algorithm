import sys
input=sys.stdin.readline
import heapq

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
arr.sort(key = lambda x : x[1])

tmp = []
for i in arr:
    heapq.heappush(tmp, i[0])
    if len(tmp) > i[1]:
        heapq.heappop(tmp)

print(sum(tmp))