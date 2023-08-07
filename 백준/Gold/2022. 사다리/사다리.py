import sys
input = sys.stdin.readline

def solve(x, y, w):
    h1 = (x**2-w**2)**0.5
    h2 = (y**2-w**2)**0.5    
    c = h1*h2 / (h1+h2)
    return c

x,y,c = map(float, input().split())

start, end = 0, min(x,y)
ans = 0

while end - start > 0.001:
    mid = (start + end) / 2
    
    if solve(x,y,mid) >= c:
        ans = mid
        start = mid
    else:
        end = mid

print(ans)