import heapq
import sys
input = sys.stdin.readline;

def find(x):
    if x == parent[x]:
        return x;
    return find(parent[x]);


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

        if a < right:
            a = right;

        for i in range(a, b + 1):
            union(a, i);

        if b > right:
            right = b;

    cnt = 0;
    for i in range(1, n + 1):
        if i == parent[i]:
            cnt += 1

    print(cnt)
