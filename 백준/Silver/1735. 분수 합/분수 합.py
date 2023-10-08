import sys
input = sys.stdin.readline

a1, b1= map(int, input().split())
a2, b2= map(int, input().split())

def gcd(x, y):
    while y > 0:
        x, y = y, x % y

    return x

b = b1 * b2
a = a1 * b2 + a2 * b1

x = gcd(a, b)

print(a//gcd(a, b), b//gcd(a,b))