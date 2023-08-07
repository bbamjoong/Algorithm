import sys
input = sys.stdin.readline

x = int(input())

li1 = list(map(int, input().split()))
li1_r = li1[::-1]

a = [1 for i in range(x)]
b = [1 for i in range(x)]

for i in range(x):
    for j in range(i):
        if li1[i] > li1[j]:
            a[i] = max(a[i], a[j]+1)
        if li1_r[i] > li1_r[j]:
            b[i] = max(b[i], b[j]+1)
b.reverse()
res = [0 for i in range(x)]
for i in range(x):
    res[i] = a[i] + b[i] -1

print(max(res))