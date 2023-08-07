import sys
input=sys.stdin.readline
n = int(input())
arr = []
for i in range(n):
    arr.append(list(map(str, input().strip())))
    arr[i].append(0)
arr.append([0] * (n+1))

def check(li):
    n=len(li)-1
    max_cnt = 1
    for i in range(n):
        cnt = 1
        for j in range(n):
            if arr[i][j] == arr[i][j+1]:
                cnt += 1
            else:
                cnt = 1
            max_cnt = max(max_cnt, cnt)

        cnt = 1
        for j in range(n):
            if arr[j][i] == arr[j+1][i]:
                cnt += 1
            else:
                cnt = 1
            max_cnt = max(max_cnt, cnt)

    return max_cnt

res = 1
for i in range(n):
    for j in range(n):
        arr[i][j], arr[i][j+1] = arr[i][j+1], arr[i][j]
        cnt = check(arr)
        res = max(res, cnt)
        arr[i][j], arr[i][j+1] = arr[i][j+1], arr[i][j]

        arr[i][j], arr[i+1][j] = arr[i+1][j], arr[i][j]
        cnt = check(arr)
        res = max(res, cnt)
        arr[i][j], arr[i+1][j] = arr[i+1][j], arr[i][j]
        if res == n:
            break
    if res==n:
        break
print(res)      