import sys
input = sys.stdin.readline

n = int(input())
mx = 0

ti = [0]
pi = [0]
for i in range(n):
    a, b = map(int, input().split())
    ti.append(a)
    pi.append(b)

res = []

def dfs(date, cost, nal):
    if date > n+1:
        res.append(cost - pi[nal])
        return
    
    if date == n+1:
        res.append(cost)
        return

    for i in range(date,n+1):
        dfs(i + ti[i], cost + pi[i], i)
        

dfs(1,0,0)
print(max(res))