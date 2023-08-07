import sys
input = sys.stdin.readline

n = int(input())
arr1 = list(map(int, input().split()))
m = int(input())
arr2 = list(map(int, input().split()))

hash = {}

for char in arr1:
    if char in hash:
        hash[char] += 1
    else:
        hash[char] = 1

for char in arr2:
    if char in hash:
        print(hash[char], end = ' ')
    else:
        print(0, end=' ')