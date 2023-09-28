import sys
input = sys.stdin.readline

n = int(input())

word = input().rstrip()
tmp = list(word)

for i in range(n-1):
    word = input().rstrip()

    for i in range(len(word)):
        if word[i] != tmp[i]:
            tmp[i] = "?"

print(''.join(tmp))