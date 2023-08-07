import sys
input=sys.stdin.readline
n = int(input())
a = int(input())
cnt  = abs(100-n)
wrong = list(map(int, input().split()))
for num in range(1000000):
    for i in str(num):
        if int(i) in wrong:
            break

    else:
        cnt = min(cnt, len(str(num)) + abs(num - n))

print(cnt)