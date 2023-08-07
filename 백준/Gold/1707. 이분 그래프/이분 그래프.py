import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)


def dfs(start, num):
    visited[start] = num

    # start와 연결된 노드 확인
    for i in graph[start]:
        # 만약 num이 부여되지 않았다면
        if visited[i] == 0:
            # -num부여 후 i와 연결된 노드 확인
            res = dfs(i, -num)
            # 만약 부모노드와 자식노드가 같은 num을 부여받았다면 False 반환
            if not res:
                return False
        else:
            # 만약 부모노드와 자식노드가 같은 num을 부여받았다면 False 반환
            if visited[i] == num:
                return False
    return True

k = int(input())

for _ in range(k):
    v, e = map(int, input().split())
    graph = [[] for _ in range(v+1)]
    visited = [0] * (v+1)

    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    for i in range(1,v+1):
        if visited[i] == 0:
            res = dfs(i, 1)
            if not res:
                break
    if res:
        print('YES')
    else:
        print('NO')