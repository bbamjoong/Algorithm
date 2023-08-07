import sys
input = sys.stdin.readline

# 주어진 조건에서 c를 구한다.
def solve(x, y, w):
    h1 = (x**2-w**2)**0.5
    h2 = (y**2-w**2)**0.5    
    c = h1*h2 / (h1+h2)
    return c

x,y,c = map(float, input().split())

# 삼각형의 성질에 의해 대각선은 다른 두 변보다 길어야한다.
# 따라서 아랫변은 min(x,y)보다 작은 값이어야 하는 것이다.
start, end = 0, min(x,y)
ans = 0

# 오차가 0.001이하일 때 답이 성립한다. >=부등호가 들어가도 정답처리는 된다.
while end - start > 0.001:
    mid = (start + end) / 2
    
    # 함수를 통해 구한 c값이 문제 조건의 c보다 크다면 아랫변의 길이를 늘려서 우리가 구할 c값을 작게 만들어야 한다.
    if solve(x,y,mid) >= c:
        ans = mid
        start = mid+0.001

    # 정답이라면 mid를 출력한다.
    elif solve(x,y,mid) == c:
        print(mid)  
        exit()

    # 함수를 통해 구한 c값이 문제 조건의 c보다 작다면 아랫변의 길이를 줄여서 우리가 구할 c값을 크게 만들어야 한다.    
    else:
        end = mid-0.001

print(ans)
