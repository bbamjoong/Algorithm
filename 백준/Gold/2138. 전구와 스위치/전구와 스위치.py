import sys
input = sys.stdin.readline


n = int(input())
# arr1 : 첫번째 스위치를 누르지 않았을 때
arr1 = list(map(int, input().strip()))
# arr2 : 첫번째 스위치를 눌렀을 때
arr2 = arr1[:]
arr2[0] = 1-arr2[0]
arr2[1] = 1-arr2[1]
ans = list(map(int, input().strip()))


cnt = 0
for i in range(1,n):
    # arr1의 i-1번째 스위치가 ans i-1번째의 스위치와 다르다면
    # cnt+=1, i-1번쨰, i번째 값을 바꿔준다.
    if arr1[i-1] != ans[i-1]:
        cnt += 1
        arr1[i-1] = 1 - arr1[i-1]
        arr1[i] = 1 - arr1[i]
        # 맨 끝쪽 전구가 아니라면 마찬가지로 i+1번째의 값을 바꿔준다.
        if i != n-1:
            arr1[i+1] = 1 - arr1[i+1]

if arr1 == ans:
    print(cnt)
    exit()

cnt = 1
for i in range(1,n):
    # arr2의 i-1번째 스위치가 ans i-1번째의 스위치와 다르다면
    # cnt+=1, i-1번쨰, i번째 값을 바꿔준다.
    if arr2[i-1] != ans[i-1]:
        cnt += 1
        arr2[i-1] = 1 - arr2[i-1]
        arr2[i] = 1 - arr2[i]
        # 맨 끝쪽 전구가 아니라면 마찬가지로 i+1번째의 값을 바꿔준다.
        if i != n-1:
            arr2[i+1] = 1 - arr2[i+1]

if arr2 == ans:
    print(cnt)    
    exit()

print(-1)
