import sys
input = sys.stdin.readline
from collections import deque

a, b = map(int, input().split())

ladder_a = []
ladder_b = []
for i in range(a):
    num1, num2 = map(int, input().split())
    ladder_a.append(num1)
    ladder_b.append(num2)

snake_a = []
snake_b = []

for i in range(b):
    num1, num2 = map(int, input().split())
    snake_a.append(num1)
    snake_b.append(num2)

visited = [0] * 101
q = deque()
q.append((1,0))
while q:
    num, cnt = q.popleft()
    if num >= 100:
        print(cnt)
        break

    for i in range(1,7):
        next = num + i

        if 1 <= next <= 100 and visited[next]==0:
            visited[next] = 1
            if next in ladder_a:
                next = ladder_b[ladder_a.index(next)]
            elif next in snake_a:
                next = snake_b[snake_a.index(next)]
            q.append((next, cnt+1))