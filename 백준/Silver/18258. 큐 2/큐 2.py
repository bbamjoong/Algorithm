import sys
input = sys.stdin.readline
from collections import deque

n = int(input())

q = deque()

for i in range(n):
    a, *b = map(str, input().split())

    if a == 'push':
        q.append(int(b[0]))
    
    elif a == 'pop':
        if q:
            print(q.popleft())
        else:
            print(-1)
    
    elif a == 'size':
        print(len(q))
    
    elif a == 'empty':
        if q:
            print(0)
        else:
            print(1)
    
    elif a == 'front':
        if q:
            print(q[0])
        else:
            print(-1)

    elif a == 'back':
        if q:
            print(q[-1])
        else:
            print(-1)