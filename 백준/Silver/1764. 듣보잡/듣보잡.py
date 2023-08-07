import sys
input = sys.stdin.readline

m, n = map(int, input().split())

a = set()
for i in range(m):
    a.add(input().strip())

b = set()
for i in range(n):
    b.add(input().strip())

ans = sorted(list(a&b))

print(len(ans))
for i in ans:
    print(i)