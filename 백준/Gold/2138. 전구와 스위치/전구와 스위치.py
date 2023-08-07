import sys
input = sys.stdin.readline


n = int(input())
arr1 = list(map(int, input().strip()))
arr2 = arr1[:]
arr2[0] = 1-arr2[0]
arr2[1] = 1-arr2[1]
ans = list(map(int, input().strip()))


cnt = 0
for i in range(1,n):
    if arr1[i-1] != ans[i-1]:
        cnt += 1
        arr1[i-1] = 1 - arr1[i-1]
        arr1[i] = 1 - arr1[i]
        if i != n-1:
            arr1[i+1] = 1 - arr1[i+1]

if arr1 == ans:
    print(cnt)
    exit()

cnt = 1
for i in range(1,n):
    if arr2[i-1] != ans[i-1]:
        cnt += 1
        arr2[i-1] = 1 - arr2[i-1]
        arr2[i] = 1 - arr2[i]
        if i != n-1:
            arr2[i+1] = 1 - arr2[i+1]

if arr2 == ans:
    print(cnt)    
    exit()

print(-1)