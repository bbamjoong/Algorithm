import sys
input=sys.stdin.readline
from collections import deque
sys.setrecursionlimit(10**6)

# 가장 빠른 시간
fastest = 1e9
# 답안의 개수
cnt = 0

def bfs():
    global fastest, cnt

    q = deque()             
    q.append([n, 0])            
    
    while  q:
        x, time = q.popleft()     

        # n -> k에 도착했고, 가장 빠른 시간 이하의 시간으로 도착했다면
        if x == k and time <= fastest:
            # 가장 빠른 시간을 초기화 후
            fastest = min(fastest, time)
            # 답안의 개수 + 1
            cnt += 1

        # 가장 빠른 시간보다 time이 크다면, 고려할 필요가 없으므로 break
        if time > fastest:
            break

        for nx in (x - 1, x + 1, x * 2):    
            # 이동할 좌표가 범위 내에 존재
            if 0 <= nx <= MAX:
                # 도착한 적이 없는 좌표일 경우
                if not dist[nx]:
                    # 거리 최신화
                    dist[nx] = dist[x] + 1
                    # queue에 원소 추가
                    q.append([nx, time + 1])    
                
                # 도착한 적이 있는 좌표일 경우
                elif dist[nx]:
                    # 좌표 도달 최소시간과, 지금 도착할 때 시간이 같다면 => 여러개의 답안이 생길 수 있다.
                    if time + 1 == dist[nx]:
                        # queue에 원소 추가
                        q.append([nx, time + 1])


MAX = 10 ** 5              
dist = [0] * (MAX + 1)      
n, k = map(int, input().split())

bfs()

print(fastest)
print(cnt)