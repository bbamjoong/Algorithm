import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
s = int(input())

for i in range(n):
    if s == 0:
        break
    max_val = max(arr[i:i+s+1])
    max_idx = arr.index(max_val)

    # 앞쪽에서 최대 s번만큼 순서를 바꿔줘야 사전 상 큰 값이 됨.
    while max_idx != i and s:
        arr[max_idx], arr[max_idx-1] = arr[max_idx-1], arr[max_idx]
        max_idx -= 1
        s -= 1

print(*arr)