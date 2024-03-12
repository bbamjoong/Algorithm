import sys
input = sys.stdin.readline

a, b = map(int, input().split())

x1, y1 = divmod(a, 4)
x2, y2 = divmod(b, 4)

if y1 == 0:
    x1 -= 1
    y1 += 4

if y2 == 0:
    x2 -= 1
    y2 += 4

x = abs(x1 - x2)
y = abs(y1 - y2)

print(x + y)