import sys
input = sys.stdin.readline

n = int(input())

arr = [False, False] + [True] * (4000000 - 1)
prime = []
for i in range(2, int(n**0.5) + 1):
    if arr[i]:
        for j in range(i*2, 4000001, i):
            arr[j] = False

for i in range(n+1):
    if arr[i] == True:
        prime.append(i)

ans = 0
for i in range(len(prime)):
    sm = 0
    if prime[i] > int(n/2):
        break
    for j in range(i, len(prime)):
        sm += prime[j]
        if sm > n:
            break
        elif sm == n:
            ans += 1
            break

if prime:
    if prime[-1] == n:
        ans += 1

print(ans)