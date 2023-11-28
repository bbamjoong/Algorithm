import sys
input = sys.stdin.readline

D, H, W = map(int, input().split())

#비율
X = D / ((H ** 2 + W ** 2) ** 0.5)

h = int(H * X)
w = int(W * X)

print(h, w)