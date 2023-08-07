import sys
input = sys.stdin.readline

n = int(input())

li1 = [list(map(int, input().split())) for _ in range(n)]

# ans1 : -1, ans2: 0, ans3 : 1
ans1, ans2, ans3 = 0, 0, 0

num = 0
def check(arr):
    global num
    # num : 주어진 행렬에서 matrix[0][0] 즉, 가장 왼쪽 위에 위치한 숫자
    num = arr[0][0]
    # 주어진 행렬이 matrix[0][0]과 다를 경우 False 반환
    for i in range(len(arr)):
        for j in range(len(arr)):
            if arr[i][j] != num:
                return False
    # 주어진 행렬이 모두 같은 숫자로 이루어져있다면 False 반환
    return True

def dfs(arr):
    global num, ans1, ans2, ans3

    # 행렬이 모두 같은 숫자로 이루어져있다면 개수 최신화
    if check(arr) == True:
        if num == -1 :
            ans1 += 1
        elif num == 0 :
            ans2 += 1
        else:
            ans3 += 1
    # 행렬이 모두 같은 숫자로 이루어져있지 않다면
    else:
        # matrix의 크기가 3x3일 경우 개수 최신화
        if len(arr) == 3:
            for i in range(0, 3):
                for j in range(0, 3):
                    if arr[i][j] == -1:
                        ans1 += 1
                    elif arr[i][j] == 0:
                        ans2 += 1
                    else:
                        ans3 += 1
        # matrix의 크기가 3x3보다 클 경우
        else:            
            # 행, 열을 3구간으로 나눈 후 dfs 탐색
            for i in range(0, len(arr), 3):
                for j in range(0, len(arr), 3):
                    dfs([row[i:i+3] for row in arr[j:j+3]])

dfs(li1)

print(ans1)
print(ans2)
print(ans3)
