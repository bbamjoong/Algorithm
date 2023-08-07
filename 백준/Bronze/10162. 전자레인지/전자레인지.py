import sys
input = sys.stdin.readline

t=int(input().strip())
a = b = c = 0
if t%10 != 0:
    print(-1)
else:
    a = t // 300
    b = (t%300)//60
    c = ((t%300)%60)//10
    print(a, b, c)