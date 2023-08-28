import sys
input = sys.stdin.readline
    
n = int(input())

if n == 0:
    print(0)
    exit(0)
arr = [int(input()) for _ in range(n)]
arr.sort()

n1 = round((n + 0.00001) * 0.15)

arr1 = arr[n1:n-n1]

print(round((sum(arr1) + 0.00001)/len(arr1)))