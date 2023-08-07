n = int(input())

for _ in range(n):
    stack = []
    str = input()
    k = 0
    for i in str:
        if i == '(':
            stack.append('(')
        elif i == ')':
            if stack:
                stack.pop()
            else:
                k = 1
                break
    if not stack and k == 0:
        print('YES')
    elif stack or k == 1:
        print('NO')