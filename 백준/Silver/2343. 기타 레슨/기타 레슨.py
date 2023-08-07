import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))

start = max(arr)
end = sum(arr)

while start <= end:
    mid = (start + end) // 2

    cnt, sm = 0, 0
    for i in range(n):
        if sm + arr[i] <= mid:
            sm += arr[i]
        elif sm + arr[i] > mid:
            cnt += 1
            sm = arr[i]
    if sm:
        cnt += 1

    if cnt > m:
        start = mid + 1
    elif cnt <= m:
        end = mid - 1

print(start)