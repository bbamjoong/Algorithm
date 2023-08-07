import sys
input = sys.stdin.readline

n, m = map(int, input().split())

print((m-1) + (n-1) * m)