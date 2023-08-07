import sys
input = sys.stdin.readline
import heapq

n = int(input())
arr = []

# heapq
q = []
# 끝나는 지점을 넣을 set
check = set()

for i in range(n):
    a,b,c = map(int, input().split())

    # 시작점1, 끝점-1
    # 시작점일 때 : 시작점(현재위치), 높이, 끝점, 1
    arr.append((a,b,c,1))
    # 끝점일 때 : 끝점(현재위치), 높이, 의미없는 값, -1
    arr.append((c,b,"0",-1))

# 1. 시점 오름차순 2. 시점이 같다면 시작점? 3. 높이가 높은지
arr.sort(key = lambda x:(x[0],-x[3],-x[1]))

# 현재 높이
now = 0
# 답안 리스트
ans = []

for i in range(len(arr)):
    # point : 날짜, idx : arr에서의 index, dir -> 1 : 시작점, -1 : 끝점
    point, height, end, dir = arr[i]

    # 시작점이라면
    if dir == 1:

        # 만약 현재 높이보다 원소의 높이가 크다면
        if now < height:
            # 현재의 높이를 재지정
            now = height
            # 답안에 추가
            ans.append((point, now))

        # push를 할 때 높이가 높아야 하므로 내림차순 / 끝나는 점까지 push
        heapq.heappush(q,(-height,end))

    # 끝점
    else:
        # set에 끝나는 점을 더해준다
        check.add(point)

        # check에 지금까지 끝나는 점이 지정이 되어있을 것이다.
        # q[0][1]이 끝나는 점인데, 만약 check에 q[0][1]이 있다면 pop해준다.
        while q:
            if q[0][1] not in check:
                break
            
            heapq.heappop(q)

        # 만약 pop을 했는데 q가 완전히 비어버렸다면 그 부분은 건물이 없는 높이가 0인 지점이다.
        if not q:
            # 시작점은 다른데 끝점이 같은 건물이 있다고 하자.
            # 첫 건물이 끝날 때 -> now가 0으로 초기화가 된다.
            # 그 다음 건물 차례 때 q가 비어있다. 이때 now는 0일 것이다.
            # but 이 결과는 append되면 안된다. 따라서 if now:를 추가해준다.
            if now:
                # 현재의 높이를 0으로 초기화 해준다.
                now = 0
                ans.append((point, now))

        # 만약 pop을 했는데 q에 원소가 남아있다면 가장 앞의 높이가 가장 높은 것이 현재의 높이가 될 것이다.
        else:
            if -q[0][0] != now:
                now = -q[0][0]
                ans.append((point, now))

for i in ans:
    print(i[0], i[1], end=' ')
