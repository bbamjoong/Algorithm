import sys
input = sys.stdin.readline
a, b = map(int, input().split())
a1, b1 = a, b

k = 0
while b > 0:
    a, b = b, a % b
    k = a

ma = a1 * b1 // k

print(k)
print(ma)