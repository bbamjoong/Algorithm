import sys
input = sys.stdin.readline
import heapq

q = []
t = int(input())
for i in range(t):
    n = int(input())
    if n == 0:
        if len(q)==0:
            print(0)
        else:
            print(-heapq.heappop(q))
    else:
        heapq.heappush(q,-n)