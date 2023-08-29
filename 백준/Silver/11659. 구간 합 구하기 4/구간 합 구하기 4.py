import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))

sm = [0]

tmp = 0
for i in arr:
    tmp += i
    sm.append(tmp)

for i in range(m):
    a, b = map(int, input().split())
    print(sm[b] - sm[a-1])