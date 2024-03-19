import sys
input = sys.stdin.readline

while True:
    li = list(map(int, input().split()))

    if li[0] == 0:
        break

    n = 1
    for i in range(li[0]):
        splitting_factor = li[2 * i + 1]
        p = li[2 * i + 2]
        n = n * splitting_factor - p
    print(n)