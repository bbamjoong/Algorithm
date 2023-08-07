import sys
input = sys.stdin.readline

a, b = map(int, input().split())
arr = []
for i in range(2):
    li1 = list(map(int, input().split()))
    arr.extend(li1)

print(*sorted(arr))