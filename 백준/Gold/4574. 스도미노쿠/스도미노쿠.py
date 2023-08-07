import sys
input = sys.stdin.readline

dx = [-1,1,0,0]
dy = [0,0,-1,1]
cnt = 1
while True:
    arr = [[0] * 9 for _ in range(9)]
    n = int(input())

    if n == 0:
        break

    domino = []
    for i in range(1,10):
        for j in range(i+1, 10):
            domino.append((i,j))

    for _ in range(n):
        u, lu, v, lv = map(str, input().split())
        u, v = int(u), int(v)
        arr[ord(lu[0])-65][int(lu[1])-1] = u
        arr[ord(lv[0])-65][int(lv[1])-1] = v
        domino.remove((min(u,v), max(u,v)))

    li = list(map(str, input().split()))
    for i in li:
        arr[ord(i[0])-65][int(i[1])-1] = li.index(i) + 1

    def check(x,y,number):
        for i in range(9):
            if arr[x][i] == number:
                return False
        for i in range(9):
            if arr[i][y] == number:
                return False
        nx, ny = x//3*3, y//3*3
        for i in range(nx, nx+3):
            for j in range(ny, ny+3):
                if arr[i][j] == number:
                    return False
        return True

    success = 0
    def dfs(domino):
        global success
        if len(domino) == 0:
            success = 1
            print('Puzzle', cnt)
            for i in range(9):
                for j in range(9):
                    print(arr[i][j], end='')
                print()
            return        

        if success == 1:
            return 

        for i in range(9):
            for j in range(9):
                if arr[i][j] == 0:
                    x, y = i, j

        for n1, n2 in domino:
            for d in range(4):
                nx, ny = x+dx[d], y+dy[d]

                if nx<0 or nx>=9 or ny<0 or ny>=9:
                    continue
                if arr[nx][ny] != 0:
                    continue

                domino1 = domino[:]
                domino1.remove((n1,n2))
                
                if check(x,y,n1) and check(nx,ny,n2):
                    arr[x][y], arr[nx][ny] = n1, n2
                    dfs(domino1)
                    arr[x][y], arr[nx][ny] = 0, 0

                domino2 = domino[:]
                domino2.remove((n1,n2))
                if check(x,y,n2) and check(nx,ny,n1):
                    arr[x][y], arr[nx][ny] = n2, n1
                    dfs(domino2)
                    arr[x][y], arr[nx][ny] = 0, 0

    dfs(domino)
    cnt += 1