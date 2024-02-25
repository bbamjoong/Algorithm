import sys
input = sys.stdin.readline

# 1.동, 2.서, 3.북, 4.남
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

n, k = map(int, input().split())
colorArr = [list(map(int, input().split())) for _ in range(n)]
arr = [[[] for _ in range(n)] for _ in range(n)]

horse = []

for i in range(k):
    x, y, d = map(int, input().split())
    horse.append([x-1, y-1, d-1])
    arr[x-1][y-1].append(i)

def changeDir(d):
    if d % 2 == 0:
        d += 1;
        return d;
    d -= 1;
    return d;
    
def move(info):
    global flag
    x, y, d = horse[info]; # 말 정보
    nx = x + dx[d];
    ny = y + dy[d];
    
    # 파란색 처리
    if nx < 0 or ny < 0 or nx >= n or ny >= n or colorArr[nx][ny] == 2: # 방향 안바꿀 때
        d = changeDir(d);
        horse[info][2] = d; # 말 배열에 방향 정보도 변경
        # 이동 방향 다시 설정
        nx = x + dx[d];
        ny = y + dy[d];
        
        if nx < 0 or ny < 0 or nx >= n or ny >= n or colorArr[nx][ny] == 2: # 다시 파란 칸을 만난다면 제자리
            return;
    
    upArr = []
    for idx in range(len(arr[x][y])): # 옮길 말의 위치를 찾고, 그 뒤부터 싸그리 이동시킴
        if arr[x][y][idx] == info: # 같은 말 찾았으면
            upArr = arr[x][y][idx:]; # 엎을 말 배열 저장
            arr[x][y] = arr[x][y][:idx] # 원래의 map에서 말을 빼준다.
            break;
        
    # 빨간색이면 배열 거꾸로        
    if colorArr[nx][ny] == 1:
        upArr = upArr[::-1]
        
    # 말을 이동시켜줄거니까 horse 배열의 정보 변경
    for h in upArr:
        horse[h][0], horse[h][1] = nx, ny
        arr[nx][ny].append(h)
        
    # 만약 4개이상 말이 겹치면 종료해야됨
    if len(arr[nx][ny]) >= 4 :
        flag = True;
        
cnt = 0 # 진행되는 턴

while True:
    cnt += 1
    
    if cnt > 1000:
        print(-1)
        break
    
    flag = False;
    for i in range(k):
        move(i);
        if flag:
            break;
    
    if flag:
        print(cnt);
        break;