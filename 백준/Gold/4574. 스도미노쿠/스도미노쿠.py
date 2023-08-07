import sys
input = sys.stdin.readline

# 한 칸 이동 좌표
dx = [-1,1,0,0]
dy = [0,0,-1,1]

# cnt 번째 puzzle
cnt = 1

while True:
    arr = [[0] * 9 for _ in range(9)]
    n = int(input())

    if n == 0:
        break

    # 도미노의 배열을 만들어준다.
    domino = []
    for i in range(1,10):
        for j in range(i+1, 10):
            domino.append((i,j))

    # 이미 사용된 도미노를 제거 및 스도쿠 판에 도미노 배치.
    for _ in range(n):
        u, lu, v, lv = map(str, input().split())
        u, v = int(u), int(v)
        arr[ord(lu[0])-65][int(lu[1])-1] = u
        arr[ord(lv[0])-65][int(lv[1])-1] = v
        domino.remove((min(u,v), max(u,v)))

    # 스도쿠 판에 숫자 배치.
    li = list(map(str, input().split()))
    for i in li:
        arr[ord(i[0])-65][int(i[1])-1] = li.index(i) + 1

    # 가로, 세로, 범위 내 숫자가 중복되는지 체크.
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


    # 이미 성공한 경우의 수가 나왔을 때 빠르게 재귀를 벗어나기 위해
    # 설정해준 변수 success
    success = 0

    def dfs(list1_):
        global success

        # 입력받은 list(도미노)의 길이가 0일 경우 리턴.
        if len(list1_) == 0:
            success = 1
            print('Puzzle', cnt)
            for i in range(9):
                for j in range(9):
                    print(arr[i][j], end='')
                print()
            return        

        # 이미 성공했을 경우 리턴.
        if success == 1:
            return 

        # 비어있는 칸을 탐색.
        # 재귀를 돌면서 처음에 탐색한 칸은 도미노로 채워질 것임
        for i in range(9):
            for j in range(9):
                if arr[i][j] == 0:
                    # x, y -> 고정시킬 좌표
                    x, y = i, j

        # 도미노의 숫자 두개 추출
        for n1, n2 in list1_:
            for d in range(4):
                #nx, ny -> x, y의 주변으로 1칸 이동한 좌표
                nx, ny = x+dx[d], y+dy[d]

                # 스도쿠 판 범위를 벗어날 경우 건너뛰기
                if nx<0 or nx>=9 or ny<0 or ny>=9:
                    continue
                # arr[x][y]는 0이지만 arr[nx][ny]도 0이어야한다.
                # arr[nx][ny]가 0이 아니라면 건너뛰기
                if arr[nx][ny] != 0:
                    continue

                # 기존의 도미노 리스트에서 제거를 할 경우
                # 실패 후 다시 돌아올 경우 n1, n2를 올바르게
                # 선택할 수 없다.
                # 따라서 복사를 통해 새로운 리스트를 생성.
                list1_1 = list1_[:]
                list1_1.remove((n1,n2))
                
                if check(x,y,n1) and check(nx,ny,n2):
                    arr[x][y], arr[nx][ny] = n1, n2
                    dfs(list1_1)
                    arr[x][y], arr[nx][ny] = 0, 0

                # 위와 같은 원리
                # 도미노를 거꾸로 배치하고 싶을 때의 경우.
                list1_2 = list1_[:]
                list1_2.remove((n1,n2))
                if check(x,y,n2) and check(nx,ny,n1):
                    arr[x][y], arr[nx][ny] = n2, n1
                    dfs(list1_2)
                    arr[x][y], arr[nx][ny] = 0, 0

    dfs(domino)
    cnt += 1
