import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)

n, m = map(int, input().split())
li = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, input().split())
    li[a].append(b)
    li[b].append(a)

visit_cnt = 0
visited = [False] * (n + 1)


def dfs(node, cnt):
    global visited, visit_cnt, node_cnt, edge_cnt
    node_cnt += 1  # 그래프 노드 개수 추가
    edge_cnt += len(li[node])  # 그래프 엣지 개수 추가
    for next in li[node]:
        if not visited[next]:
            visited[next] = True
            visit_cnt += 1

            dfs(next, cnt + 1)


def check_cycle():
    global node_cnt, edge_cnt, cut_cnt, tree_cnt

    edge_cnt //= 2  # 양방향 간선으로 저장했으니까 // 2

    if node_cnt - edge_cnt != 1:  # 사이클이 있다면
        cut_cnt += edge_cnt - node_cnt + 1


cut_cnt = 0  # 사이클 끊은 횟수
tree_cnt = 0  # 트리의 개수
for i in range(1, n + 1):
    if not visited[i]:
        visited[i] = True
        visit_cnt += 1  # 방문한 노드 개수++
        tree_cnt += 1  # 트리의 개수++

        node_cnt = 0
        edge_cnt = 0

        dfs(i, 1)
        check_cycle();

        if visit_cnt == n:
            break

print(cut_cnt + tree_cnt - 1)
