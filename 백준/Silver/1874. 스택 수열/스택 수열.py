n = int(input())

stack = []
ans = []
k = 1
cnt = 1

for i in range(n):
    num = int(input())
    while cnt <= num:
        stack.append(cnt)
        ans.append('+')
        cnt +=1

    if stack[-1] > num:
        k = 0

    if stack[-1] == num:
        stack.pop()
        ans.append('-')

if k == 0:
    print('NO')
else:
    for i in ans:
        print(i)
