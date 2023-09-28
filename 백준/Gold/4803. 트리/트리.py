import sys
input = sys.stdin.readline
from collections import deque

# bfs를 이용해 트리에 사이클이 있는지 검사한다.
def cycle(x):
    q = deque()
    # queue에 시작 노드를 넣는다.
    q.append(x)

    ret = False

    while q:
        node = q.popleft()

        # 다른 노드 방문 시 사이클이 발생 안 할수도 있기때문에 True를 반환하지 말고 반환할 변수를 True로 바꾼다.
        if visited[node]:
            ret = True

        # 방문 시 visited 최신화
        visited[node] = 1

        for i in graph[node]:
            if visited[i] == 0:
                q.append(i)

    return ret

# 몇번 째 트리인지 나타낸다.
case = 0

while True:
    v, e = map(int, input().split())

    # 간선, 노드의 개수가 모두 0이면 break
    if v == 0 and e == 0:
        break

    graph = [[] for _ in range(v+1)]
    visited = [0] * (v+1)

    cnt = 0

    # 양방향 그래프로 잡아준다. ex) 1->2, 2->1 모두 가능함
    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    # 모든 노드에 대해서 사이클이 존재하는지 판단한다.
    for i in range(1, v+1):
        # 방문하지 않은 노드에 대해서 사이클이 존재하는지 판단..
        if visited[i] == 0:
            # 사이클이 존재하지 않으면 cnt += 1
            if not cycle(i):
                cnt += 1

    # 한 트리에 대해 검사가 끝났으면 case += 1
    case += 1

    if cnt == 0:
        print(f'Case {case}: No trees.')
    elif cnt == 1:
        print(f'Case {case}: There is one tree.')
    else:
        # n번째 케이스에 대해 cnt 개수의 트리가 존재한다.
        print(f'Case {case}: A forest of {cnt} trees.')