import sys
input = sys.stdin.readline

n = int(input())
li = list(map(int, input().split()))
cost = int(input())

start, end = 0, max(li)

while start <= end:
    mid = (start + end) // 2 # 상한선임.
    amount = 0

    for i in li:
        if i > mid:
            amount += mid
        else:
            amount += i

    if amount > cost:
        end = mid - 1
    # 최댓값을 구해야하니 upper bound
    elif amount == cost:
        start = mid + 1
    elif amount < cost:
        start = mid + 1

print(end)