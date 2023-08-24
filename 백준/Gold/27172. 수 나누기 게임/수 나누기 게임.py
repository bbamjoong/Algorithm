import sys
input = sys.stdin.readline

# 모든 경우의 수를 다 따질 경우 100,000C2 = 4,999,950,000 시간초과

n = int(input())
li1 = list(map(int, input().split()))
MAX = max(li1)

# arr : 수가 있으면 1, 없으면 0
arr = [0] * 1000001
for j in li1:
    arr[j] += 1

# 정답 리스트
ans = [0] * (MAX + 1)

# 숫자를 오름차순으로 정렬 후, 소수 판정하듯이 판별
# 시간복잡도 : O(n log(logn))  n = 100,000
for i in li1:
    for j in range(i*2,MAX + 1,i):
        if arr[j] != 1:
            continue
        else:
            ans[i] += 1
            ans[j] -= 1

for i in li1:
    print(ans[i], end= ' ')