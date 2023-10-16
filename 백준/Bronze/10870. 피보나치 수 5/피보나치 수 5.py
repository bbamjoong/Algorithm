import sys
input = sys.stdin.readline

n = int(input())
    
def fibo(n):
    if n > 1:
        return fibo(n-1) + fibo(n-2)
    elif n == 1:
        return 1
    elif n == 0:
        return 0
    
print(fibo(n))