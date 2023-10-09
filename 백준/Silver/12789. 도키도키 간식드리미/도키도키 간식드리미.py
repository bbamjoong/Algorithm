import sys
input = sys.stdin.readline

n = int(input())

arr = list(map(int, input().split()))
stack = []

ans = 1
 
while arr:
    if arr[0] == ans:
        arr.pop(0)
        ans += 1
    else:
        stack.append(arr.pop(0))
 
    while stack:
        if stack[-1] == ans:
            stack.pop()
            ans += 1
        else:
            break
 
if not stack: 
    print('Nice')
else:
    print('Sad')