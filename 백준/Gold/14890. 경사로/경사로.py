import sys
input = sys.stdin.readline

n, l = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

def check(arr):
    visited = [False for _ in range(n)]
    for i in range(n-1):

        # 현재칸, 다음칸의 높이차가 1 초과일 시 False 반환
        if abs(arr[i] - arr[i+1]) > 1:
            return False
        
        # 현재칸, 다음칸의 높이가 같으면 일단 보류
        if arr[i] == arr[i+1]:
            continue
        
        # 현재칸의 높이가 다음칸의 높이보다 클 때
        elif arr[i] > arr[i+1]:

            # 경사로의 길이만큼 다음칸을 검사할 것
            for j in range(i+1,i+l+1):

                # 다음 칸들이 0~n-1의 index를 가질 때만 검사해야한다.
                if 0 <= j < n:

                    # 경사로를 설치할 칸의 높이가 다르면 False 반환
                    if arr[j] != arr[i+1]:
                        return False
                    
                    # 이미 경사로를 설치한 칸이라면 False 반환
                    if visited[j] == True:
                        return False
                    
                    # 조건 만족 시 경사로 설치
                    visited[j] = True
                
                # index가 범위를 벗어날 경우
                else:
                    return False
        # 현재칸의 높이가 다음칸의 높이보다 작을 때
        elif arr[i] < arr[i+1]:

            # i부터 거꾸로 l길이 만큼의 경사로를 설치해야한다.
            for j in range(i, i-l, -1):
                if 0 <= j < n:
                    # 경사로를 설치할 칸의 높이가 다르면 False 반환
                    if arr[j] != arr[i]:
                        return False
                    
                    if visited[j] == True:
                        return False
                    
                    visited[j] = True

                else:
                    return False
    return True
          
cnt = 0

# 행 검사
for i in range(n):
    arr = graph[i]
    if check(arr) == True:
        cnt += 1

# 열 검사
for j in range(n):
    arr = []
    for i in range(n):
        arr.append(graph[i][j])
    if check(arr) == True:
        cnt += 1

print(cnt)