import sys
input = sys.stdin.readline

n = int(input())
arr1 = list(map(int, input().split()))
arr1.sort()

m = int(input())
arr2 = list(map(int, input().split()))

for i in arr2:
    start = 0
    end = n - 1

    ans = 0
    while start <= end:
        mid = (start + end) // 2

        if i > arr1[mid]:
            start = mid + 1

        elif i < arr1[mid]:
            end = mid - 1

        elif i == arr1[mid]:
            ans = 1
            break
    print(ans)