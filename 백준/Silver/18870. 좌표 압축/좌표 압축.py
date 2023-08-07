import sys
input = sys.stdin.readline
n = int(input())
li = list(map(int, input().split()))

li1 = list(set(li))
li1.sort()

dict = {}
for index, num in enumerate(li1):
    dict[num] = index

for i in li:
    print(dict[i], end = ' ')