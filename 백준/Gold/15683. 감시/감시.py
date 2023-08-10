import sys
input = sys.stdin.readline

# 가능한 방향의 배열
mode = [
    [],
    [[0], [1], [2], [3]],
    [[0, 2], [1, 3]],
    [[0, 1], [1, 2], [2, 3], [0, 3]],
    [[0, 1, 2], [0, 1, 3], [1, 2, 3], [0, 2, 3]],
    [[0, 1, 2, 3]],
]

dx = [-1,0,1,0]
dy = [0,-1,0,1]

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

# cctv가 있는곳의 좌표 + cctv의 종류
cctv = []
for i in range(n):
    for j in range(m):
        if graph[i][j] != 0 and graph[i][j] != 6:
            cctv.append([i,j,graph[i][j]])

# cctv가 바라보고 있는 방향을 체크
def check(x,y,dir,tmp):
    for i in dir:
        nx, ny = x, y
        while True:
            nx += dx[i]
            ny += dy[i]
            # 범위 벗어나면 break
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                break
            # 벽을 만나면 break
            if tmp[nx][ny] == 6:
                break
            # 빈 공간이거나 이미 check한 곳 일 때
            elif tmp[nx][ny] == 0:
                # check표시
                tmp[nx][ny] = -1


ans = sys.maxsize
def dfs(depth, arr):
    global ans

    # 만약 모든 cctv의 경우를 다 체크했을 때
    if depth == len(cctv):
        cnt = 0
        for i in range(n):
            for j in range(m):
                if arr[i][j] == 0:
                    cnt += 1
        ans = min(ans, cnt)
        # 반환하여 다른 방향을 볼 때를 체크한다
        return
    
    # 백트래킹을 실시할 것이므로 깊은복사를 한 배열을 만들어준다
    tmp = [li1[:] for li1 in arr]
    x, y, num = cctv[depth]

    for i in mode[num]:
        check(x,y,i,tmp)
        dfs(depth+1, tmp)
        # check가 끝나고 다른 배열을 확인할 때 깊은복사를 한 배열 필요
        tmp = [li1[:] for li1 in arr]

dfs(0, graph)
print(ans)