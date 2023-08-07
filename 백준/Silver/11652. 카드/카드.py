import sys
input = sys.stdin.readline

n = int(input())

dict1 = {}

for i in range(n):
    a = int(input())
    if a in dict1.keys():
        dict1[a] += 1
    else:
        dict1[a] = 1

a = sorted(dict1.items(),key = lambda x : (-x[1],x[0]))

print(a[0][0])