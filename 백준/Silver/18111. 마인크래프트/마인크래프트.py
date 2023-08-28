import sys
input = sys.stdin.readline

n, m, b = map(int, input().split())

arr = []
min_arr = sys.maxsize
max_arr = 0

for i in range(n):
    li = list(map(int, input().split()))
    min_arr = min(min_arr, min(li))
    max_arr = max(max_arr, max(li))
    arr.append(li)

ans_height = 0
ans = sys.maxsize

for height in range(min_arr, max_arr+1):
    surplus = 0
    need = 0

    for i in range(n):
        for j in range(m):
            # 목표높이인 height보다 현재칸의 블록 높이가 작다면
            # 몇 칸을 채워야하는지 갱신
            if arr[i][j] < height:
                need += (height - arr[i][j])
            # 목표높이인 height와 현재칸의 높이가 같거나 크다면
            # 몇 칸을 캘 수 있는지 갱신           
            else:
                surplus += (arr[i][j] - height)
    
    block = surplus + b

    # 가지고있는 블록보다 필요한 블록이 많으면 불가능함.
    if block < need:
        continue

    time = 2 * surplus + need

    if time <= ans:
        ans = time
        ans_height = height

print(ans, ans_height)