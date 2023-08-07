import sys
input = sys.stdin.readline
n = int(input())

def gcd(a, b):
    while b > 0:
        a, b = b, a % b
    return a

for i in range(n):
    a, b = map(int, input().split())
    print(a * b // gcd(a,b))
