import sys
input = sys.stdin.readline

n, k = map(int, input().split())

set_word = []

for i in range(n):
    a = input().strip()
    set_word.append(set(list(a)))

learn = [0] * 26

for i in 'antic':
    learn[ord(i) - 97] = 1

ans = 0

def dfs(idx, cnt):
    global ans

    if cnt == k-5:
        res=0
        for word in set_word:
            check = True
            for w in word:
                if not learn[ord(w) - 97]:
                    check = False
                    break

            if check:
                res+=1
        ans = max(ans, res)
        return
    
    for i in range(idx, 26):
        if not learn[i]:
            learn[i] = 1
            dfs(i, cnt+1)
            learn[i] = 0

if k < 5:
    print(0)
elif k == 26:
    print(n)
else:
    dfs(0,0)
    print(ans)