import sys
input = sys.stdin.readline

n, k = map(int, input().split())

start = 0
num = 1
nine = 9

while k > nine * num:
    k = k - (num * nine)
    start += nine
    nine *= 10
    num += 1

# k값이 포함된 숫자
res = (start+1) + (k-1) // num

if res > n:
    print(-1)
else:
    print(str(res)[(k-1)%num])