import sys
input = sys.stdin.readline

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


v, e = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(e)]
# kruskal 알고리즘
graph.sort(key=lambda x: x[2]) # 간선들을 가중치 기준으로 정렬시킨다.

parent = [i for i in range(v+1)]
answer = 0

for s, e, w in graph:
    # 부모 노드가 같지 않다면 스패닝 트리
    if find(s) != find(e):
        union(s, e)
        answer += w

print(answer)