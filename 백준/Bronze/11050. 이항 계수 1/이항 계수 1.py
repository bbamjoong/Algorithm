import sys
input = sys.stdin.readline

n, k = map(int, input().split())

if k > n//2:
    k = n - k

a = 1
for i in range(k):
    a *= (n - i)

b = 1
for i in range(1, k+1):
    b *= i

print(a//b)