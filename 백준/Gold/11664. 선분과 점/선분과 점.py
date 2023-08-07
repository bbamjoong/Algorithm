import sys
input = sys.stdin.readline

x1,y1,z1,x2,y2,z2,x3,y3,z3 = map(int, input().split())

def calc(x1, y1, z1, x2, y2, z2):
    return ((x2-x1)**2 + (y2-y1)**2 + (z2-z1)**2) ** (1/2)


len1 = calc(x1,y1,z1,x3,y3,z3)
len2 = calc(x2,y2,z2,x3,y3,z3)

ans = sys.maxsize
lenm = 0

while True:
    # 선분의 가운데 좌표
    mx, my, mz = (x1 + x2)/2, (y1 + y2)/2, (z1 + z2)/2
    # 선분의 가운데좌표와 떨어진 점의 거리
    lenm = calc(mx,my,mz,x3,y3,z3)

    # lenm과 ans 의 차이가 10^-6 이하라면
    if abs(ans - lenm) <= 0.000001:
        print(ans)
        break

    # 만약 차이가 10^-6초과라면
    ans = min(ans, lenm)

    if len1 <= len2:
        x2,y2,z2 = mx,my,mz
        len2 = lenm

    else:
        x1,y1,z1 = mx,my,mz
        len1 = lenm
