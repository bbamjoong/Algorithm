import sys
input = sys.stdin.readline
print = sys.stdout.write
n = int(input())
li = [input() for i in range(n)]
li = list(set(li))
li.sort()
li.sort(key = len)
for i in li:
    print(i)