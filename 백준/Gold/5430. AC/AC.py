import sys
input = sys.stdin.readline
from collections import deque

t = int(input())

for i in range(t):
    p = input().strip()
    n = int(input())
    arr = deque(input().rstrip()[1:-1].split(','))
    
    cnt = 0
    stop = False

    for ins in p:
        if ins == 'R':
            cnt += 1
        
        elif ins == 'D':
            if len(arr) == 0 or arr==deque(['']):
                print('error')
                stop = True
                break
            else:
                if cnt % 2 == 0:
                    arr.popleft()
                else:
                    arr.pop()

    if not stop:
        if cnt % 2 == 0:
            print("[" + ",".join(arr) + "]")
        else:
            arr.reverse()
            print("[" + ",".join(arr) + "]")