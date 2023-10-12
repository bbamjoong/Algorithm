import sys
input = sys.stdin.readline

n = int(input())

if n == 1:
    print(0)
else:
    print(n*(n-1))