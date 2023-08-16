import sys
input = sys.stdin.readline

n, m, k = map(int, input().split())

# 겨울마다 추가될 양분
food = [list(map(int, input().split())) for _ in range(n)]
# 토양의 양분 / 초기값은 5
soil = [[5] * n for _ in range(n)]

# 나무 배치
tree = [[[] for _ in range(n)] for _ in range(n)]
for _ in range(m):
    x,y,z = map(int, input().split())
    tree[x-1][y-1].append(z)

dx = [0,0,-1,1,1,1,-1,-1]
dy = [-1,1,0,0,1,-1,1,-1]

# k년동안
for _ in range(k):

    for i in range(n):
        for j in range(n):
            # 어린나무부터 양분을 먹여야 하므로 정렬
            if tree[i][j]:
                tree[i][j].sort()

                tmp = []
                death_food = 0
                # 나무의 나이가 현재 토양의 양분 이하라면
                for age in tree[i][j]:
                    if age <= soil[i][j]:
                        soil[i][j] -= age
                        age += 1
                        tmp.append(age)
                    # 나머지는 죽고 양분이 된다.
                    else:
                        death_food += age//2
                # 토양에 양분을 더해준다.
                soil[i][j] += death_food
                # 살아남은 나무 최신화
                tree[i][j] = tmp

    for i in range(n):
        for j in range(n):
            if tree[i][j]:
                # 나무의 나이가 5의 배수라면
                for age in tree[i][j]:
                    if age % 5 == 0:
                        # 주변 8칸에 나이가 1인 나무 번식
                        for k in range(8):
                            nx, ny = i + dx[k], j + dy[k]
                            if 0<= nx < n and 0<= ny < n:
                                tree[nx][ny].append(1)

    # 겨울에 양분이 추가된다.
    for i in range(n):
        for j in range(n):
            soil[i][j] += food[i][j]

ans = 0
for i in range(n):
    for j in range(n):
        ans += len(tree[i][j])

print(ans)