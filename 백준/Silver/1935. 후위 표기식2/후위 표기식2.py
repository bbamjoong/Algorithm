import sys
input = sys.stdin.readline
n = int(input())
word = list(input().rstrip('\n'))
stack = []
dict = {}
for i in word:
    if i.isalpha() and i not in dict:
        dict[i] = int(input())

for i, v in enumerate(word):
    if v.isalpha():
        word[i] = dict[v]

for i in range(len(word)):
    if type(word[i]) is int:
        stack.append(word[i])
    else:
        b, a = str(stack.pop()), str(stack.pop())
        c = a + word[i] + b
        stack.append(eval(c))

print(f'{stack[0]:.2f}')