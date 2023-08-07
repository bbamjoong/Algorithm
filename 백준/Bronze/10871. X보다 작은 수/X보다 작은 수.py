import sys
n,x = map(int, sys.stdin.readline().split())
num = list(map(int, sys.stdin.readline().split()))

for i in range(n):
    if num[i] < x :
        print(num[i], end =' ')