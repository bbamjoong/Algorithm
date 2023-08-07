import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = [list(map(int, input().strip())) for _ in range(n)]
ans = 0

def bitmask():
    global ans
    # 모든칸에 0 or 1의 경우의 수 가능. 2^(n*m)경우의 수를 따져본다
    for i in range(1 << n * m):
        total = 0
        # 가로의 합을 계산
        for row in range(n):
            rowsum = 0
            for col in range(m):
                # 이차원 배열을 일렬로 표기할때의 Index 의미
                idx = row * m + col
                # 가로일 때
                if i & (1 << idx) != 0:
                    rowsum = rowsum * 10 + arr[row][col]
                # 세로일 때 rowsum을 total에 더하고 rowsum 초기화
                else:
                    total += rowsum
                    rowsum = 0
            total += rowsum

        # 세로의 합을 계산
        for col in range(m):
            colsum = 0
            for row in range(n):
                # 이차원배열을 일렬로 늘림.
                idx = row * m + col
                # 세로일때
                if i & (1 << idx) == 0:
                    colsum = colsum * 10 + arr[row][col]
                # 가로일 때 colsum을 total에 더하고 colsum 초기화
                else:
                    total += colsum
                    colsum = 0
            total += colsum
        ans = max(ans, total)

bitmask()
print(ans)