import sys
input = sys.stdin.readline
from collections import deque

s, t = map(int, input().split())

if s == t:
    print(0)
    exit()

def bfs():
    global s, t
    res = -1
    q = deque()
    q.append((s,''))
    check = []
    check.append(s)

    while q:
        num, ans = q.popleft()
        if num == t:
            res = ans
            break

        for i in ('*', '+', '/'):
            if i == '*':
                a = num * num
            elif i == '+':
                a = num + num
            else:
                a = 1

            if 0 <= a <= t and a not in check:
                check.append(a)
                q.append((a,ans+i))
    return res
        
print(bfs())