import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
LIS = [-1000000001]
dp = []

# 이진탐색을 통해 수열을 찾는다.
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

# LIS에 수열을 찾고 / dp에는 LIS에 값이 변경되는 경우에 대해 (idx, value)를 추가해준다.
for target in arr:
    if LIS[-1] < target:
        LIS.append(target)
        dp.append((len(LIS)-1, target))
    else:
        idx = binary(target)
        LIS[idx] = target
        dp.append((idx, target))

# 예를들어 [10 30 40 50 20 40] 이라는 수열이 주어졌다고 하자
# LIS : [-1000000001, 10, 20, 40, 50]
# DP : [(1, 10), (2, 30), (3, 40), (4, 50), (2, 20), (3, 40)]일 것이다.
# LIS에 있는 배열 값이 정답이 될 수 없다. (정답은 10 30 40 50)
# 이 때 역추적을 통해 결과값을 찾는다.
cnt = len(LIS) - 1
res = []
for i in range(len(dp)-1, -1, -1):
    if dp[i][0] == cnt:
        res.append(dp[i][1])
        cnt-=1

print(len(LIS)-1)
print(*res[::-1])
