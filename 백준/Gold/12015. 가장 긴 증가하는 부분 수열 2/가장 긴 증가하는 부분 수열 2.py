import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
dp = [0]

for target in arr:
    if dp[-1] < target:
        dp.append(target)

    else:
        left = 0
        right = len(dp) - 1
        

        while left <= right:
            mid = (left + right) // 2
            if dp[mid] < target:
                left = mid + 1            
            else:
                right = mid - 1

        dp[left] = target

print(len(dp) - 1)