import sys
input = sys.stdin.readline

x1, y1, x2, y2, x3, y3 = map(int, input().split())

if ((x1 - x2) * (y1 - y3) == (y1 - y2) * (x1 - x3)):
    print(-1.0)

else:
    len_12 = ((x1-x2)**2 + (y1-y2)**2)**0.5
    len_31 = ((x1-x3)**2 + (y1-y3)**2)**0.5
    len_23 = ((x2-x3)**2 + (y2-y3)**2)**0.5

    subset = [len_12 + len_31, len_12 + len_23, len_31 + len_23]

    ans = max(subset) - min(subset)
    print(2 * ans)