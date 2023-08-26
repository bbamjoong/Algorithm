import sys
input = sys.stdin.readline

n = int(input())
word = str(input().strip())

h = 0
for i in range(len(word)):
    h += (ord(word[i]) - 96) * (31 ** i)

print(h % 1234567891)