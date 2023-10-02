import sys
input = sys.stdin.readline

n = int(input())
arr = sorted(list(map(int, input().split())))

a, b = divmod(n, 2)

print(arr[a+b-1])