import sys
input=sys.stdin.readline
sys.setrecursionlimit(100000)
from collections import deque

def cycle(start, idx, cnt):
    global circulation
    # 방문역 visited를 True로 전환
    visited[idx] = True
    for i in arr[idx]:
        # 방문하지 않은 곳은 재귀
        if visited[i] == False:
            cycle(start, i, cnt+1)
        # 만약 방문할 곳이 시작점과 같고 방문역이 2개이상이라면
        # circulation True로 만들어주고 return
        elif i == start and cnt>=2:
            circulation = True
            return

def distance():
    q = deque()
    for i in range(1,n+1):
        if check_circulation[i] == True:
            check_distance[i] = 0
            q.append(i)
    
    while q:
        a = q.popleft()
        for i in arr[a]:
            if check_distance[i] == -1111:
                q.append(i)
                check_distance[i] = check_distance[a] + 1



n = int(input())
arr = [[] for _ in range(n+1)]
# 노드별 순환선 체크
check_circulation = [False]*(n+1)
# 거리저장
check_distance = [-1111]*(n+1)

for i in range(n):
    a,b = map(int, input().split())
    arr[a].append(b)
    arr[b].append(a)

for i in range(1,n+1):
    visited = [False] * (n+1)
    circulation = False
    cycle(i,i,0)
    if circulation == True:
        check_circulation[i] = True

distance()
print(*check_distance[1:])