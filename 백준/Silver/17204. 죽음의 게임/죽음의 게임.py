import sys
input = sys.stdin.readline

#참여하는 사람 수, 보성이의 번호
n, k = map(int, input().split())

li = [int(input()) for _ in range(n)]

gamer = 0 # 지목하는 사람
cnt = 0 # 지목 횟수
finished = False

for i in range(n):
    target = li[gamer] # 지목 당한 사람
    cnt += 1

    if target == k: # 보성이가 타겟이라면
        print(cnt)
        finished = True
        break

    gamer = target

if not finished:
    print(-1)