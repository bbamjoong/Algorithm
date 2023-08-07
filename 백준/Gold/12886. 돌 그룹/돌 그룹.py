import sys
input = sys.stdin.readline
from collections import deque

a, b, c = map(int, input().split())
total = a+b+c
visited = [[0] * (total + 1) for _ in range(total+1)]

if total % 3 != 0:
    print(0)
    exit()

q = deque()
q.append((a,b))
visited[a][b] = 1

while q:
    x, y = q.popleft()
    z = total - x - y
    if x==y==z:
        print(1)
        exit()

    for a1, b1 in (x,y),(y,z),(x,z):
        if a1<b1:
            b1-=a1
            a1+=a1
        elif a1>b1:
            a1-=b1
            b1+=b1
        c1 = total - a1 - b1
        x1 = min(a1,b1,c1)
        y1 = max(a1,b1,c1)
        if not visited[x1][y1]:
            q.append((x1,y1))
            visited[x1][y1] = True
print(0)