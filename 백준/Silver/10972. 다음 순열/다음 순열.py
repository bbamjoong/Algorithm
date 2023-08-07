import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

# ex)1 3 4 2
for i in range(n-1,0,-1):
    # i가 2일 때 arr[i-1] < arr[i] (3 < 4)
    if arr[i-1] < arr[i]:
        for j in range(n-1,0,-1):
            # 배열을 거꾸로 탐색할 때 arr[i-1] < arr[j] (3 < 4)
            if arr[i-1] < arr[j]:
                # 두 값을 swap / 1 3 4 2 -> 1 4 3 2
                arr[i-1], arr[j] = arr[j], arr[i-1]
                # i-1까지의 배열 + "정렬된"i~n-1까지의 배열
                print(*(arr[:i] + sorted(arr[i:])))
                exit()

print(-1)
