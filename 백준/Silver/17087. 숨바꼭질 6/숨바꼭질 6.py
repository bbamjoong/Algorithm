import sys
n, s = map(int, input().split())
li = list(map(int, input().split()))

def gcd(a,b):
    while b>0:
        a,b=b,a%b
    return a

distance = []
for i in li:
    distance.append(abs(i - s))

ans = distance[0]
for i in range(1,n):
    ans = gcd(distance[i], ans)

print(ans)