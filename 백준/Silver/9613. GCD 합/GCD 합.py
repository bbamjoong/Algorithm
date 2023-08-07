import sys
input = sys.stdin.readline

def gcd(a,b):
    while b>0:
        a,b = b,a%b
    return a

t = int(input())

for i in range(t):
    a, *li = map(int, input().split())

    sum_gcd = 0
    for i in range(len(li)-1):
        for j in range(i+1, len(li)):
            sum_gcd += gcd(li[i], li[j])

    print(sum_gcd)
