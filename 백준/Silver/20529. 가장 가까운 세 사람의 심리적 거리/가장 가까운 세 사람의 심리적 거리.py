import sys
input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n = int(input())
    arr = list(map(str, input().rstrip().split()))

    if n > 32:
        print(0)
        continue
    
    ans = 1e09
    for i in range(n):
        for j in range(i+1, n):
            for k in range(j+1, n):
                tmp = 0
                for x in range(4):
                    if arr[i][x] != arr[j][x]:
                        tmp+=1
                    if arr[j][x] != arr[k][x]:
                        tmp+=1
                    if arr[k][x] != arr[i][x]:
                        tmp+=1

                ans = min(tmp, ans)                    

    print(ans)                