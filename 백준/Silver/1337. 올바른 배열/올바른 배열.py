import sys
input = sys.stdin.readline

n = int(input())
li = [int(input()) for _ in range(n)]
li.sort()

tmp = []

for i in range(0, n):
    cnt = 0

    for j in range(li[i], li[i] + 5):
        if j not in li:
            cnt += 1

    tmp.append(cnt)

print(min(tmp))