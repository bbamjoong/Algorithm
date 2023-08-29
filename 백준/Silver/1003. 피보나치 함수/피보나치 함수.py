import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    dp0 = [1, 0]
    dp1 = [0, 1]
    n = int(input())
    if n == 0:
        print(dp0[0], dp1[0])
    elif n == 1:
        print(dp0[1], dp1[1])
    else:
        for i in range(2, n+1):
            dp0.append(dp0[i-1] + dp0[i-2])
            dp1.append(dp1[i-1] + dp1[i-2])
        print(dp0[i], dp1[i])