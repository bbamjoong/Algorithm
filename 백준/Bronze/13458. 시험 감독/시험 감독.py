import sys
input=sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
b, c = map(int, input().split())

cnt = n
for i in range(n):
    arr[i] -=b
    if arr[i] > 0:
        if arr[i] % c:
            cnt += arr[i] // c + 1
        else:
            cnt += arr[i] // c

print(cnt)