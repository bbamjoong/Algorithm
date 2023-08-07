import sys
input = sys.stdin.readline

n = int(input())
graph = [[" ", " ", "*", " ", " "], [" ", "*", " ", "*", " "], ["*", "*", "*", "*", "*"]]

def star(graph):
    arr = []
    for i in range(2*len(graph)):
        if i // len(graph) == 1:
            i = i%len(graph)
            arr.append([''.join(graph[i] + [' '] + graph[i])])
        else:
            arr.append([''.join([' ']*len(graph) + graph[i] + [' ']*len(graph))])
    return arr

a = n//3
cnt = 0
while a > 1:
    a//=2
    cnt+=1


for i in range(cnt):
    graph = star(graph)

[print(''.join(graph[i])) for i in range(n)]