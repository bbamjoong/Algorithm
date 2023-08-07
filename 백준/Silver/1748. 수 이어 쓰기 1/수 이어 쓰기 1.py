import sys
input = sys.stdin.readline
import sys
n = int(input())
l = len(str(n))

if len(str(n))==1:
    print(n)
else:
    cnt = 0
    for i in range(1,l):
        cnt += i * 9 * (10 ** (i-1))
    cnt += (n+1-10**(l-1)) * l

    print(cnt)
