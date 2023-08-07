import sys
input=sys.stdin.readline
from collections import deque
sys.setrecursionlimit(10**6)

def path(x):
    arr = []
    temp = x
    for _ in range(dist[x]+1):
        arr.append(temp)
        temp = move[temp]
    print(*arr[::-1])

def bfs():
    q = deque()             
    q.append(n)            
    while  q:
        x = q.popleft()     
        if x == k:
            print(dist[x])
            path(x)
            break
        for nx in (x - 1, x + 1, x * 2):    
            if 0 <= nx <= MAX and not dist[nx]:
                dist[nx] = dist[x] + 1
                move[nx] = x
                q.append(nx)    

MAX = 10 ** 5              
dist = [0] * (MAX + 1)      
move = [0] * (MAX + 1)
n, k = map(int, input().split())

bfs()