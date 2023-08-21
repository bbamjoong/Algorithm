import sys
input = sys.stdin.readline
from collections import deque

t = int(input())
for _ in range(t):
    n, k = map(int, input().split())

    # 건물을 지을 때 얼마만큼의 시간이 걸리는지 리스트에 저장
    time1 = list(map(int, input().split()))
    time = [0] + time1
    
    # 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
    graph = [[] for _ in range(n+1)]
    # 모든 노드에 대한 진입차수는 0으로 초기화
    indegree = [0] * (n+1) 

    # 방향그래프의 모든 간선 정보를 입력받기
    for i in range(k):
        a, b = map(int, input().split())
        graph[a].append(b) #정점 A에서 B로 이동 가능
        indegree[b] += 1 #진입차수를 1 증가

    w = int(input())

    # 처음 시작 시 진입차수가 0인 노드를 큐에 삽입
    q = deque()
    for i in range (1,n+1):
        if indegree[i] == 0:
            q.append((i,time[i]))

    # 건물이 지어질 때 이전까지 지어진 건물들 시간의 합을 저장하는 리스트
    ans = [[0] for _ in range(n+1)]

    while q:
        now, time_now = q.popleft()

        # 조건에 맞는 건물이 지어졌다면 결과 출력 후 종료
        if now == w:
            print(time_now)
            break
        # now 건물이 지어졌다면, 그 다음 건물들을 확인한다
        for i in graph[now]:
            # 먼저 now와 연결되어있는 정점들이 건설될 경우의 시간을 ans에 저장한다.
            ans[i].append(time_now + time[i])
            # 진입차수를 1 감소
            indegree[i] -= 1

            # 만약 진입차수가 0이되어 건설이 가능하다면
            if indegree[i] == 0:
                # 현재까지 지은 건물들 시간의 합중 가장 큰 값을 이용한다
                q.append((i, max(ans[i])))