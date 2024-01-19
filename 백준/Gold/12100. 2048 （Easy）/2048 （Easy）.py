import sys
input = sys.stdin.readline
from copy import deepcopy

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]

def up(graph):
    for j in range(n):
        pointer = 0
        for i in range(1,n):
            if graph[i][j]:
                tmp = graph[i][j]
                graph[i][j] = 0

                if graph[pointer][j] == 0:
                    graph[pointer][j] = tmp
                
                elif graph[pointer][j] == tmp:
                    graph[pointer][j] *= 2
                    pointer += 1

                else:
                    pointer += 1
                    graph[pointer][j] = tmp
    return graph

def down(graph):
    for j in range(n):
        pointer = n-1
        for i in range(n-2,-1,-1):
            if graph[i][j]:
                tmp = graph[i][j]
                graph[i][j] = 0

                if graph[pointer][j] == 0:
                    graph[pointer][j] = tmp
                
                elif graph[pointer][j] == tmp:
                    graph[pointer][j] *= 2
                    pointer -= 1

                else:
                    pointer -= 1
                    graph[pointer][j] = tmp
    return graph
            
def left(graph):
    for i in range(n):
        pointer = 0
        for j in range(1,n):
            if graph[i][j]:
                tmp = graph[i][j]
                graph[i][j] = 0

                if graph[i][pointer] == 0:
                    graph[i][pointer] = tmp

                elif graph[i][pointer]  == tmp:
                    graph[i][pointer] *= 2
                    pointer += 1

                else:
                    pointer += 1
                    graph[i][pointer]= tmp                    
    return graph

def right(graph):
    for i in range(n):
        pointer = n - 1
        for j in range(n - 2, -1, -1):
            if graph[i][j]:
                tmp = graph[i][j]
                graph[i][j] = 0
                if graph[i][pointer] == 0:
                    graph[i][pointer] = tmp
                elif graph[i][pointer]  == tmp:
                    graph[i][pointer] *= 2
                    pointer -= 1
                else:
                    pointer -= 1
                    graph[i][pointer] = tmp
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