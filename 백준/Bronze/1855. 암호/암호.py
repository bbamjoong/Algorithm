import sys
input = sys.stdin.readline


n = int(input())
s = input().strip()

arr = []
for i in range(0, len(s), n):
    arr.append(list(s[i:i+n]))

for i in range(len(arr)):
    if i % 2 != 0:
        data = list(reversed(arr[i]))
        arr[i] = data

res = ''
for j in range(n):
    for i in range(len(arr)):
        res += arr[i][j]
print(res)