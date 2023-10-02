import sys
input = sys.stdin.readline

a, b = input().split()

ans = 1e9
for i in range(len(b) - len(a) + 1):
    cnt = 0
    for j in range(len(a)):
        if a[j] != b[i+j]:
            cnt += 1

    ans = min(ans, cnt)

print(ans)