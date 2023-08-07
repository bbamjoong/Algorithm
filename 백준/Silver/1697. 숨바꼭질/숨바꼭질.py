import sys
input=sys.stdin.readline
from collections import deque
maxi = 100001
s, d = map(int, input().split())
visited = [0] * maxi

def bfs():
    q = deque()
    q.append(s)
    
    while q:
        x = q.popleft()
        if x == d:
            print(visited[x])
            break
        for nx in (x-1, x+1, x*2):
            if 0<=nx<maxi and visited[nx]==0:
                visited[nx] = visited[x]+1
                q.append(nx)

bfs()
