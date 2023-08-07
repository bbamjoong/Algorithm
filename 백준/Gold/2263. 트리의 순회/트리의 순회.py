import sys
input = sys.stdin.readline
from collections import deque

n = int(input())

inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))

# 어떤 노드가 몇번째 index에 있는지 저장
inorder_idx = [0] * (n+1)
for i in range(n):
    inorder_idx[inorder[i]] = i


q = deque()
q.append((0,n-1,0,n-1))

while q:
    instart, inend, poststart, postend = q.popleft()

    if (instart > inend) and (poststart > postend):
        continue

    root = postorder[postend]

    print(root, end=' ')

    # inorder기준으로 root왼쪽 오른쪽의 노드 수
    leftnode = inorder_idx[root] - instart
    rightnode = inend - inorder_idx[root]

    # 왼1 오1 -> 왼2오2 오1 -> 왼3오3 오2 오1 -> .... 이런식으로 탐색을 진행하기 때문에
    # 루트를 기점으로 오른쪽을 탐색하는 것을 먼저 appendleft. 그 후 루트를 기점으로 왼쪽을 탐색하는 것을 appendleft 한다.

    # 1. 루트를 기점으로 오른쪽 탐색

    # inorder의 경우 루트의 index를 기준으로 한다. BUT postorder의 경우 / inorder에서 root를 기점으로 오른쪽에 존재하는 노드의 수 만큼 노드가 존재하게 배치를 해야한다.
    # 이 때 postorder의 끝나는 부분은  postend - 1로 항상 고정이다.
    q.appendleft((inorder_idx[root]+1 , inend, postend - rightnode ,postend - 1))

    # postorder의 경우 root를 기점으로 왼쪽에 존재하는 노드의 수 만큼 노드가 존재하게 배치를 해야한다.
    # 이 때 postorder의 시작하는 부분은 poststart로 항상 고정이다.
    q.appendleft((instart, inorder_idx[root] - 1, poststart, poststart + leftnode - 1))
