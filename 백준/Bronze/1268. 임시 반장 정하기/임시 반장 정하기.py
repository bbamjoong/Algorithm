import sys
input = sys.stdin.readline

n = int(input())
classes = []
tmp = [0] * n
for i in range(n):
    classes.append(list(map(int, input().split())))
    tmp[i] = [0] * n

for i in range(5):
    for j in range(n):
        for k in range(j + 1, n):
            if classes[j][i] == classes[k][i]:
                tmp[k][j] = 1
                tmp[j][k] = 1

cnt = []
for s in tmp:
    cnt.append(s.count(1))

print(cnt.index(max(cnt)) + 1)