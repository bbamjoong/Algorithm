import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
li_sum = [i for i in range(1,sum(arr)+1)]


def dfs(idx, sm):

    if idx == n:
        li_sum[sm-1] = sum(arr)
        return
    
    dfs(idx+1,sm+arr[idx])
    dfs(idx+1,sm+0)

dfs(0,0)
if li_sum[-1] == min(li_sum):
    print(li_sum[-1]+1)
else:
    print(min(li_sum))