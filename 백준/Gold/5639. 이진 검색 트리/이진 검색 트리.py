import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

preorder = []

while True:
    try:
        preorder.append(int(input()))
    except:
        break

# index를 이용할 것임
def postorder(root, end):

    if root > end:
        return
    
    # root보다 큰 값이 없으면? 다 왼쪽 서브트리
    right_start = end + 1

    for i in range(root, end+1):
        # root보다 큰 값이 있으면 범위 내에서 그 값부터 end까지는 "오른쪽 서브트리"
        if preorder[root] < preorder[i]:
            right_start = i
            break

    # 왼쪽서브트리
    postorder(root+1, right_start - 1)

    # 오른쪽서브트리
    postorder(right_start, end)

    # 루트
    print(preorder[root])


postorder(0, len(preorder) - 1)