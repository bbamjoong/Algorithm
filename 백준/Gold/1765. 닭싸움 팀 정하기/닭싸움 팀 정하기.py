import sys
input = sys.stdin.readline

def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = parent[a]
        return
    parent[a] = parent[b]

n = int(input())
m = int(input())

parent = [i for i in range(n + 1)]
enemies = [[] for _ in range(n + 1)]

for i in range(m):
    command, a, b = input().split()
    a, b = int(a), int(b)

    if command == 'F':
        union(a, b)
    else:
        enemies[a].append(b)
        enemies[b].append(a)

for i in range(1, n + 1):
    if len(enemies[i]) > 1:
        for j in range(1, len(enemies[i])):
            union(enemies[i][j - 1], enemies[i][j])

ans = 0
for i in range(1, n+1):
    if parent[i] == i:
        ans+=1

print(ans)
