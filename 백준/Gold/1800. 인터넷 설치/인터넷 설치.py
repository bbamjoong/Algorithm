import sys
input = sys.stdin.readline
import heapq


def dijkstra(budget):
    counts = [mx_value for _ in range(n + 1)]
    counts[1] = 0
    pq = []
    heapq.heappush(pq, [0, 1])

    while pq:
        cnt, now = heapq.heappop(pq)

        if counts[now] < cnt: continue

        for next, cost in nodes[now]:
            if budget < cost:
                if counts[next] > cnt + 1:
                    counts[next] = cnt + 1
                    heapq.heappush(pq, [cnt + 1, next])
                    # cost_budget보다 값이 커지면 무료 기회를 사용해야 한다.
            else:
                if counts[next] > cnt:
                    counts[next] = cnt
                    heapq.heappush(pq, [cnt, next])
                    # cost_budget으로 모두 계산 가능. 무료 기회를 사용할 필요가 없다.
    return counts[n]



mx_value = 1_000_001
n, p, k = map(int, input().split())

nodes = [[] for _ in range(n + 1)]
mx_cost = 0

for _ in range(p):
    start, end, cost = map(int, input().split())
    nodes[start].append([end, cost])
    nodes[end].append([start, cost])

    mx_cost = max(mx_cost, cost)

left, right = 0, mx_cost
ans = mx_value

while left <= right:
    mid = (left + right) // 2

    if dijkstra(mid) <= k:
        right = mid - 1
        ans = mid
    else:
        left = mid + 1

if ans == mx_value:
    print(-1)
else:
    print(ans)
