import sys
input=sys.stdin.readline
import heapq

n, k = map(int, input().split())

# 보석의 무게가 최대 1,000,000이다.
MAX = 10000001

# 보석의 무게에 가격이 배열되는 2차원 리스트를 생성한다.
gems = [[] for _ in range(MAX)]
for _ in range(n):
    m, v = map(int, input().split())
    gems[m].append(v)

# 가방이 무게가 작은 순으로 정렬 -> 가방의 무게 >= 보석의무게 가 만족하는 보석 중에서 가장 가치가 높은 보석 선택
bags = [int(input()) for _ in range(k)]
bags.sort()

tmp = []
size = 0
ans = 0

# 무게가 작은 가방부터 선택
for bag in bags:
    # 보석의 크기를 0부터 탐색해 나간다.
    while size <= bag:
        # 만약 보석크기에 맞는 보석이 있다면
        for value in gems[size]:
            # tmp에 가치를 push해준다. -> heapq는 minheap이기 때문에 -value를 삽입
            heapq.heappush(tmp, -value)
        # 이 후 보석의 크기를 1씩 늘려 탐색한다.
        size += 1
    
    # 가방의 크기 조건에 맞는 보석을 탐색했을 때, 보석이 있다면? 가장 가치가 높은 보석을 선택
    # 만약 없다면 들어갈 보석이 없는 것이기 때문에 아무 조치를 취하지 않는다.
    if tmp:
        ans -= heapq.heappop(tmp)

print(ans)