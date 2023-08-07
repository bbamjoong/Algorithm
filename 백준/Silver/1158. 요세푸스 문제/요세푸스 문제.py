from collections import deque

n, k = map(int, input().split())
q = deque(range(1,n+1))
res = []

while q:
    for i in range(k-1):
        q.append(q.popleft())
    res.append(q.popleft())
print(f'<{", ".join(map(str, res))}>')
