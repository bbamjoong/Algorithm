import sys
input = sys.stdin.readline

def prime(x):
    if x == 0 or x == 1:
        return False
    
    for i in range(2, int(x**0.5)+1):
        if x % i == 0:
            return False
        
    return True

cnt = 0

while True:
    x = int(input())

    if x == 0:
        break

    for i in range(x+1, 2*x+1):
        if prime(i):
            cnt += 1

    print(cnt)
    cnt = 0