import sys
input = sys.stdin.readline
from copy import deepcopy

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]

def up(graph):
    for j in range(n):
        maincheck = 0
        for i in range(1,n):
            if graph[i][j]:
                #ex) 0 4 0 0 0 이라면 -> 4 0 0 0 0으로 바꿔준다.
                if graph[maincheck][j] == 0:
                    graph[maincheck][j], graph[i][j] = graph[i][j], 0
                #ex) maincheck = 1, check = 3일 때 [0 2 0 2 0] -> [0 (2*2) 0 (2->0) 0]
                #그리고 포인터를 밑으로 내려준다.                
                elif graph[maincheck][j] == graph[i][j]:
                    graph[maincheck][j], graph[i][j] = 2 * graph[maincheck][j], 0
                    maincheck += 1
                #ex) maincheck = 1, check = 3일 때 [0 2 0 4 0] -> [0 (2->4) 0 (4->2) 0]
                #포인터 아래의 값과 check값을 바꿔준다.
                else:
                    maincheck += 1
                    graph[maincheck][j], graph[i][j] = graph[i][j], graph[maincheck][j]
    return graph

def down(graph):
    for j in range(n):
        maincheck = n-1
        for i in range(n-2,-1,-1):
            if graph[i][j]:
                if graph[maincheck][j] == 0:
                    graph[maincheck][j], graph[i][j] = graph[i][j], 0                
                elif graph[maincheck][j] == graph[i][j]:
                    graph[maincheck][j], graph[i][j] = 2 * graph[maincheck][j], 0
                    maincheck -= 1
                else:
                    maincheck -= 1
                    graph[maincheck][j], graph[i][j] = graph[i][j], graph[maincheck][j]
    return graph

def left(graph):
    for i in range(n):
        maincheck = 0
        for j in range(1,n):
            if graph[i][j]:
                if graph[i][maincheck] == 0:
                    graph[i][maincheck], graph[i][j] = graph[i][j], 0                
                elif graph[i][maincheck] == graph[i][j]:
                    graph[i][maincheck], graph[i][j] = 2 * graph[i][maincheck], 0
                    maincheck += 1
                else:
                    maincheck += 1
                    graph[i][maincheck], graph[i][j] = graph[i][j], graph[i][maincheck]
    return graph

def right(graph):
    for i in range(n):
        maincheck = n-1
        for j in range(n-2,-1,-1):
            if graph[i][j]:
                if graph[i][maincheck] == 0:
                    graph[i][maincheck], graph[i][j] = graph[i][j], 0                
                elif graph[i][maincheck] == graph[i][j]:
                    graph[i][maincheck], graph[i][j] = 2 * graph[i][maincheck], 0
                    maincheck -= 1
                else:
                    maincheck -= 1
                    graph[i][maincheck], graph[i][j] = graph[i][j], graph[i][maincheck]
    return graph

ans = 0
def dfs(graph, cnt):
    global ans
    if cnt == 5:
        ans = max(ans, max(map(max,graph)))
        return    
    dfs(up(deepcopy(graph)), cnt + 1)
    dfs(down(deepcopy(graph)), cnt + 1)
    dfs(left(deepcopy(graph)), cnt + 1)
    dfs(right(deepcopy(graph)), cnt + 1)

dfs(board,0)
print(ans)
