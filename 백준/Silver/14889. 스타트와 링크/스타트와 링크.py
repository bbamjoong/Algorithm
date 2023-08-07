import sys
input = sys.stdin.readline

n = int(input())
arr = [list(map(int, input().split()))for _ in range(n)]
li = [i for i in range(2,n+1)]
start = [1]
link = []
c = sys.maxsize
def dfs():
    if len(start)==n//2:
        a = 0
        b = 0
        global c
        link = list(set(li) - set(start))
        for i in start:
            for j in start:
                a += arr[i-1][j-1]        
        for i in link:
            for j in link:
                b += arr[i-1][j-1]
        c = min(c, abs(a-b))
        return
    
    for i in li:
        if start[-1] < i:
            start.append(i)
            dfs()
            start.pop()

dfs()
print(c)