import sys
from collections import deque
input = sys.stdin.readline


n = int(input())

# 노드가 하나일 때 (1, 1)출력 후 종료
if n == 1:
    print(1, 1)
    exit()

tree = [[-1,-1,-1] for _ in range(n+1)]

# [0] : 시작노드를 찾기위한 밑작업, [1] : left, [2] : right
for i in range(n):
    node, left, right = map(int, input().split())
    tree[node][1] = left
    tree[node][2] = right
    # 자식노드라는 것을 나타내주기 위해 -1대신 1을 넣어줄 것이다.
    tree[left][0] = 1
    tree[right][0] = 1

# 시작노드가 1이 아닐 수 있기 때문에 시작노드를 찾아주는 작업
root = -1
for i in range(1, n+1):
    if tree[i][0] == -1:
        root = i

# 첫번째 값은 depth 두번째 값은 x축의 위치 저장할 것
visit = [[-1, -1] for _ in range(n+1)]

def maxdepth(root):
    queue = deque([root])
    visit[root][0] = 1

    while queue:
        node = queue.popleft()
        left = tree[node][1]
        right = tree[node][2]
        # 왼쪽자식노드가 존재할 때
        if left != -1:
            # 방문하지 않았다면
            if visit[left][0] == -1:
                # 부모노드의 depth + 1을 visit[left][0]에 저장
                visit[left][0] = visit[node][0] + 1
                queue.append(left)
        # 오른쪽자식노드가 존재할 때
        if right!= -1:
            # 방문하지 않았다면
            if visit[right][0] == -1:
                # 부모노드의 depth + 1을 visit[left][0]에 저장
                visit[right][0] = visit[node][0] + 1
                queue.append(right)
    return max(visit)[0]

# dist : x축의 좌표
dist = 0
def inorder(node):
    global dist
    # 노드의 left root가 존재할 때
    if tree[node][1] != -1:
        # 함수 호출
        inorder(tree[node][1])
    # 만약 왼쪽 끝까지 도착했다면 x축의 좌표에 1을 더해주고
    dist+=1
    # visit[node][1]에 x축의 좌표를 갱신해준다.
    visit[node][1] = dist

    # 이 후 노드의 right root가 존재할 때
    if tree[node][2] != -1:
        # 함수 호출
        inorder(tree[node][2])


maxdepth(root)
inorder(root)
maxdepth = maxdepth(root)

max_dist = 0
level = 0

# 깊이에 따른 최대 너비를 계산한다.
for d in range(1,maxdepth+1):
    minval = sys.maxsize
    maxval = 0
    # 같은 깊이일 때 노드의 최소 x값, 최대 x값 저장
    for i in range(1,n+1):
        if d == visit[i][0]:
            minval = min(visit[i][1], minval)
            maxval = max(visit[i][1], maxval)

    # max_dist보다 최대x값 - 최소x값+1이 더 클 경우 max_dist, level 갱신
    if max_dist < maxval - minval + 1:
        max_dist = maxval - minval + 1
        level = d

print(level, max_dist)
