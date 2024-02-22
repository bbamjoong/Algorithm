t = int(input())

def find(x):
    if parent[x] == x:
        return x;
    parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    a = find(a);
    b = find(b);
    
    if a < b:
        parent[b] = a;
        return
    parent[a] = b;


for tc in range(1, t+1):
    v, e = map(int, input().split())
    graph = [list(map(int, input().split())) for _ in range(e)]
    graph.sort(key = lambda x : x[2]) # 가중치를 기준으로 정렬
    parent = [i for i in range(v+1)];
    ans = 0;
    
    for start, end, weight in graph:
        if (find(start) != find(end)):
            union(start, end);
            ans+= weight;
    
    print(f"#{tc} {ans}")