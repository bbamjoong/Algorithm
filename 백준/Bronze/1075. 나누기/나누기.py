import sys
input = sys.stdin.readline

n = int(input())
f = int(input())

front = n // 100
ans = front * 100

while ans % f != 0:
    ans += 1
print(str(ans)[-2:])