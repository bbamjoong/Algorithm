import sys
input = sys.stdin.readline

n, k = map(int, input().split())
arr = [x+1 for x in range(n)]

pointer = 0
ans = []
while arr:
    pointer += k - 1

    if pointer >= len(arr):
        pointer %= len(arr)

    ans.append(str(arr.pop(pointer)))

print("<", ", ".join(ans), ">", sep="")