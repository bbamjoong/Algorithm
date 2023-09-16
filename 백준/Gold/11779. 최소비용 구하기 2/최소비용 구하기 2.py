import sys
input = sys.stdin.readline
import heapq

n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]

# 그래프에 노드 - 간선 정보 추가
for i in range(m):
    a, b, c = map(int, input().split())
    graph[a].append([b,c])

start, end = map(int, input().split())

# 거리를 나타내는 배열
distance = [1e9] * (n+1)

# 어떤 경로에 최소비용으로 도착했을 때, 도착 이전의 노드 정보
parent = [0 for _ in range(n+1)]

def dijkstra(start):
    distance[0] = 0
    distance[start] = 0

    q = []
    heapq.heappush(q, [0, start])

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue

        for i in graph[now]:
            cost = dist + i[1]
            
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q,[cost, i[0]])
                parent[i[0]] = now

# 다익스트라 함수 호출
dijkstra(start)


# 도착 노드는 end
node = end

# 경로 배열
ans = [node]

while True:
    # 이전 노드 = parent[node]
    prev_node = parent[node]

    # 만약 이전 노드의 parent가 0라면 start노드이므로 break
    if prev_node == 0:
        break
    # 만약 이전 노드의 parent가 0이 아니라면 "경로배열" ans 최신화 + node 최신화
    else:
        ans.append(prev_node)
        node = prev_node

print(distance[end])
print(len(ans))
print(*ans[::-1])