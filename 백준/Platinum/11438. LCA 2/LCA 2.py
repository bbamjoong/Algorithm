import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)
LENGTH = 21

n = int(input())
graph = [[] for _ in range(n+1)] # 그래프
parent = [[0] * LENGTH for _ in range(n+1)] # 부모
node_depth = [0] * (n+1) # 노드의 깊이
calculated = [False] * (n+1) # 계산 여부

# 그래프 추가
for _ in range(n-1):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

# depth 계산
def calculate_depth(x, depth):
    calculated[x] = True
    node_depth[x] = depth

    for node in graph[x]:
        if calculated[node]:
            continue
        parent[node][0] = x
        calculate_depth(node, depth + 1)

# 부모 계산
def set_parent():
    for i in range(1, LENGTH):
        for j in range(1, n+1):
            parent[j][i] = parent[parent[j][i-1]][i-1]

def lca(a, b):
    # a가 더 깊도록 설정할 것이다.
    if node_depth[a] < node_depth[b]:
        a, b = b, a

    # 깊이가 동일하도록 설정한다.
    for i in range(LENGTH - 1, -1, -1):
        if node_depth[a] - node_depth[b] >= (1 << i):
            a = parent[a][i]

    if a == b:
        return a

    for i in range(LENGTH - 1, -1, -1):
        if parent[a][i] != parent[b][i]:
            a = parent[a][i]
            b = parent[b][i]

    return parent[a][0]

m = int(input())

calculate_depth(1, 0)
set_parent()

for i in range(m):
    a, b = map(int, input().split())
    print(lca(a, b))