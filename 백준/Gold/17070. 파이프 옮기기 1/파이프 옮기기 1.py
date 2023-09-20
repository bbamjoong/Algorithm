import sys
input=sys.stdin.readline

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

dp = [[[0 for _ in range(n)] for _ in range(n)] for _ in range(3)]

def solution():
    # 첫번째 파이프가 놓여져 있으므로 0행1열은 1로 치환
    dp[0][0][1] = 1

    # 1행은 가로 파이프밖에 놓이지 못한다.
    for i in range(2, n):
        if graph[0][i] == 0:
            dp[0][0][i] = dp[0][0][i - 1]

    # dp를 탐색할 때, 1행 & (1열 & 2열)은 확인할 필요가 없다.
    # 파이프가 1행의 1열 2열에 가로 모양으로 놓여있으므로, 1열과 2열은 고려할 필요가 없고
    # 1행은 가로 파이프밖에 놓이지 못하므로 탐색 할 필요가 없다.
    for r in range(1, n):
        for c in range(2, n):
            
            # 대각선의 경우, 놓을칸 & 놓을칸의 왼쪽 & 놓을칸의 위쪽이 모두 0이면 탐색
            if graph[r][c] == 0 and graph[r][c - 1] == 0 and graph[r - 1][c] == 0:
                dp[1][r][c] = dp[0][r - 1][c - 1] + dp[1][r - 1][c - 1] + dp[2][r - 1][c - 1]
                
            if graph[r][c] == 0:
                dp[0][r][c] = dp[0][r][c - 1] + dp[1][r][c - 1]
                dp[2][r][c] = dp[2][r - 1][c] + dp[1][r - 1][c]
    
    print(sum(dp[i][n - 1][n - 1] for i in range(3)))

solution()
