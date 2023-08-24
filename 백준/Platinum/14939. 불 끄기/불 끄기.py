import sys
input = sys.stdin.readline

arr = [list(input().strip()) for _ in range(10)]

dx = [0,0,0,-1,1]
dy = [0,-1,1,0,0]

ans = 101 #답은 최대 100개이므로 불가능한 개수인 101로 초기화해주는 것

# 첫째줄 모든경우의수 확인, 아래 행 부터는 위쪽행 같은 열 전구가 켜져있으면 누른다.
for i in range(1<<10):
    # 깊은복사 : deepcopy는 느리다. 슬라이싱을 통한 깊은 복사를 이용한다.
    tmp = [arr[j][:] for j in range(10)]

    cnt = 0

    for j in range(10):
        if i & (1<<j): # 첫째줄의 j번째 스위치를 눌렀다.
            cnt += 1
            # 범위 내의 5방향 전구 불을 바꿔준다.
            for dir in range(5):
                nx, ny = 0 + dx[dir], j + dy[dir]
                if 0 <= nx < 10 and 0 <= ny < 10:
                    if tmp[nx][ny] == "O":
                        tmp[nx][ny] = "#"
                    else:
                        tmp[nx][ny] = "O"

    for x in range(1,10):
        for y in range(10):
            # 윗줄이랑 전구 불이 켜져있으면 스위치 누름
            if tmp[x-1][y] == "O":
                cnt += 1
                for dir in range(5):
                    nx, ny = x + dx[dir], y + dy[dir]
                    if 0 <= nx < 10 and 0 <= ny < 10:
                        if tmp[nx][ny] == "O":
                            tmp[nx][ny] = "#"
                        else:
                            tmp[nx][ny] = "O"                                
    
    # 마지막줄 전구가 전부 꺼져있는지 확인
    bulb_off = True
    for y in range(10):
        if tmp[9][y] == 'O':
            bulb_off = False

    # 전구가 다 꺼져있으면 ans 갱신
    if bulb_off == True:
        ans = min(cnt, ans)

print(ans if ans != 101 else -1)