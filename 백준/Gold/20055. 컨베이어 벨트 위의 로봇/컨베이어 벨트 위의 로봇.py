import sys
input = sys.stdin.readline
from collections import deque

n, k = map(int, input().split())
belt = deque(map(int, input().split())) ## 벨트
# 로봇 -> 0은 없다, 1은 있다.
robot = deque([0 for _ in range(n)]) # 로봇은 1에서 올리고 n에서 내린다. n+1 못지나니까 최대 n개


# 제일 앞 로봇부터
# 다음 칸 내구도 > 0, 로봇 X일때 이동

cnt = 0;
ans = 0;
while True: #무한루프
    ans += 1 # 단계추가
    belt.rotate(1) # 시계방향으로 1 회전

    robot[-1] = 0
    robot.rotate(1) # 로봇도 회전
    robot[-1] = 0 # 마지막 칸은 로봇 제거
    
    for i in range(n - 2, -1, -1): # 마지막칸 제외하고 로봇 이동할건지 체크
        if robot[i] == 1: # 로봇이 존재하는 칸
            if belt[i+1] > 0 and robot[i+1] == 0: # 로봇 이동조건
                robot[i] = 0;
                robot[i+1] = 1;
                belt[i+1] -= 1; # 내구도 닳음
                
                if belt[i+1] == 0:
                    cnt+=1
                
    # 로봇 옮겼으니까 로봇 올리기
    # 1번칸에 로봇 없고, 내구도 0보다 커야됨
    if robot[0] == 0 and belt[0] > 0 :
        robot[0] = 1; #로봇올림
        belt[0] -= 1;
        
        if belt[0] == 0:
            cnt+=1
            
    if cnt >= k:
        print(ans);
        break;       