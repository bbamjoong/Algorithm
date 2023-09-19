import sys
input = sys.stdin.readline
from collections import deque

a, b = map(int, input().split())

def bfs():
    global a, b
    q = deque()
    q.append((a,1))

    while q:
        num, cnt = q.popleft()

        if num == b:
            return cnt
            break

        if num < b:
            q.append((2*num, cnt+1))
            q.appendleft((int(str(num)+'1'),cnt+1))

ans = bfs()

if ans:
    print(ans)
else:
    print(-1)