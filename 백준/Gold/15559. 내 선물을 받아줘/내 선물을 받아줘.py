import sys

input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
direction = ['W', 'E', 'N', 'S']

n, m = map(int, input().split())
arr = []
for i in range(n):
    for char in input().strip():
        arr.append(char)
parent = [i for i in range(n * m)]


def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = parent[a]
        return;

    parent[a] = parent[b]


for i in range(n * m):
    x = i // m;
    y = i % m;
    dir = direction.index(arr[x * m + y]);

    nx = x + dx[dir];
    ny = y + dy[dir];
    union(parent[i], parent[nx * m + ny])

set = set();
for i in range(n * m):
    set.add(find(parent[i]))

print(len(set))