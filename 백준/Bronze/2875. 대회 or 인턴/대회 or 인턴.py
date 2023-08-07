import sys
input = sys.stdin.readline

n, m, k = map(int, input().split())

remain = 0
team = m

if 2*m < n:
    remain = n - 2*m

elif 2*m > n:
    if n % 2 == 0:
        remain = m - n/2
        team = n//2
    else:
        remain = m - n/2 + 1
        team = n//2

if remain >= k:
    print(team)

else:
    a1, a2 = divmod(k - remain, 3)
    if a2:
        a1+=1
    
    ans = team - a1
    if ans > 0:
        print(int(ans))
    else:
        print(0)