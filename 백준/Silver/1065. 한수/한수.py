import sys
input = sys.stdin.readline

n = int(input())

ans = 0
for i in range(1, n+1):
    nums = list(map(int, str(i)))
    
    if i < 100:
        ans += 1
        
    elif nums[0] - nums[1] == nums[1] - nums[2]:
        ans += 1
print(ans)