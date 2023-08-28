import sys
input = sys.stdin.readline

while True:
    word = input().rstrip()

    if word == '.':
        break

    stack = []

    for i in range(len(word)):
        if word[i] == '(' or word[i] =='[':
            stack.append(word[i])

        elif word[i] == ')':
            if stack and stack[-1] == '(':
                stack.pop()
            else:
                stack.append(')')
                break
        
        elif word[i] == ']':
            if stack and stack[-1] == '[':
                stack.pop()
            else:
                stack.append(']')
                break

    if stack:
        print('no')
    else:
        print('yes')