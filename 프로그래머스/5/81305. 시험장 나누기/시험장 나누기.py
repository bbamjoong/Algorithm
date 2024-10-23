import sys
sys.setrecursionlimit(10**6)

class Node:
    def __init__(self, idx, value):
        self.idx = idx
        self.value = value
        self.parent = None
        self.left = None
        self.right = None

nodes = []
nums = []
cnt = 0

def calculate(limit, root):
    global cnt
    cnt = 0
    dfs(limit, root.idx)
    return cnt + 1

def dfs(limit, node_idx): # 내가 타고 내려간 노드의 합을 return함.
    global nodes, nums, cnt
    
    left, right = 0, 0
    node = nodes[node_idx]
    
    if node.left != -1: # 왼쪽으로 타고 내려감
        left = dfs(limit, node.left)
    
    if node.right != -1: # 오른쪽으로 타고 내려감
        right = dfs(limit, node.right)
    
    # 전부 Parent와 결합 -> cnt += 0
    if nums[node.idx] + left + right <= limit:
        return nums[node.idx] + left + right
    
    # 왼쪽, 오른쪽중 하나가 Parent와 결합 -> cnt += 1
    if nums[node.idx] + min(left, right) <= limit:
        cnt += 1
        return nums[node.idx] + min(left, right)
    
    # 모두 분리 -> cnt += 2
    cnt += 2
    return nums[node.idx]
    

def solution(k, num, links):
    global nodes, nums
    
    nums = num # 전역변수로 쓰기 위함
    nodes = [Node(idx, nums[idx]) for idx in range(len(nums))]
    
    # 노드들의 left, right idx 설정
    for idx in range(len(links)):
        left, right = links[idx]
        nodes[idx].left = left
        nodes[idx].right = right
        
        # -1이 아니면 부모 노드도 추가해줘야함.(양방향)
        if left != -1:
            nodes[left].parent = idx
        if right != -1:
            nodes[right].parent = idx
        
    # root 찾기
    root = None
    for node in nodes:
        if node.parent is None:
            root = node
    
    # 그룹 하나면 sum만 하고 종료
    if k == 1:
        return sum(nums)
    
    start, end = max(nums), sum(nums)
    while start <= end:
        mid = (start + end) // 2
        
        if calculate(mid, root) <= k:
            end = mid - 1
            
        else:
            start = mid + 1 
    return end + 1
