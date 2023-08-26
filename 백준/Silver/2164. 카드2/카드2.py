import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
if n == 1:
    print(1)
    exit(0)

q = deque()
for i in range(n):
    q.append(i+1)

while True:
    q.popleft()
    if len(q) == 1:
        print(q[0])
        break
    a = q.popleft()
    q.append(a)
