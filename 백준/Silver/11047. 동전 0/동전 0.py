import sys
input = sys.stdin.readline

n, k = map(int, input().split())

value = [int(input().strip()) for _ in range(n)]

cnt = 0
for i in range(n-1,-1,-1):
    cnt += k//value[i]
    k = k%value[i]

print(cnt)