import sys
input = sys.stdin.readline
from collections import defaultdict
sm = 0
dict = defaultdict(int)
for i in range(10):
    a = int(input())
    sm += a
    dict[a] += 1

print(int(sm/10))
k = max(dict.items(),key = lambda x : x[1])
print(k[0])