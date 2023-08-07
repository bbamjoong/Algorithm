import sys
input = sys.stdin.readline

n, c = map(int, input().split())

arr = [int(input()) for _ in range(n)]
arr.sort()
# 가장 인접한 두 공유기 사이의 거리를 기준으로 이분탐색
start, end = 1, arr[-1] - arr[0]
ans = 0

while start <= end:
    mid = (start + end) // 2
    house = arr[0]
    cnt = 1
    a = sys.maxsize
    for i in range(1,n):
        if arr[i] - house >= mid:
            cnt += 1
            a = min(a, arr[i] - house)
            house = arr[i]
            
    # cnt가 c초과일 때, 거리를 더 키워 cnt의 개수를 줄여야 한다.
    # cnt가 c와 같더라도, 거리가 더 클 수 있기 때문에 탐색을 해준다.
    if cnt >= c:
        start = mid+1
        ans = max(ans, a)
    # cnt < c일 경우 / cnt의 개수를 c와 맞추기 위해 거리를 더 줄여 탐색을 해준다.
    else:
        end = mid-1

print(ans)