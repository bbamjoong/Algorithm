import sys
input = sys.stdin.readline

n = int(input())
arr1 = list(map(int, input().split()))
arr2 = list(map(int, input().split()))

arr = []
for i in range(n):
    if arr1[i] == 0:
        arr.append(arr2[i])

arr.reverse()

m = int(input())
arr3 = list(map(int, input().split()))

arr.extend(arr3)

print(*arr[:m])