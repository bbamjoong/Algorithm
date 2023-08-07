import sys
input = sys.stdin.readline

s = input().strip()
t = input().strip()

for i in range(len(t)-1,len(s)-1,-1):
    if t[i] == 'B':
        t = t[:i][::-1]
    else:
        t = t[:i]

if s == t:
    print(1)
else:
    print(0)