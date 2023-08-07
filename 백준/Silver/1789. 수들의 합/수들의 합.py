import sys
input = sys.stdin.readline

n = int(input())
sm = 0
cnt = 0

while sm <= n:
    cnt += 1
    sm += cnt

print(cnt - 1)