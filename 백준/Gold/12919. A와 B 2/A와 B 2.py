import sys
input = sys.stdin.readline

s = input().strip()
t = input().strip()

def dfs(t):
    if s == t:
        print(1)
        exit()

    if len(t) == 0:
        return
    
    if t[-1] == 'A':
        dfs(t[:-1])
    
    if t[0] == 'B':
        dfs(t[1:][::-1])

dfs(t)
print(0)