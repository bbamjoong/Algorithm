import sys
input = sys.stdin.readline

while True:
    # 입력
    N = input().rstrip()
    if N == '0':
        break

    res = 1  # 필요한 여백
    for i in N:
        if i == '1':
            res += 2
        elif i == '0':
            res += 4
        else:
            res += 3
        res += 1  # 숫자 사이 여백
    print(res)