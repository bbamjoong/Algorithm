import sys
input = sys.stdin.readline

a, b = map(int, input().split())

d = {}

for i in range(a):
    word = input().rstrip()
    d[word] = True

cnt = 0

for i in range(b):
    word = input().rstrip()
    if word in d:
        cnt += 1

print(cnt)