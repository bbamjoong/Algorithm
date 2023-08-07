import sys
input = sys.stdin.readline

row, col = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(row)]
ans = ''

# 행이 홀수개일 때
if row %2 == 1:
    for i in range(row):
        if i % 2 == 0:
            ans += 'R'* (col-1)
        else:
            ans += 'L' * (col-1)
        ans += 'D'
    print(ans[:-1])
    exit()

# 열이 홀수개일 때
if col % 2 == 1:
    for i in range(col):
        if i % 2 == 0:
            ans += 'D'* (row-1)
        else:
            ans += 'U' * (row-1)
        ans += 'R'
    print(ans[:-1])
    exit()    

# 행, 열이 모두 짝수개 일 때 /
# 한군데 이상을 방문하지 못한다. 이 때 한 군데만 방문하지 못한다면 가장 좋은 방법일 것이다.
# 만약 방문하지 못할 예정인 한 곳 ->  행의 index + 열의 index가 홀수 : 한 곳만 방문하지 않을 수 있다.
# 만약 방문하지 못할 예정인 한 곳 ->  행의 index + 열의 index가 짝수 : 방문하지 못할 예정이었던 한 곳 + 최소 한개 즉, 총 두 군데 이상을 방문하지 못하게 된다.

# 따라서 방문하지 못할 예정인 곳(blank로 칭함)은 행의 index + 열의 index가 홀수인 경우이다.

# blank의 위치 정보 저장
blank_row, blank_col = 0, 0
blank = sys.maxsize
for i in range(row):
    for j in range(col):
        if (i + j) % 2 == 1:
            blank = min(board[i][j], blank)
            if blank == board[i][j]:
                blank_row, blank_col = i, j

# 좌표가 0열에서 시작했을 때 0~1열에 blank가 있을 떄와, blank가 없을때는 움직이는 형태를 달리한다.
# 만약 blank가 없다면 -> UUURDDD or DDDRUUU 같은 형태가 나타날 것이고
# 만약 blank가 있다면 -> blank를 피해서 이동해야한다.
# 좌표가 0열, 2열, 4열 ...에 최초 도착 시 이를 파악해서 어떤 경로로 움직일 지 결정한다.
check = 0

# 첫 좌표를 0행 -1열로 둔다. 아래쪽 코드에 ans+='R'이 있기 때문
x, y = 0, -1
while len(ans) <= row * col - 2:
    # 한 사이클이 끝날 때 마다 R을 추가하여 다음 열(0, 2, 4...)로 이동시켜준다.
    ans += 'R'
    y+=1

    # 만약 check, check+1 열에 blank가 있다면
    if check//2 == blank_col//2:
        #한 사이클 동안 row*2 -2번 움직이게 된다.
        for i in range(row*2-2):
            # 만약 다음 행선지에 blank가 있다면 무조건 아래로 움직인다.
            if (x == blank_row and y+1 == blank_col) or (x == blank_row and y-1 == blank_col):
                ans += 'D'
                x += 1
            else:
                # (0,2,4...)열에 처음 도착했을 때 무조건 R 방향으로 움직인다.
                if i == 0:
                    ans += 'R'
                    y+=1

                # 이전 움직인 방향이 D이고, 현재 위치의 column이 짝수라면 R 방향으로 움직인다.
                elif ans[-1] == 'D' and y % 2 == 0:
                    ans += 'R'
                    y+=1
                
                # 이전 움직인 방향이 D이고, 현재 위치의 column이 홀수라면 L 방향으로 움직인다.
                elif ans[-1] == 'D' and y % 2 != 0:
                    ans += 'L'
                    y-=1
                
                # 이전 움직인 방향이 L 또는 R이고 현재 위치가 board의 맨 아래쪽이 아니라면 D 방향으로 움직인다.
                elif ans[-1] == 'L' or ans[-1] == 'R' and x != row-1:
                    ans += 'D'
                    x+=1
                # 이전 움직인 방향이 L 또는 R이고 현재 위치가 board의 맨 아래쪽이라면?
                # 이미 한 사이클이 끝난 상태이기때문에 while문의 첫번째로 돌아가 좌표에 R을 추가하게 될 것이다.
    
        # 현재 check, check+1 열의 좌표는 모두 방문했기에 check에 2를 더해준다.
        check += 2

    # 만약 check, check+1 열에 blank가 없다면?
    else:
        # 만약 현재 좌표가 가장 아래쪽에 있다면
        # U.... -> R -> D....
        if x == row-1:
            ans += 'U' * (row-1)
            ans += 'R'
            ans += 'D' * (row-1)
            y+=1
        # 만약 현재 좌표가 가장 위쪽에 있다면
        # D.... -> R -> U....    
        else:
            ans += 'D' * (row-1)
            ans += 'R'
            ans += 'U' * (row-1)
            y+=1            

        # 현재 check, check+1 열의 좌표는 모두 방문했기에 check에 2를 더해준다.
        check += 2

# 맨 처음에 불필요한 R이 추가되어 있으므로 제외하고 출력한다.
print(ans[1:])