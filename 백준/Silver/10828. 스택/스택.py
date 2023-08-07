import sys
input = sys.stdin.readline
count = int(input())
s = []

for i in range(count):
    words = input().split()
    
    if words[0] == 'push':
        s.append(words[1])
    
    elif words[0] == 'pop':
        if s: print(s.pop())
        else: print(-1)
    
    elif words[0] == 'size':
        print(len(s))
    
    elif words[0] == 'empty':
        if s: print(0)
        else : print(1)
    
    elif words[0] == 'top':
        if s : print(s[-1])
        else : print(-1)