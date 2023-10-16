import sys
input = sys.stdin.readline

n, m = map(int, input().split())
word = {}

for _ in range(n):
    a = input().rstrip()
    
    if len(a) < m:
        continue

    else:
        if a in word:
            word[a] += 1
        else:
            word[a] = 1

words = sorted(word.items(), key = lambda x : (-x[1], -len(x[0]), x[0]))

for i in words:
    print(i[0])