import sys
input = sys.stdin.readline

n, k = map(int, input().split())

val = k * (k+1) //2

if n < val:
    print(-1)
elif (n - val) % k == 0:
    print(k - 1)
else:
    print(k)