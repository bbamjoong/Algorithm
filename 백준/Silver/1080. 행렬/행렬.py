import sys
input = sys.stdin.readline

m, n = map(int, input().split())

arr1 = [list(map(int, input().strip())) for _ in range(m)]
arr2 = [list(map(int, input().strip())) for _ in range(m)]

cnt = 0

def convert(x,y):
    for i in range(x,x+3):
        for j in range(y,y+3):
            arr1[i][j] = 1-arr1[i][j]

for i in range(m-2):
    for j in range(n-2):
        if arr1[i][j] != arr2[i][j]:
            convert(i,j)
            cnt+=1

if arr1 == arr2:
    print(cnt)
else:
    print(-1)