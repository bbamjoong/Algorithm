import sys
input = sys.stdin.readline

s=input().rstrip()

words = set()

# 시작 point
for i in range(len(s)):
    # 끝 point
    for j in range(i, len(s)):
        words.add(s[i:j+1])

print(len(words))