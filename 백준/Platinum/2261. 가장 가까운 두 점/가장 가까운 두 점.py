import sys
input = sys.stdin.readline

n = int(input())

points = []

for _ in range(n):
    x, y = map(int, input().split())
    points.append((x, y))

# x축 기준으로 정렬
points.sort()


# 주어진 범위 내에서 최소 거리 계산
def dist_(start, end):
    min_dist = int(1e10)
    for i in range(start, end):
        for j in range(i+1, end+1):
            dist = (points[i][0] - points[j][0])**2 + (points[i][1] - points[j][1])**2
            min_dist = min(min_dist, dist)
    return min_dist


def find(start, end):
    size = end - start

    # 주어진 범위 내에서 점이 1개 or 2개일 경우 거리 계산
    if size < 3:
        return dist_(start, end)
    
    # 이분탐색을 이용하여 구간별로 최소거리 탐색
    mid = (start + end) // 2 

    left_value = find(start, mid)
    right_value = find(mid, end)

    # 해당 구간의 최소거리 정의
    min_dist = min(left_value, right_value)

    # mid에서 인접한 거리까지의 점이 최소 거리가 될 수 있다.
    # 1. 왼쪽, 오른쪽에서 계산한 min_dist미만의 거리만큼 x축으로 떨어져 있는 점 탐색
    # 2. 그 중에서 y축으로 min_dist미만의 거리만큼 떨어져 있는 점 탐색
    check = []
    for i in range(start, end+1):
        if (points[mid][0] - points[i][0])**2 < min_dist:
            check.append(points[i])

    check.sort(key=lambda x : x[1])

    for i in range(len(check)):
        for j in range(i+1, len(check)):
            if (check[i][1] - check[j][1])**2 >= min_dist:
                break
            dist = (check[i][0] - check[j][0])**2 + (check[i][1] - check[j][1])**2
            min_dist = min(dist, min_dist)

    return min_dist

print(find(0,n-1))