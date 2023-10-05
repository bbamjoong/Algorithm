import sys
input = sys.stdin.readline

a, b = map(int, input().split(":"))

def gcd(x, y):
    while y > 0:
        x, y = y, x%y

    return x

x = gcd(a, b)

print(str(a//x)+":"+str(b//x))