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
        parent[b] = a
    else:
        parent[a] = b


n = int(input())  # 원소 개수
m = int(input())  # 연산 개수

parent = [i for i in range(n + 1)]  # 각 원소의 부모를 자기 자신으로 초기화
floyd = [[int(1e9)] * (n + 1) for _ in range(n + 1)]  # 플로이드 워셜
for i in range(m):
    a, b = map(int, input().split())
    floyd[a][b] = 1
    floyd[b][a] = 1
    if find(a) != find(b):  # a와 b가 다른 집합에 속해있을 때
        union(a, b)  # 합집합 연산 수행

sets = {}  # 각 집합의 루트를 키로, 해당 집합의 원소를 값으로 갖는 딕셔너리
for i in range(1, n + 1):
    root = find(i)
    if root not in sets:
        sets[root] = [i]
    else:
        sets[root].append(i)

for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if i == j:
                floyd[i][j] = 0

            if floyd[i][j] > floyd[i][k] + floyd[k][j]:
                floyd[i][j] = floyd[i][k] + floyd[k][j]

li = []
for elements in sets.values():
    tmpMin = 1e9
    idx = 0
    for i in elements:
        maxElement = 0
        tmpIdx = 0
        for j in elements:
            if floyd[i][j] >= maxElement:
                maxElement = floyd[i][j]
                tmpIdx = i

        if maxElement <= tmpMin:
            tmpMin = maxElement
            idx = tmpIdx
    li.append(idx)

li.sort()
print(len(sets))
for i in li:
    print(i)