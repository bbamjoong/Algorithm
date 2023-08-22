import sys
input = sys.stdin.readline

# 다각형의 넓이를 구할때 벡터의 외적을 이용한다.
# (x1, y1) (x2, y2)의 외적 == (x1 * y2) - (x2 * y1)

# (0, 0) (0, 10) (10, 10) (10, 0) 예제의 경우 (x1, y1) (x2, y2)의 외적 ~ (x4, y4) (x1, y1)의 외적까지의 합을 구해준다.
# 이후 외적의 합의 절댓값 / 2

n = int(input())

arr = [list(map(int, input().split())) for _ in range(n)]
arr.append(arr[0])

a = 0
ans = 0
for i in range(n):
    a += arr[i][0] * arr[i+1][1] - arr[i][1] * arr[i+1][0]

ans += abs(a)/2

print(round(ans,1))