import sys
input = sys.stdin.readline

# 이 문제에서 비상탈출구를 "0"번째 노드로 설정하여
# 모든 노드를 연결하는 것이 핵심이다.

# 부모 노드 찾기
def find(x):
    if x !=parent[x]:
        return find(parent[x])
    return x

# 트리 합치기
def union(a, b):
    a = find(a)
    b = find(b)

    # 작은 루트 노드를 기준으로 합침
    if b < a:
        parent[a] = b
    else:
        parent[b] = a


n, m = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(m)]
time = list(map(int, input().split()))

for i in range(1,n+1):
    graph.append([0, i, time[i - 1]])

# kruskal 알고리즘
graph.sort(key=lambda x: x[2]) # 간선들을 가중치 기준으로 정렬시킨다.

parent = [i for i in range(n+1)]
answer = 0

for s, e, w in graph:
    # 부모 노드가 같지 않다면 스패닝 트리
    if find(s) != find(e):
        union(s, e)
        answer += w

print(answer)