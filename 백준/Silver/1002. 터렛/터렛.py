import sys
input = sys.stdin.readline

n = int(input())

for _ in range(n):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())

    # 두 원 중심 사이의 거리
    d = ((x1-x2)**2 + (y1-y2)**2)**0.5

    if d == 0 and r1 == r2 : # 두 원이 같은 원일 경우
        print(-1)

    elif abs(r1-r2) == d or r1 + r2 == d:  # 내접, 외접
        print(1)

    elif abs(r1-r2) < d < (r1+r2) :  # 서로 다른 두 점에서 만남
        print(2)

    else:
        print(0)