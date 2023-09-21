import sys
input = sys.stdin.readline

x = 1000000007

def ans(n, s):
    return s * mul(n, x-2) % x

def mul(b, t):
    if t == 1:
        return b % x
    
    if t%2 == 0:
        tmp = mul(b, t//2)
        return (tmp * tmp) % x
    else:
        return b*mul(b,t-1) % x

m = int(input())
sm = 0

for _ in range(m):
    n, s = map(int, input().split())
    sm += ans(n, s)
    sm %= x

print(sm)