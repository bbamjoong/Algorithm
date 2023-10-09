import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
arr1 = list(map(int, input().split()))
arr2 = list(map(int, input().split()))

m = int(input())
arr3 = list(map(int, input().split()))

q = deque()

for i in range(n):
    if arr1[i] == 0:
        q.appendleft(arr2[i])
        
for i in range(m):
    q.append(arr3[i])
    print(q.popleft(), end=' ')