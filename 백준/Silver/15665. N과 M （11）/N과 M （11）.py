import sys
input = sys.stdin.readline

n,m = map(int, input().split())
num = list(map(int, input().split()))
nums = list(set(num))
nums.sort()
res=[]

def dfs():
    if len(res) == m:
        print(' '.join(map(str, res)))
        return
    
    for i in nums:           
        # if i not in res:
        res.append(i)
        dfs()
        res.pop()

dfs()