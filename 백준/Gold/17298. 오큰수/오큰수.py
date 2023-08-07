import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

stack = [0]
ans = [-1] * n

for i in range(n):
    while True:
        if arr[i] > arr[stack[-1]]:
            ans[stack[-1]] = arr[i]
            stack.pop(-1)
            if len(stack) == 0:
                stack.append(i)
                break
        else:
            stack.append(i)
            break
print(*ans)