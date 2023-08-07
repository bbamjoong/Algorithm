import sys
input=sys.stdin.readline

arr = input().strip()
ans = 10
for i in range(len(arr)-1):
    if arr[i] == arr[i+1]:
        ans += 5
    else:
        ans += 10
print(ans)