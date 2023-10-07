import sys
input = sys.stdin.readline

n = int(input())

d = dict()

for _ in range(n):
    name, status = map(str, input().split())
    if status == 'enter':
        d[name] = True
    
    else:
        d.pop(name)

d1 = sorted(d.keys(), reverse = True)
for i in d1:
    print(i)