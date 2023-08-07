import sys
input = sys.stdin.readline
from itertools import combinations
def gcd(a,b):
    while b>0:
        a,b = b,a%b
    return a

t = int(input())
for i in range(t):
    a, *li = map(int, input().split())
    sum_gcd = 0
    combs = combinations(li,2)
    for j in combs:
        sum_gcd += gcd(j[0], j[1])
    print(sum_gcd)