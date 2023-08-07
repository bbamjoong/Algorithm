import sys
input=sys.stdin.readline
sys.setrecursionlimit(100000)
from collections import deque

n = int(input())
graph = [[] for _ in range(n+1)]
for i in range(n-1):
    a,b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

test_case = list(map(int, input().split()))

visited = [-1] * (n+1)
children = [set() for _ in range(n+1)]


def bfs(start):
    q = deque()
    q.append(start)
    visited[start]=0
    
    while q:
        x = q.popleft()
        for i in graph[x]:
            if visited[i] == -1:
                visited[i] = visited[x] + 1
                children[x].add(i)
                q.append(i)

bfs(1)

idx = 1
for i in test_case:
    if idx == n:
        break
    c_length = len(children[i])
    c1 = set(test_case[idx:idx+c_length])
    c2 = children[i]

    if c1!=c2:
        print(0)
        exit()
    idx += c_length
print(1)