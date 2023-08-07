import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
LIS = [-1000000001]
dp = []

def binary(target):
    left, right = 0, len(LIS) - 1
    while left <= right:
        mid = (left + right) // 2
        if LIS[mid] < target:
            left = mid + 1
        elif LIS[mid] > target:
            right = mid - 1
        else:
            return mid
    return left

for target in arr:
    if LIS[-1] < target:
        LIS.append(target)
        dp.append((len(LIS)-1, target))
    else:
        idx = binary(target)
        LIS[idx] = target
        dp.append((idx, target))


cnt = len(LIS) - 1
res = []
for i in range(len(dp)-1, -1, -1):
    if dp[i][0] == cnt:
        res.append(dp[i][1])
        cnt-=1

print(len(LIS)-1)
print(*res[::-1])
