import sys
input=sys.stdin.readline

n = int(input().strip())
a1=0
a0=0
for i in range(n):
    a = int(input().strip())
    if a == 1:
        a1+=1
    else:
        a0+=1

if a1>a0:
    print('Junhee is cute!')
else:
    print('Junhee is not cute!')