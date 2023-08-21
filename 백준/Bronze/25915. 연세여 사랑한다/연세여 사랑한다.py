import sys
input = sys.stdin.readline

str = input().strip()
str1 = str + 'ILOVEYONSEI'

sm = 0
for i in range(len(str1) - 1):
    sm += abs(ord(str1[i]) - ord(str1[i+1]))

print(sm)