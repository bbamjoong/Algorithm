import sys
input = sys.stdin.readline
a = int(input())
li = [0,1,2]
for i in range(3,a+1):
    li.append((li[i-1] + li[i-2])%10007)

print(li[a])