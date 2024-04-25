import sys
input = sys.stdin.readline


n = int(input())
bricks = [[0, 0, 0, 0]]
dp = [0] * (n + 1) # 해당 idx의 탑을 가장 밑에 깔았을 때의 높이

for i in range(1, n + 1):
    a, b, c = map(int, input().split())
    bricks.append([i, a, b, c])

# 넓이를 기준으로 정렬
bricks.sort(key=lambda x: x[1])

for i in range(1, n + 1):
    # 넓이를 기준으로 정렬했으니, 이제 나보다 앞에 있는 것들은 나보다 넓이가 작다.
    # 따라서 무게만 비교해보자.
    for j in range(0, i):
        # j는 나보다 앞쪽의 무게. 나보다 가벼운 녀석이 있다면 높이를 높여주자.
        if bricks[i][3] > bricks[j][3]:
            # dp는 해당 벽돌이 가장 아래에 있을 때의 최대 높이이다.
            # 따라서 dp[j]에 나(i)의 높이를 더한 것과 비교하자.
            dp[i] = max(dp[i], bricks[i][2] + dp[j])

'''
예제의 경우 이 코드에서
bricks = [
[0, 0, 0, 0]
[5, 1, 5, 2]
[2, 4, 4, 6]
[3, 9, 2, 3]
[4, 16, 2, 5]
[1, 25, 3, 4]
]

dp = [0, 5, 9, 7, 9, 10]
'''

# 가장 아래에 쌓은 벽돌의 높이를 빼주면서 dp에서 일치하는 값을 찾도록 하자.
mx = max(dp)
idx = n
ans = []

# 어차피 넓이를 기준으로 정렬했기 때문에 dp배열의 가장 뒤쪽부터 탐색한다.
# 그것이 답일 가능성이 가장 크니까.
while idx != 0:
    if mx == dp[idx]: # 일치하면, res에 원소 추가 + mx높이 빼주기
        ans.append(bricks[idx][0])
        mx = mx - bricks[idx][2]

    idx -= 1

size = len(ans)

print(size)
for i in range(size - 1, -1, -1):
    print(ans[i])