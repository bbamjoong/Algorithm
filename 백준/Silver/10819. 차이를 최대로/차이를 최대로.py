import sys
input = sys.stdin.readline

n = int(input())
nums = list(map(int, input().split()))
nums.sort()
ans = 0
res=[]

def dfs():
    global ans
    if len(res) == n:
        cnt = 0
        for i in range(n-1):
            cnt += abs(nums[res[i]] - nums[res[i+1]])
        ans = max(ans, cnt)
        return

    for i in range(n):
        if i not in res:
            res.append(i)
            dfs()
            res.pop()

dfs()
print(ans)
