import sys
sys.setrecursionlimit(10**6)

class Node:
    def __init__(self, x, y, value):
        self.x = x
        self.y = y
        self.value = value
        self.left = None
        self.right = None

class Tree:
    def __init__(self):
        self.root = None
        self.pre_answer = []
        self.post_answer = []
        
    def create(self, nodes):        
        for i in range(len(nodes)):
            if i == 0:
                self.root = nodes[i]
                continue
                
            self.insert(self.root, nodes[i])
        
    def insert(self, parent, node):
        if parent == None:
            parent = node
            
        if node.x < parent.x:
            parent.left = self.insert(parent.left, node)
            
        elif node.x > parent.x:
            parent.right = self.insert(parent.right, node)
            
        return parent
                    
    def preorder(self, node):
        self.pre_answer.append(node.value)
        
        if node.left:
            self.preorder(node.left)
            
        if node.right:
            self.preorder(node.right)
    
    def postorder(self, node):
        if node.left:
            self.postorder(node.left)
            
        if node.right:
            self.postorder(node.right)
            
        self.post_answer.append(node.value)
        
def solution(nodeinfo):
    nodes = []
    for i in range(len(nodeinfo)):
        nodes.append([nodeinfo[i][0], nodeinfo[i][1], i + 1])
    nodes.sort(key=lambda x: (-x[1], x[0]))
    
    tree = Tree()
    tree.create([Node(x, y, value) for x, y, value in nodes])
    tree.preorder(tree.root)
    tree.postorder(tree.root)
        
    return [tree.pre_answer, tree.post_answer]