import sys
input = sys.stdin.readline
n = int(input())
li1 = list(map(int, input().split()))

for i in range(1,n):
    li1[i] = max(li1[i], li1[i-1] + li1[i])

print(max(li1))