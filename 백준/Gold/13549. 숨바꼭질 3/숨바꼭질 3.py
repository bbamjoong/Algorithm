import sys
input=sys.stdin.readline
from collections import deque
sys.setrecursionlimit(10**6)

def bfs():
    q = deque()             
    q.append(n)            
    while  q:
        x = q.popleft()     
        if x == k:
            print(dist[x])
            break
        for nx in (x - 1, x + 1, x * 2):    
            if 0 <= nx <= MAX and dist[nx] == -1:
                if nx == 2*x:
                    dist[nx] = dist[x]
                    q.appendleft(nx)
                else:
                    dist[nx] = dist[x] + 1
                    q.append(nx)    

MAX = 10 ** 5              
dist = [-1] * (MAX + 1)      
n, k = map(int, input().split())
dist[n] = 0

bfs()