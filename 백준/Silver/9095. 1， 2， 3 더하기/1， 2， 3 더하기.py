import sys
input = sys.stdin.readline
t = int(input())
li = [0,1,2,4] + [0] * 11
for j in range(t):
    a = int(input())
    for i in range(4,a+1):
        li[i] = (li[i-1] + li[i-2] + li[i-3])
    print(li[a])