import sys
input = sys.stdin.readline

def fact(n):
    if (n>1):
        return n * fact(n-1)
    else:
        return 1
    
a = int(input())
li = list(str(fact(a))[::-1])
cnt = 0
for i in range(len(li)):
    if li[i] == '0':
        cnt += 1
    else:
        break

print(cnt)
