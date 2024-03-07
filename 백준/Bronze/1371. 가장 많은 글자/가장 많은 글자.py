import sys
paragraph = sys.stdin.read()

li = [0] * 26

for alpha in paragraph:
    if alpha.islower():
        li[ord(alpha) - ord('a')] += 1

for i in range(26):
    if li[i] == max(li):
        print(chr(97+i), end='')