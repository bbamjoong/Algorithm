import sys

input = sys.stdin.readline

n = int(input())
word = input()
ans = - (2 ** 31) - 1


def calculate(operand1, operator, operand2):
    if operator == '+':
        return operand1 + operand2

    if operator == '-':
        return operand1 - operand2

    if operator == '*':
        return operand1 * operand2


def dfs(idx, value):
    global ans

    if idx == n - 1:
        ans = max(ans, value);
        return

    '''
    괄호안에 연산자가 1개만 존재 가능함.
    따라서 아래와 같이 2가지의 경우로 나눌 수 있다.
    
    a + b + c
    1. 앞에 괄호
    2. 뒤에 괄호
    '''
    # 앞에 괄호
    if idx + 2 < n:
        dfs(idx + 2, calculate(value, word[idx + 1], int(word[idx + 2])))

    # 뒤에 괄호
    if idx + 4 < n:
        num2 = calculate(int(word[idx + 2]), word[idx + 3], int(word[idx + 4]))

        dfs(idx + 4, calculate(value, word[idx + 1], num2))


dfs(0, int(word[0]))
print(ans)
