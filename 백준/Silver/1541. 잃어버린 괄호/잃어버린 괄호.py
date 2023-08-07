import sys
input = sys.stdin.readline

arr = input().split('-')

ans = 0
for i in arr[0].split('+'):
    ans += int(i)

for i in arr[1:]:
    for j in i.split('+'):
        ans -= int(j)

print(ans)