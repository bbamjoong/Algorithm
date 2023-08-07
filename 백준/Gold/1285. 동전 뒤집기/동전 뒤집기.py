import sys
input = sys.stdin.readline
n = int(input())
arr = [list(input().strip()) for _ in range(n)]

res = sys.maxsize

# 길이가 n일 때 선택 or 선택하지 않는 경우의 수는 1<<n -1 가지
for num in range(1<<n):
    # deepcopy를 사용할 시 시간이 오래걸림
    # 따라서 2차원 리스트를 슬라이싱을 이용한 깊은복사
    tmp = [arr[i][:] for i in range(n)]

    # ex) 백준 입력 예시에서 / num = 5 -> 1 0 1 이 때 1행 3행을 뒤집을 것인데
    # 1<<0, 1<<1, 1<<2를 해주었을 때 True 출력시 해당 원소 바꿔주기
    for i in range(n):
        if num & (1 << i):
            for j in range(n):
                if tmp[i][j] == 'T':
                    tmp[i][j] = 'H'
                else:
                    tmp[i][j] = 'T'

    ans = 0
    for j in range(n):
        cnt = 0
        for i in range(n):
        # 한 열에서 T의 개수에 따라 n-cnt or cnt를 더해준다.
            if tmp[i][j] == 'T':
                cnt += 1
        if cnt >= n//2 + 1:
            ans += n-cnt
        else:
            ans += cnt
    res = min(ans, res)

print(res)