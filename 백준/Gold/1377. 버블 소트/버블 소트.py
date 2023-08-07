import sys
input = sys.stdin.readline

n = int(input())
arr = []
for i in range(n):
    a = int(input())
    arr.append((a,i))

ans = 0
for idx,value in enumerate(sorted(arr)):
    if value[1] > idx :
        ans = max(ans, value[1] - idx)

print(ans + 1)