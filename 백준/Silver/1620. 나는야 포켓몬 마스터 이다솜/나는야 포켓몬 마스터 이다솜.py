import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = [input().strip() for _ in range(n)]

for _ in range(m):
    prob = str(input().strip())

    try:
        number = int(prob)
        print(arr[number - 1])
    except:
        print(arr.index(prob) + 1)