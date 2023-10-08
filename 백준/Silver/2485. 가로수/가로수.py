import sys
input = sys.stdin.readline

n = int(input())
arr = [int(input()) for _ in range(n)]

diff = [arr[i+1] - arr[i] for i in range(n-1)]

def gcd(x, y):
    while y > 0:
        x, y = y, x % y

    return x

a = diff[0]
for i in range(1, n-1):
    a = gcd(a, diff[i])

    if a == 1:
        break

print((arr[-1] - arr[0]) // a + 1 - n)