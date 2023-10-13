import sys
input = sys.stdin.readline

n = int(input())

def factorial(n):
    if n > 1:
        return n * factorial(n-1)
    else:
        return 1

for i in range(n):
    r, n = map(int, input().split())

    ans = factorial(n) // (factorial(n-r) * factorial(r))

    print(ans)