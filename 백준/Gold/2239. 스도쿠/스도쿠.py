import sys
input = sys.stdin.readline

arr = [list(map(int,list(input().strip()))) for _ in range(9)]

# 우리가 채워야될 곳의 좌표를 q에 저장
q = []
for i in range(9):
    for j in range(9):
        if arr[i][j] == 0:
            q.append([i,j])

def checkrow(x,i):
    for j in range(9):
        if i == arr[x][j]:
            return False
    return True

def checkcol(y,i):
    for j in range(9):
        if i == arr[j][y]:
            return False
    return True

def checkdiag(x,y,i):
    nx = x//3*3
    ny = y//3*3
    for j in range(3):
        for k in range(3):
            if i == arr[nx+j][ny+k]:
                return False
    return True

# cnt : 빈곳을 올바른 숫자로 넣은 개수
def dfs(cnt):
    # 모든 빈칸을 올바른 숫자로 넣었다면 그래프 출력
    if cnt == len(q):
        for i in range(9):
            print(*arr[i],sep='')
        exit()    

    # 0에 1~9중 어떤 값을 넣을지 체크한다.
    for i in range(1,10):
        x = q[cnt][0]
        y = q[cnt][1]
        
        # 행, 열, 3x3 행열에 모두 조건이 맞다면
        if checkrow(x,i) and checkcol(y,i) and checkdiag(x,y,i):
            # 해당 값을 추가해준다.
            arr[x][y] = i
            dfs(cnt+1)
            # 만약 어떠한 값을 넣고 / 다음 구간들을 탐색하는데 조건에 맞는 값이 없다면?
            # 조건에 맞는 마지막 값을 0으로 바꾸고 -> 다른 값으로 다시 탐색한다.
            arr[x][y] = 0

dfs(0)