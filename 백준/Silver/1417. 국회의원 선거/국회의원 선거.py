import sys
input = sys.stdin.readline
import heapq

n = int(input())

hq = []
for i in range(n):
    vote = int(input())
    if i == 0:
        dasom = vote
        continue

    heapq.heappush(hq, -vote) # MaxHeap 사용.

cnt = 0
while hq:
    vote = -heapq.heappop(hq)
    
    if vote < dasom:
        break

    dasom += 1
    cnt += 1
    heapq.heappush(hq, -(vote - 1))

print(cnt)