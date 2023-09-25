import sys
input = sys.stdin.readline

R, C = map(int, input().split())
n = int(input())

graph = [[0 for _ in range(C)] for _ in range(R)]

for i in range(n):
    a,b,c = map(int, input().split())
    graph[a-1][b-1] = c

def find(x):
    global R, C

    dp = [[0 for _ in range(C)] for _ in range(R)]
    dp[0][0] = x

    for i in range(R):
        for j in range(C):
            if i == 0 and j == 0:
                continue

            # dp 값 최신화
            if i == 0:
                dp[i][j] = dp[i][j-1] - 1

            elif j == 0:
                dp[i][j] = dp[i-1][j] - 1

            else:
                dp[i][j] = max(dp[i][j-1], dp[i-1][j]) - 1

            # 만약 연료가 있는데...?
            # 거리가 안된다?? -> 연료를 더해주지 말자. 어차피 도착 못하는 곳이니까 계속 값을 뺀다.
            if graph[i][j]:
                if dp[i][j] >= 0:
                    dp[i][j] += graph[i][j]

    if dp[R-1][C-1] < 0:
        return False
    
    return True


start = 0
end = R+C


while start <= end:
    mid = (start + end) // 2

    if find(mid):
        end = mid - 1

    else:
        start = mid + 1

print(start)
