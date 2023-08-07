import sys
input = sys.stdin.readline
from collections import deque

# n : 섬 개수, m: 다리 개수
n, m = map(int, input().split())
arr = [[] for _ in range(n+1)]

for i in range(m):
    a,b,c = map(int, input().split())
    arr[a].append([b,c])
    arr[b].append([a,c])

start_island, end_island = map(int, input().split())

def bfs(start, end, mid):
    q = deque()
    visited = [0] * (n+1)
    visited[start] = True
    q.append(start)

    while q:
        x = q.popleft()
        # 끝점 도착시 True 반환
        if x == end:
            return True
        
        for i in arr[x]:
            # 도착하지 않은 곳이고 중량제한이 mid 이상이라면 queue에 append
            if not visited[i[0]] and i[1] >= mid:
                visited[i[0]] = True
                q.append(i[0])
    # 끝점 도착 못할 시 False 반환
    return False


start = 1
end = 1000000000
ans = 0

while start <= end:
    mid = (start + end) // 2

    # 끝점에 도착했더라도 중량제한무게가 더 클 때 끝점에 도착할 가능성이 있다.
    if bfs(start_island, end_island, mid) == True:
        start = mid + 1
        ans = max(ans, mid)
    
    # 끝점에 도착하지 못했을 시 중량제한무게를 낮춘다.
    else:
        end = mid - 1

print(ans)