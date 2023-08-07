import sys
input=sys.stdin.readline
from collections import deque
sys.setrecursionlimit(10**6)

def bfs():
    global s
    q = deque()
    q.append((1,0,0))    
    visited=[[False]*1001 for _ in range(1001)]

    while q:
        screen,clip,cnt=q.popleft()
        if screen==s:
            print(cnt)
            break

        for i in range(3):
            if i==0:
                clip_1, screen_1 = screen, screen
            elif i==1:
                clip_1, screen_1 = clip, screen+clip
            else:
                clip_1, screen_1 = clip_1, screen-1

            if clip_1<0 or clip_1>=1001 or screen_1<0 or screen_1>=1001 or visited[screen_1][clip_1] != False:
                continue

            visited[screen_1][clip_1] = True
            q.append((screen_1,clip_1,cnt+1))

s = int(input())
bfs()