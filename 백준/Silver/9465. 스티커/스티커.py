import sys
input = sys.stdin.readline

n = int(input())

for i in range(n):
    k = int(input())
    li1 = list(map(int, input().split()))
    li2 = list(map(int, input().split()))
    if k == 1:
        print(max(li1[0], li2[0]))
    else:
        li1[1] += li2[0]
        li2[1] += li1[0]
        for j in range(2,k):
            li1[j] += max(li2[j-1], li2[j-2])
            li2[j] += max(li1[j-1], li1[j-2])
        print(max(li1[-1], li2[-1]))