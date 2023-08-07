import sys
input = sys.stdin.readline

n,m = map(int, input().split())
nums = list(map(int, input().split()))
visited = [False] * (n+1)
nums.sort()
res=[]
def dfs(idx):
    if len(res) == m:
        print(' '.join(map(str, res)))
        return
    prev = 0
    for i in range(idx,n+1):           
        if nums[i-1] != prev and visited[i] == False:
            visited[i] = True
            res.append(nums[i-1])
            prev = nums[i-1]
            dfs(i+1)
            visited[i] = False
            res.pop()

dfs(1)