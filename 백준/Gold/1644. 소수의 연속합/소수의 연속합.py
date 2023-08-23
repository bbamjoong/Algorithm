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
sm = 0
l = 0
r = 0

while True:
    if sm == n:
        ans += 1

    if sm > n:
        sm -= prime[l]
        l += 1

    elif r == len(prime):
        break

    else:
        sm += prime[r]
        r += 1

print(ans)