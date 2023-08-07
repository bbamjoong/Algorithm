import sys
input = sys.stdin.readline
word = input().strip()
li = []
for i in range(len(word)):
    li.append(word[i:len(word)])

for i in sorted(li):
    print(i)