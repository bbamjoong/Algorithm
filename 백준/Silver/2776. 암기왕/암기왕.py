import sys
input = sys.stdin.readline


def search(start, end):
    global li, num

    while start <= end:
        mid = (start + end) // 2

        if li[mid] == num:
            return 1

        elif li[mid] < num:
            start = mid + 1

        elif li[mid] > num:
            end = mid - 1

    return 0


t = int(input())
for _ in range(t):
    n = int(input())

    li = list(map(int, input().split()))
    li.sort() # 이분 탐색을 위해 정렬

    m = int(input()) # 체크할 숫자 m개
    checks = list(map(int, input().split()))
    for num in checks:
        ans = search(0, n - 1)
        print(ans)