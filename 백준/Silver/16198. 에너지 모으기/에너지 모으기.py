import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
ans = 0

def dfs(x):
    global ans

    if len(arr) ==2:
        ans = max(ans, x)
        return
    
    for i in range(1, len(arr)-1):
        energy = arr[i-1] * arr[i+1]
        idx = arr.pop(i)
        dfs(x+energy)
        arr.insert(i, idx)

dfs(0)
print(ans)