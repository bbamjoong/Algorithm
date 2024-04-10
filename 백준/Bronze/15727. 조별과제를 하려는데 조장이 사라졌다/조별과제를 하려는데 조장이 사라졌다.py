import sys
input = sys.stdin.readline

a = int(input())

ans = 0

if a % 5 != 0:
    ans += 1

print(ans + a // 5)