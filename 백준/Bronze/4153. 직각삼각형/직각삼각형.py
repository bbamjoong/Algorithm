import sys
input = sys.stdin.readline

while True:
    a, b, c = map(int, input().split())
    if a == 0 and b == 0 and c == 0:
        break
    
    mx = max(a,b,c)

    if mx == c:
        if a**2 + b **2 == c**2:
            print('right')
            continue
    
    if mx == b:
        if a**2 + c **2 == b**2:
            print('right')
            continue

    if mx == a:
        if c**2 + b **2 == a**2:
            print('right')
            continue

    print('wrong')