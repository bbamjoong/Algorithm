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
    global visited, visit_cnt, res

    for next in li[node]:
        if not visited[next]:
            visited[next] = True
            visit_cnt += 1
            res.append(next);
            dfs(next, cnt + 1)


def check_cycle():
    global res, li, tree_cnt

    node_cnt = len(res)  # 노드의 개수
    edge_cnt = 0
    for i in res:
        edge_cnt += len(li[i])
    edge_cnt //= 2

    if node_cnt - edge_cnt != 1:  # 사이클이 있다면
        tree_cnt += edge_cnt - node_cnt + 1


tree_cnt = 0  # 트리의 개수
for i in range(1, n + 1):
    if not visited[i]:
        visited[i] = True
        visit_cnt += 1  # 방문한 노드 개수++
        tree_cnt += 1  # 트리의 개수++

        res = [i]
        dfs(i, 1)

        check_cycle();

        if visit_cnt == n:
            break

print(tree_cnt - 1)
