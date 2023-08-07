import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

n = int(input())

inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))

inorder_idx = [0] * (n+1)
for i in range(n):
    inorder_idx[inorder[i]] = i

ans=[]

stack = [(0,n-1,0,n-1)]

while stack:
    instart, inend, poststart, postend = stack.pop()

    if (instart > inend) and (poststart > postend):
        continue

    root = postorder[postend]

    print(root, end=' ')

    leftnode = inorder_idx[root] - instart
    rightnode = inend - inorder_idx[root]

    stack.append((inorder_idx[root]+1 , inend, postend - rightnode ,postend - 1))
    stack.append((instart, inorder_idx[root] - 1, poststart, poststart + leftnode - 1))
