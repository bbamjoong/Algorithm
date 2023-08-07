import sys
from collections import Counter
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
stack = [0]
ans = [-1] * n
count = Counter(arr)

for i in range(n):
    while True:
        if count[arr[stack[-1]]] < count[arr[i]]:
            ans[stack[-1]] = arr[i]
            stack.pop()
            if len(stack) == 0:
                stack.append(i)
                break
        else:
            stack.append(i)
            break

print(*ans)
