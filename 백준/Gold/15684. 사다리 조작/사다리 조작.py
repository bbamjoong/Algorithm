import sys
input = sys.stdin.readline

n, m, h = map(int, input().split())

graph = [[0] * n for _ in range(h)]

# 사다리왼쪽1, 오른쪽-1
for _ in range(m):
    a, b = map(int, input().split())
    graph[a-1][b-1] = 1
    graph[a-1][b] = -1

# i번째 사다리에서 시작해서 i번째 사다리에 도착하는지?
def check():
    global n, h
    for i in range(n-1):
        x, y = 0, i
        while x <= h-1:
            if graph[x][y] == 1:
                x+=1
                y+=1
            
            elif graph[x][y] == -1:
                x+=1
                y-=1

            else:
                x+=1

        if y != i:
            return False
    return True


ans = sys.maxsize
def radder(depth,x,y):
    global n, ans

    # 길이 3 넘으면 return
    if depth > 3:
        return
    # depth가 현재 답 이상일 경우 return(가지치기)
    if ans <= depth:
        return
    # check시 답 갱신 / 답이 1이면 바로 종료(가지치기)
    if check():
        ans = min(ans, depth)
        if ans == 1:
            print(1)
            exit()
        return

    # 사다리를 두는데 모든 경우의수를 체크한다. 하지만 위쪽 사다리를 점검 후 아래쪽 사다리를 점검중인데 굳이 위쪽에 사다리를 둘 필요가 없다. 중복되기 때문
    for i in range(x,h):
        # 같은 줄의 사다리를 검사할 때 앞쪽을 중복해서 체크할 필요는 없다.
        k = y if i == x else 0
        for j in range(k, n-1):
            if graph[i][j] == 0 and graph[i][j+1] == 0:                
                graph[i][j] = 1
                graph[i][j+1] = -1
                radder(depth+1,i,j+2)
                graph[i][j] = 0
                graph[i][j+1] = 0
                
if m == 0:
    print(0)
else:
    radder(0,0,0)
    if ans > 3:
        print(-1)
    else:
        print(ans)