import sys
input = sys.stdin.readline

n = int(input())
res = 0
ans = 0
for i in range(n):
    a, b, c = map(int, input().split())
    if a==b==c:
        res = 10000 + a*1000
    elif a==b or b==c:
        res = 1000 + b*100
    elif c==a:
        res = 1000 + a*100
    else:
        res = max(a,b,c) * 100
    ans = max(ans, res)

print(ans)