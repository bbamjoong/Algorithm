import sys
input = sys.stdin.readline

n = int(input())

a_ = int(n**0.5)
a = a_**2

b_ = int(n**0.5) + 1
b = b_ **2

def solution(n):
    sqrt = n ** (1/2)
    if sqrt % 1 == 0:
        return True
    return False

if n == 1 or n == 2:
    print(4)

elif solution(n):
    print(4*a_ - 4)

elif n - a > a_:
    print(4*b_ - 4)

else:
    print(2*a_ + 2*b_ - 4)

