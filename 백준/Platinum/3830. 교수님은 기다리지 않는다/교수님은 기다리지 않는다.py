import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)


def find(x):
    if parent[x] == x:
        return x

    px = find(parent[x]) # x의 부모를 구하는데, 부모 바꾸면 무게 또 갱신은해줘야됨
    weight[x] += weight[parent[x]]
    parent[x] = px

    return parent[x]


def union(a, b, w):
    pa = find(a)
    pb = find(b)

    if pa == pb:
        return

    # a를 루트로 삼겠다. 가벼운쪽을 루트로 달아줌.
    parent[pb] = pa
    weight[pb] = weight[pa] + weight[a] + w - weight[b]


while True:
    n, m = map(int, input().split())

    if n == 0 and m == 0:
        break

    parent = [i for i in range(n+1)]
    weight = [0] * (n+1)

    for _ in range(m):
        arr = list(input().rstrip().split())
        a = int(arr[1])
        b = int(arr[2])

        if arr[0] == '!':
            union(a, b, int(arr[3]))
        else:
            if find(a) != find(b):
                print("UNKNOWN")
            else:
                print(weight[b] - weight[a])