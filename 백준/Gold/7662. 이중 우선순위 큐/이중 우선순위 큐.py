import sys
input = sys.stdin.readline
import heapq

t = int(input())

res = []
for _ in range(t):
    k = int(input())
    minq, maxq = [], []
    visited = [False] * 1000001

    for i in range(k):
        ins, num = input().rstrip().split()

        if ins == 'I':
            heapq.heappush(maxq, (-int(num), i))
            heapq.heappush(minq, (int(num), i))
            visited[i] = True

        # 처음 삭제 후에는 쓰레기 값이 존재하므로
        # 이 후에는 while문을 통해 visited되지 않은 수는 제거해준다.
        elif num == '1':
            while maxq and not visited[maxq[0][1]]:
                heapq.heappop(maxq)
            if maxq:
                visited[maxq[0][1]] = False
                heapq.heappop(maxq)
        
        else:
            while minq and not visited[minq[0][1]]:
                heapq.heappop(minq)
            if minq:
                visited[minq[0][1]] = False
                heapq.heappop(minq)

    while maxq and not visited[maxq[0][1]]:
        heapq.heappop(maxq)

    while minq and not visited[minq[0][1]]:
        heapq.heappop(minq)

    if maxq and minq:
        print(-maxq[0][0], minq[0][0])
    else:
        print("EMPTY")