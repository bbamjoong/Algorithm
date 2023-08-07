import sys
input=sys.stdin.readline

n = int(input().strip())

for _ in range(n):
    a,b,c = map(int, input().split())
    if b-c < a:
        print('do not advertise')
    elif b-c == a:
        print('does not matter')
    else:
        print('advertise')
