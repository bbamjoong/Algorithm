import sys
input = sys.stdin.readline

n = int(input())

li1 = [list(map(int, input().split())) for _ in range(n)]

ans1, ans2, ans3 = 0, 0, 0

num = 0
def check(arr):
    global num
    num = arr[0][0]
    for i in range(len(arr)):
        for j in range(len(arr)):
            if arr[i][j] != num:
                return False
    return True

def dfs(arr):
    global num, ans1, ans2, ans3
    if check(arr) == True:
        if num == -1 :
            ans1 += 1
        elif num == 0 :
            ans2 += 1
        else:
            ans3 += 1

    else:
        if len(arr) == 3:
            for i in range(0, 3):
                for j in range(0, 3):
                    if arr[i][j] == -1:
                        ans1 += 1
                    elif arr[i][j] == 0:
                        ans2 += 1
                    else:
                        ans3 += 1
        else:            
            for i in range(0, len(arr), 3):
                for j in range(0, len(arr), 3):
                    dfs([row[i:i+3] for row in arr[j:j+3]])

dfs(li1)

print(ans1)
print(ans2)
print(ans3)