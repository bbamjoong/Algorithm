import sys

input = sys.stdin.readline

while True:
    n, k = map(int, input().split())
    if n == 0 and k == 0:
        break;

    arr = [-1] + list(map(int, input().split()))
    parent = [0] * (n + 1)

    target = 0
    idx = -1
    for i in range(1, n + 1):
        if arr[i] == k:
            target = i

        if arr[i] > arr[i - 1] + 1:  # 2이상 차이가 나면.
            # 루트노드의 경우 최소 1임. 그렇다면 0번째 값은 -1 이하로 배치해야함.
            idx += 1

        parent[i] = idx
        
    ans = 0
    for i in range(1, n + 1):
        if parent[parent[target]] < parent[i]:
            if parent[i] != parent[target] and parent[parent[i]] == parent[parent[target]]:
                ans += 1

    print(ans)
