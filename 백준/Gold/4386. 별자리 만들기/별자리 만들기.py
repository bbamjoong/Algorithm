import sys
input = sys.stdin.readline

n = int(input())
coor = [list(map(float, input().split())) for _ in range(n)]

info = []
for i in range(len(coor)):
    for j in range(i+1, len(coor)):
        x1, y1 = coor[i]
        x2, y2 = coor[j]
        length = ((x2 - x1)**2 + (y2 - y1) **2)**0.5
        info.append([i+1,j+1,round(length,2)])

parent = [0] + [x+1 for x in range(n)]

def find(x):
    if x != parent[x]:
        return find(parent[x])
    return parent[x]

def union(a,b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b

info.sort(key = lambda x : x[2])

ans = 0
for a, b, c in info:
    if find(a) != find(b):
        union(a,b)
        ans += c

print(ans)