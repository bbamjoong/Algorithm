import sys
input = sys.stdin.readline

n = int(input())

ans = 0
gomgom = set()

for i in range(n):
    word = input().rstrip()

    if word == 'ENTER':
        gomgom.clear()

    else:
        if word not in gomgom:
            gomgom.add(word)
            ans += 1

print(ans)