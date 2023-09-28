import sys
input = sys.stdin.readline


# 유클리드 호제법
def gcd(x,y):
    while y>0:
        x, y = y, x%y
    return x


n = int(input())
arr_a = list(map(int, input().split()))


n = int(input())
arr_b = list(map(int, input().split()))


A = 1
for i in arr_a:
    A *= i

B = 1
for i in arr_b:
    B *= i

ans = gcd(A,B)

if len(str(ans)) > 9:
    print(str(ans)[-9:])

else:
    print(ans)