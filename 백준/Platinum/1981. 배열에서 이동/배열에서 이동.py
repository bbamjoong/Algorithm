import sys
input = sys.stdin.readline
from collections import deque

def bfs(a, b):
    q = deque()
    q.append([0,0])
    visited = [[False] * n for _ in range(n)]
    visited[0][0] = True

    while q:
        x, y = q.popleft()
        # 도착점에 무사히 도착히 True 반환
        if x == n-1 and y == n-1:
            return True
        
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            # 다음 좌표가 그래프를 벗어나면 안된다.
            if nx<0 or nx>=n or ny<0 or ny>=n:
                continue
            # 다음 좌표가 [a,b]에 존재하고 방문하지 않았다면 queue에 원소 추가
            if a<=arr[nx][ny]<=b and visited[nx][ny] == False:
                visited[nx][ny] = True
                q.append([nx,ny])

    # 도착점에 도착하지 못했을 때 False 반환
    return False


if __name__ == "__main__":
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]

    dx, dy = [0,0,-1,1], [-1,1,0,0]

    # min_arr : 그래프에서 가장 작은 값, max_arr : 그래프에서 가장 큰 값
    min_arr = sys.maxsize
    max_arr = 0

    for i in range(n):
        for j in range(n):
            min_arr = min(min_arr, arr[i][j])
            max_arr = max(max_arr, arr[i][j])

    # start_arr : 그래프 1행1열의 값(시작점), end_arr : 그래프 n행n열의 값(도착점)
    start_arr, end_arr = arr[0][0], arr[n-1][n-1]

    
    # k1 ~ k2사이의 크기를 가지는 점들로 구성된 답을 찾는것이 핵심이다.
    # 만약 답이 4라면 0~4 / 1~5 / 2~6등 다양한 답안이 나올것인데 이를 위한 범위를 미리 지정해줄 것이다.

    ## 배열을 모두 순회하는데 O(100*100)이 필요하다.
    ## 최대-최소의 값이 1이라고 할 때 (1,2) ... (199,200)까지 199개의 경우의 수가 존재한다.
    ## 차이가 최대 200까지 발생하기 때문에 배열의 쌍을 구하는 시간 복잡도는 (0 + ... + 199) = 20000이다.
    ## 총 시간 복잡도는 10000 * 20000 = 200,000,000으로 시간초과 발생

    ### 따라서 이진탐색을 통해 횟수를 줄여나갈 것이다.
    ans = 0

    # 답안의 최소 크기는 0 / 최대 크기는 max_arr - min_arr이다.
    start = 0
    end = max_arr - min_arr
    
    # 답안의 크기를 기준으로 이분탐색
    while start <= end:
        mid = (start + end)//2
        k = False

        # 그래프의 최솟값부터 최댓값까지 탐색을 진행한다
        # 시작점, 도착점이 [i,i+mid]의 범위 내에 존재할 경우 탐색을 진행한다.
        for i in range(min_arr,max_arr+1):
            if i + mid > max_arr:
                continue
            if not (i <= start_arr <= i + mid and i <= end_arr <= i + mid):
                continue
            elif bfs(i, i+mid) == True:
                k = True
                break

        # 답이 존재할 때 -> 더 작은 답안이 존재할 수도 있으므로
        if k == True:
            ans = mid
            end = mid - 1
        
        # 답이 존재하지 않으면 답안의 크기가 더 커져야 하므로
        elif k == False:
            start = mid + 1

    print(ans)