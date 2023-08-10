import sys
input = sys.stdin.readline
from collections import deque

# 회전을 이용하기 위해 deque 사용
arr = []
for i in range(4):
    arr.append(deque(list(map(int, input().strip()))))

def change(num, turn_dir, vector):
    if num < 0 or num > 3:
        return

    # 회전
    arr[num].rotate(turn_dir)

    # 처음 입력받아 이동 방향이 없을 때
    if vector == 0:
        # 첫번째 톱니바퀴일 때
        if num == 0:
            # 오른쪽 톱니바퀴와 같은 극이라면
            if same[num][1] == 1:
                # 돌리는 방향 없이 / vector = 1
                change(num+1, 0, 1)
            # 다른 극이라면
            else:
                # 돌리는 방향 -turn_dir / vector = 1
                change(num+1,-turn_dir, 1)

        # 네번째 톱니바퀴일 때
        elif num == 3:
            # 왼쪽 톱니바퀴와 같은 극이라면
            if same[num][0] == 1:
                # 돌리는 방향 없이 / vector = -1
                change(num-1, 0, 1)
            # 다른 극이라면
            else:
                # 돌리는 방향 -turn_dir / vector = -1
                change(num-1,-turn_dir, -1)

        # 두번째, 세번째 톱니바퀴일 때
        elif num == 1 or num == 2:
            # 왼쪽 톱니바퀴 체크
            if same[num][0] == 1:
                change(num-1,0,-1)
            else:
                change(num-1,-turn_dir,-1)
            
            # 오른쪽 톱니바퀴 체크
            if same[num][1] == 1:
                change(num+1,0,1)
            else:
                change(num+1,-turn_dir,1)

    # vector = 1 / 오른쪽 방향으로 나아가며 체크하는 경우
    elif vector == 1:
        if same[num][1] == 1:
            change(num+1,0,1)
        elif same[num][1] == -1:
            change(num+1,-turn_dir,1)
    # vector = -1 / 왼쪽 방향으로 나아가며 체크하는 경우
    elif vector == -1:
        if same[num][0] == 1:
            change(num-1,0,-1)
        elif same[num][0] == -1:
            change(num-1,-turn_dir,-1)


# 어떤 톱니를 돌릴지 입력받음
k = int(input())
turn = [list(map(int, input().split())) for _ in range(k)]

for num, turn_dir in turn:   
    # 톱니가 같은 극 or 다른 극으로 맞물려있는지 확인
    # 같은 극으로 맞물려 있을 경우 1 / 아닐경우 -1
    same = [[0,0] for _ in range(4)]
    for i in range(3):
        if arr[i][2] == arr[i+1][6]:
            same[i][1]=1
            same[i+1][0]=1
        else:
            same[i][1]=-1
            same[i+1][0]=-1
    change(num-1, turn_dir, 0)

score = 0
if arr[0][0] == 1:
    score += 1
if arr[1][0] == 1:
    score += 2
if arr[2][0] == 1:
    score += 4
if arr[3][0] == 1:
    score += 8

print(score)
