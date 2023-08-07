import sys
input = sys.stdin.readline
from collections import deque

n = int(input())

prime = [True for _ in range(10000)]
for i in range(2,100):
    if prime[i] == True:
        for j in range(2*i, 10000, i):
            prime[j] = False

def bfs(start, end):
    q = deque()
    q.append((start,0))

    visited = [0 for i in range(10000)]
    visited[start] = 1

    while q:
        num, cnt = q.popleft()

        if num == end:
            return cnt
        
        strnum = str(num)

        for i in range(4):
            for j in range(10):
                check = int(strnum[:i] + str(j) + strnum[i+1:])

                if visited[check] == 0 and prime[check] == True and check >= 1000:
                    visited[check] = 1
                    q.append((check, cnt+1))


for i in range(n):
    a, b = map(int, input().split())
    cnt = bfs(a,b)

    if cnt == None:
        print('Impossible')
    else:
        print(cnt)