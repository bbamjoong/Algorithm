import sys
input = sys.stdin.readline

n, m, b = map(int, input().split())

arr = {}
for i in range(n):
    li = list(map(int, input().split()))
    for j in li:
        if j not in arr.keys():
            arr[j] = 1
        else:
            arr[j] += 1

ans_height = 0
ans = sys.maxsize

max_height = max(arr.keys())
min_height = min(arr.keys())

for height in range(max_height, min_height - 1, -1):
    surplus = 0
    need = 0

    for i in arr.keys():
        # 목표높이인 height보다 현재칸의 블록 높이가 작다면
        # 몇 칸을 채워야하는지 갱신
        if i < height:
            need += (height - i) * arr[i]
        # 목표높이인 height와 현재칸의 높이가 같거나 크다면
        # 몇 칸을 캘 수 있는지 갱신           
        else:
            surplus += (i - height) * arr[i]
    
    block = surplus + b

    # 가지고있는 블록보다 필요한 블록이 많으면 불가능함.
    if block < need:
        continue

    time = 2 * surplus + need

    if time < ans:
        ans = time
        ans_height = height

print(ans, ans_height)