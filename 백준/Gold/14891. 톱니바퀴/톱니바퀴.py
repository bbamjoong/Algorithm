import sys
input = sys.stdin.readline
from collections import deque

arr = []
for i in range(4):
    arr.append(deque(list(map(int, input().strip()))))

same = [[0,0] for _ in range(4)]


k = int(input())
turn = [list(map(int, input().split())) for _ in range(k)]

def change(num, turn_dir, vector):
    if num < 0 or num > 3:
        return

    if vector == 0:
        if num == 0:
            arr[num].rotate(turn_dir)
            if same[num][1] == 1:
                change(num+1, 0, 1)
            else:
                change(num+1,-turn_dir, 1)

        elif num == 3:
            arr[num].rotate(turn_dir)
            if same[num][0] == 1:
                change(num-1, 0, 1)
            else:
                change(num-1,-turn_dir, -1)

        elif num == 1 or num == 2:
            arr[num].rotate(turn_dir)
            if same[num][0] == 1:
                change(num-1,0,-1)
            else:
                change(num-1,-turn_dir,-1)
            
            if same[num][1] == 1:
                change(num+1,0,1)
            else:
                change(num+1,-turn_dir,1)
        
    elif vector == 1:
        arr[num].rotate(turn_dir)

        if same[num][1] == 1:
            change(num+1,0,1)
        elif same[num][1] == -1:
            change(num+1,-turn_dir,1)

    elif vector == -1:
        arr[num].rotate(turn_dir)

        if same[num][0] == 1:
            change(num-1,0,-1)
        elif same[num][0] == -1:
            change(num-1,-turn_dir,-1)
    

for num, turn_dir in turn:
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