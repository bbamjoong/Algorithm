import sys
input = sys.stdin.readline

# 최소 스패닝 트리를 이용한다. 간선이 k개일 때 시간복잡도 O(klogk). 간선의 개수는 100C2 = 100 * 99 / 2 = 4950.
# 4950 * log(4950) = 4950 * 3.69 = 18,265

n = int(input())
# 별들의 좌표
coor = [list(map(float, input().split())) for _ in range(n)]

# 별1의 번호, 별2의 번호, 두 별 사이의 거리를 info에 추가한다.
info = []
for i in range(len(coor)):
    for j in range(i+1, len(coor)):
        x1, y1 = coor[i]
        x2, y2 = coor[j]
        length = ((x2 - x1)**2 + (y2 - y1) **2)**0.5
        info.append([i+1,j+1,round(length,2)])

parent = [0] + [x+1 for x in range(n)]

# 부모 별 찾기
def find(x):
    if x != parent[x]:
        return find(parent[x])
    return parent[x]

# 두 별을 연결할 때 부모가 더 작은 별을 기준으로 부모 변경
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