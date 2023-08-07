import sys
input = sys.stdin.readline

str = list(input().rstrip())
stack = []

for i in range(int(input())):
    key = list(input().split())

    if key[0]=='L':
        if str:
            stack.append(str.pop())
    
    elif key[0]=='D':
        if stack:
            str.append(stack.pop())
    
    elif key[0] == 'B':
        if str:
            str.pop()

    elif key[0] == 'P':
        str.append(key[1])

str.extend(reversed(stack))
for i in str:
    print(i, end='')