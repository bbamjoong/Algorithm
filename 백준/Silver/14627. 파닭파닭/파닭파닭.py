import sys
input = sys.stdin.readline

s, c = map(int, input().split())

pa = [int(input()) for _ in range(s)]

start = 1
end = max(pa)


while start <= end:
    mid = (start + end) // 2

    cnt = sum(i//mid for i in pa)

    if cnt >= c:
        start = mid + 1

    else:
        end = mid - 1

print(sum(pa) - c*end)