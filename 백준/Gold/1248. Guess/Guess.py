import sys
input = sys.stdin.readline

n = int(input())
matrix = [[0]*n for i in range(n)]
arr = list(input())
res = []
k=0
for i in range(n):
    for j in range(i, n):
        temp = arr.pop(0)
        if temp =='+':
            matrix[i][j] = 1
        elif temp == '-':
            matrix[i][j] = -1

def check(idx):
    sum1 = 0
    for i in range(idx, -1, -1):
        sum1 += res[i]
        if sum1==0 and matrix[i][idx]!=0:
            return False
        elif sum1<0 and matrix[i][idx]>=0:
            return False
        elif sum1>0 and matrix[i][idx]<=0:
            return False
    return True

def dfs(idx):
    if idx == n:
        print(' '.join(map(str,res)))
        exit()

    for i in range(1,11):
        if matrix[idx][idx] == 0:
            res.append(0)
            if check(idx):
                dfs(idx+1)
            res.pop()
        
        else:
            res.append(matrix[idx][idx] * i)
            if check(idx):
                dfs(idx+1)
            res.pop()
            
dfs(0)