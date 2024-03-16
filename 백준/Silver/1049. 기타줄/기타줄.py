import sys
input = sys.stdin.readline

n, m = map(int, input().split())

s = []
o = []

for _ in range(m):
    a, b = input().split()
    s.append(int(a))
    o.append(int(b))

s = min(s)
o = min(o)

if s < o * 6:
    if s < (n % 6) * o:
        print((n // 6) * s + s)
    else:
        print((n // 6) * s + (n % 6) * o)

else:
    print(n * o)