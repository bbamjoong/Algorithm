import sys
input = sys.stdin.readline
n = int(input())
res=''
if n == 0:
    print(0)
else:
    while n:
        if n%(-2):
            res += '1'
            n = (n-1)//-2
        else:
            res += '0'
            n //= -2
print(res[::-1])