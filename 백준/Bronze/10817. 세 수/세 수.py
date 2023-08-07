import sys
input = sys.stdin.readline

arr = list(map(int, input().split()))
arr.sort()
print(arr[1])
