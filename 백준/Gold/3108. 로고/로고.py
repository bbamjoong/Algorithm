import sys
input = sys.stdin.readline

def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a
        return
    parent[a] = b

def meet(rectangle1, rectangle2):
    if (rectangle1[1][0] < rectangle2[0][0] or rectangle2[1][0] < rectangle1[0][0] or
        rectangle1[0][1] > rectangle2[1][1] or rectangle2[0][1] > rectangle1[1][1]):
        return False
    elif ((rectangle1[0][0] < rectangle2[0][0] < rectangle2[1][0] < rectangle1[1][0] and
          rectangle1[0][1] < rectangle2[0][1] < rectangle2[1][1] < rectangle1[1][1])
        or
          (rectangle2[0][0] < rectangle1[0][0] < rectangle1[1][0] < rectangle2[1][0] and
          rectangle2[0][1] < rectangle1[0][1] < rectangle1[1][1] < rectangle2[1][1])):
        return False
    
    return True

def containsZero(rectangle):
    # x1, x2, y1, y2중 하나라도 0이어야 함
    if rectangle[0][0] != 0 and rectangle[0][1] != 0 and rectangle[1][0] != 0 and rectangle[1][1] != 0:
        return False
    # 하나라도 0이긴 한데, (0, 0)을 안지날 수도 있음
    # 0을 포함하거나 -> 0에 대칭이기 때문에 x1 * x2  y1 * y2는 <= 0 이다.
    elif rectangle[0][0]*rectangle[1][0] > 0 or rectangle[0][1]*rectangle[1][1] > 0:
        return False
    else:
        return True

n = int(input())
parent = [i for i in range(n)]
rectangles = []

ans = 0

for _ in range(n):
    x1, y1, x2, y2 = map(int, input().split())
    rectangles.append([[x1, y1], [x2, y2]])

for i in range(n-1):
    for j in range(i+1, n):
        if meet(rectangles[i], rectangles[j]):
            union(i, j)

for i in range(n):
    if i == parent[i]:
        ans += 1

for i in range(n):
    if containsZero(rectangles[i]):
        ans -= 1
        break

print(ans)