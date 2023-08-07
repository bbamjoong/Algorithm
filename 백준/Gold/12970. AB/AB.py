import sys
input = sys.stdin.readline

n, k = map(int, input().split())

MAX = 0
for i in range(1, n//2+1):
    MAX = max(MAX, i*(n-i))

if k > MAX:
    print(-1)
    exit()


def check(word):
    cnt = 0
    for i in range(len(word)-1):
        if word[i] == 'A':
            for j in range(i+1,len(word)):
                if word[j] == 'B':
                    cnt+=1
    return cnt


word = ['B' for _ in range(n)]

for i in range(n):
    word[i] = 'A'
    if check(word) == k:
        break
    elif check(word) > k:
        word[i] = 'B'

print(''.join(word))