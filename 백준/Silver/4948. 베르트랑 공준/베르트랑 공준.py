import sys
input = sys.stdin.readline

n = 123456*2

prime = [False, False] + [True] * (n-1)

for i in range(2, int(n**0.5) + 1):
    if prime[i]:
        for j in range(2*i, n+1, i):
            prime[j] = False

while True:
    n = int(input())

    if n == 0:
        break

    cnt = 0

    for i in range(n+1, 2*n+1):
        if prime[i]:
            cnt += 1

    print(cnt)