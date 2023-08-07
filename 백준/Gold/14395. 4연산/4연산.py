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
    # 한번 도달한 숫자는 다시 만들 필요가 없다. 연산만 더 할 뿐이기 때문이다.
    check = []
    check.append(s)

    while q:
        num, ans = q.popleft()
        if num == t:
            res = ans
            break

        # -를 하게되면 0이 되기 때문에 고려할 필요가 없다.
        for i in ('*', '+', '/'):
            if i == '*':
                a = num * num
            elif i == '+':
                a = num + num
            else:
                a = 1

            # 나누기를 하면 1이된다. 즉, 답안보다 숫자가 클 때 숫자를 줄이는 방법이 없다는 것이다.
            # 따라서 숫자가 0이상 t이하여야 하고, 도달하지 않았던 숫자일 때 조건이 만족한다.
            if 0 <= a <= t and a not in check:
                check.append(a)
                q.append((a,ans+i))
    return res
        
print(bfs())
