import sys
input = sys.stdin.readline
import heapq

n = int(input())
q = []

for i in range(n):
    x = int(input())

    if x == 0:
        if not q:
            print(0)
        else:
            print(heapq.heappop(q))

    else:
        heapq.heappush(q, x)