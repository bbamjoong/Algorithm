import sys
input = sys.stdin.readline

from collections import defaultdict

hash = defaultdict(int)
n = int(input())
arr1 = list(map(int, input().split()))
m = int(input())
arr2 = list(map(int, input().split()))

for i in arr1:
    hash[i] += 1

for i in arr2:
    print(hash[i], end = ' ')