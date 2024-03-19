import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)


# 중위순회
def inorder_traversal(node):
    global last_node
    
    if node[1] != '-1':
        inorder_traversal(tree[node[1]])
        
    last_node = node[0]
    
    if node[2] != '-1':
        inorder_traversal(tree[node[2]])


def similar_inorder_traversal(node):
    global last_node, cnt, n

    if node[1] != '-1':
        similar_inorder_traversal(tree[node[1]])
        cnt += 1

    if node[2] != '-1':
        similar_inorder_traversal(tree[node[2]])
        cnt += 1

    if node[0] == last_node:  #inorder-traversal의 마지막 값과 같으면 return
        print(cnt + n - 1)
        return


n = int(input())
tree = {}
for i in range(n):
    value, left, right = input().split()
    tree[value] = [value, left, right]

last_node = 0
cnt = 0
inorder_traversal(tree['1'])
similar_inorder_traversal(tree['1'])
