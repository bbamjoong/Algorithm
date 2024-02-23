import sys
import heapq
input = sys.stdin.readline

# 오름차순으로 정렬을 해봅시다.
# A - B가 가깝다고 하면  A - C, A - D와 같이 더 먼 간선은 쓸데없는 정보입니다.
# 프림 알고리즘을 이용했을 때 ElogV = (100000 * 99999) / 2 * log100000 = 대략 830억
# X, Y, Z축 기준으로 오름차순을 해서 바로 다음 행성과의 거리만 간선으로 저장
# -> 간선 E = (V-1) * 3이다. 이 때 ElogV = 99999*3 / 2 * log100000 = 대략 250만

n = int(input())

# 좌표 값과, 인덱스를 함께 추가해준다.
xList = []
yList = []
zList = []
for i in range(n):
    x, y, z = map(int, input().split())
    xList.append([x, i])
    yList.append([y, i])
    zList.append([z, i])

xList.sort()
yList.sort()
zList.sort()

graph = [[] for _ in range(n + 1)]
visited = [False for _ in range(n + 1)]

def appendEdges(arr):
    global graph
    for i in range(1, n):
        weight1, start = arr[i - 1];
        weight2, end = arr[i]
        graph[start].append([abs(weight1 - weight2), end])
        graph[end].append([abs(weight1 - weight2), start])


appendEdges(xList);
appendEdges(yList);
appendEdges(zList);

def prim(start):
    global cnt, ans;
    pq = []
    heapq.heappush(pq, [0, start]) # 시작은 weight가 0임
    
    while cnt < n:
        weight, now = heapq.heappop(pq);
            
        if visited[now]:
            continue;
            
        visited[now] = True;
        cnt += 1;
        ans += weight;
        
        for getNode in graph[now]:
            weight, next = getNode;
            if not visited[next]:
                heapq.heappush(pq, getNode)

ans = 0;
cnt = 0;
prim(1);
print(ans);