import sys
input = sys.stdin.readline

a = input().rstrip()
ans = 0
stack = 0

for i in a.replace('()', '@'):
    if i == '(':
        stack += 1
    elif i == '@':
        ans += stack
    else:
        stack -= 1
        ans += 1

print(ans)