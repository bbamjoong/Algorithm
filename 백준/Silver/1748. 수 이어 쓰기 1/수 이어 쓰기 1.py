import sys
input = sys.stdin.readline
import sys
n = int(input())
l = len(str(n))
ans = n * l
for i in range(1,l):
    ans -= 10**i -1

print(ans)