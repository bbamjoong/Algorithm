import sys

input = sys.stdin.readline

n = int(input())

li = []
for _ in range(n):
    li.append(str(input().rstrip()))

for i in range(1, len(li[0]) + 1):  # 문자열의 길이
    res = set()

    for j in range(n):
        if res.__contains__(li[j][-i:]):
            break
        else:
            res.add(li[j][-i:])

    if len(res) == n:
        print(i)
        break
