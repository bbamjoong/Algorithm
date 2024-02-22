import heapq
import sys
input = sys.stdin.readline;

def find(x):
    if x == parent[x]:
        return x;
    parent[x] = find(parent[x])
    return parent[x];


def union(a, b):
    a = find(a);
    b = find(b);

    if a < b:
        parent[b] = a;
    else:
        parent[a] = b;


class Node:
    def __init__(self, x, y):
        self.x = x;
        self.y = y;

    def __lt__(self, other):
        if self.x == other.x:
            return self.y < other.y;

        return self.x < other.x;


if __name__ == "__main__":
    n = int(input());
    m = int(input());

    pq = [];

    for i in range(m):
        x, y = map(int, input().split());
        heapq.heappush(pq, Node(x, y));

    parent = [i for i in range(n + 1)];

    right = 0;
    while len(pq) != 0:
        node = heapq.heappop(pq);
        a = node.x;
        b = node.y;

        if a < right: # 앞에서 부쉈던 벽보다 시작점이 왼쪽이면 a를 옮김
            a = right;

        for i in range(a, b + 1):
            union(a, i);

        if b > right: # 가장 큰 방의 번호 갱신
            right = b;

    cnt = 0;
    for i in range(1, n + 1):
        if i == parent[i]:
            cnt += 1

    print(cnt)
