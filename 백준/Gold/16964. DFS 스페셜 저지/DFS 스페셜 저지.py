import sys
input=sys.stdin.readline
sys.setrecursionlimit(100000)
from collections import deque

def dfs(test_case):
    x = test_case.popleft()
    if not test_case:
        print(1)
        exit()

    visited[x] = 1
    for i in range(len(graph[x])):
        if test_case[0] in graph[x] and visited[test_case[0]]==0:
            dfs(test_case)


n = int(input())
graph = [[] for _ in range(n+1)]
for i in range(n-1):
    a,b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

test_case = deque(map(int, input().split()))
visited = [0] * (n+1)

if test_case[0] != 1:
    print(0)
else:
    dfs(test_case)
    print(0)
