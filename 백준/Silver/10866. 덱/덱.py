import sys
input = sys.stdin.readline
count = int(input())
s = []

for i in range(count):
    words = input().split()
    
    if words[0] == 'push_front':
        s.insert(0, int(words[1]))

    elif words[0] == 'push_back':
        s.append(int(words[1]))

    elif words[0] == 'pop_front':
        if s:
            print(s[0])
            s.remove(s[0])
        else: print(-1)
    
    elif words[0] == 'pop_back':
        if s:
            print(s.pop())
        else: print(-1)

    elif words[0] == 'size':
        print(len(s))
    
    elif words[0] == 'empty':
        if s: print(0)
        else : print(1)
    
    elif words[0] == 'front':
        if s : print(s[0])
        else : print(-1)

    else:
        if s : print(s[-1])
        else : print(-1)