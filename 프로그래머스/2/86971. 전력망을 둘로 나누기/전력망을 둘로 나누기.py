from collections import deque
def check(start, end, visited, graph):
    visited[start] = 1
    visited[end] = 1
    q = deque()
    q.append(end)
    cnt = 1
    while q:
        cur = q.popleft()
        for next in graph[cur]:
            if not visited[next]:
                visited[next] = 1
                cnt += 1
                q.append(next)
    return cnt

def solution(n, wires):
    answer = -1
    graph = [[] for _ in range(n + 1)]
    
    for s, e in wires:
        graph[s].append(e)
        graph[e].append(s)
        
    answer = float("inf")
    
    for s, e in wires:
        visited = [0] * (n + 1)
        # s -> e로 갔을 때 방문할 수 있는 노드 개수
        cnt_a = check(s, e, visited, graph)
        # 나머지 노드 개수
        cnt_b = n - cnt_a
        # 그룹 차이
        cnt = abs(cnt_a - cnt_b)
        # 답 갱신
        answer = min(answer, cnt)
        
    return answer