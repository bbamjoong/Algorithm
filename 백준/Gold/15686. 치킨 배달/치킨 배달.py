import sys
input = sys.stdin.readline

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

# 집, 치킨집 좌표 탐색
house = []
chicken = []
for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            house.append([i,j])
        elif graph[i][j] == 2:
            chicken.append([i,j])

def find_dist(a,b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])

# 치킨거리의 모든 경우를 구한다. / dfs안에서 구할 시 중복으로 인해 시간 초과
dist = []
def cal_dist():
    for i in range(len(house)):
        tmp = []
        for j in range(len(chicken)):
            tmp.append(find_dist(house[i],chicken[j]))
        dist.append(tmp)


ans = sys.maxsize
def dfs(depth,select,k):
    global m, ans

    if depth == m:
        sum_cnt = 0
        for i in range(len(house)):
            cnt = sys.maxsize
            # 한 집에 대한 치킨거리를 탐색한다.
            for j in select:
                cnt = min(cnt, dist[i][j])
            # 각각의 집에 대한 치킨거리 탐색 후 더해준다.
            sum_cnt += cnt
            # 만약 답안보다 치킨거리의 합이 크거나 같다면 치킨거리 탐색을 중단한다.
            if sum_cnt >= ans:
                return
        ans = min(ans, sum_cnt)
        return
    
    # 치킨집의 조합을 찾는데, 앞에서부터 고른 치킨집의 뒤쪽 치킨집을 뽑으면 되기 때문에
    # 변수k를 통해 시간복잡도를 O(n*n)에서 O(n*logn)으로 줄인다.
    for i in range(k,len(chicken)):
        select.append(i)
        dfs(depth+1,select,i+1)
        select.pop()

cal_dist()
dfs(0,[],0)
print(ans)