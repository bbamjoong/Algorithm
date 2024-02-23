T = int(input())


def find(x):
    if parent[x] == x:
        return x;
    parent[x] = find(parent[x]);
    return parent[x];


def union(a, b):
    a = find(a);
    b = find(b);

    if a < b:
        parent[b] = a;
        return
    parent[a] = b;


for tc in range(1, T + 1):
    n = int(input())
    xList = list(map(int, input().split()));
    yList = list(map(int, input().split()));
    e = float(input())

    arr = []
    # 조합을 짭니다.
    for i in range(n):
        for j in range(i + 1, n):
            arr.append([i, j, (xList[i] - xList[j]) ** 2 + (yList[i] - yList[j]) ** 2]);

    arr.sort(key=lambda x: x[2])  # 정렬

    parent = [i for i in range(n + 1)]

    ans = 0;
    cnt = 0;
    for start, end, weight in arr:
        if find(start) != find(end):
            union(start, end);
            ans += weight;
            cnt+=1;

            if cnt == n-1:
                break;

    ans *= e;

    print(f"#{tc} {round(ans)}")
