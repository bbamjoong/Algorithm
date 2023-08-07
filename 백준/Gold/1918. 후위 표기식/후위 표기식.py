import sys
input = sys.stdin.readline
word = list(input())
stack = []
res = []

for i in word:
    if i.isalpha():
        res.append(i)
    else:
        if i == '(':
            stack.append(i)
        elif i in '+-':
            while stack and stack[-1] != '(':
                res.append(stack.pop())
            stack.append(i)    
        elif i in '*/':
            while stack and stack[-1] in '*/':
                res.append(stack.pop())
            stack.append(i)
        elif i == ')':
            while stack and stack[-1] != '(':
                res.append(stack.pop())
            stack.pop()

while stack:
    res.append(stack.pop())

print(''.join(res))