import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))

# 구간내 최댓값-최솟값들을 비교했을 때 최대 값으로 이분탐색 진행
# 모든 값이 같다면 결과는 0일 수 있다
start = 0
# 모든 구간을 통째로 볼때 결과는 "배열의 최대값 - 배열의 최솟값"이다.
end = max(arr) - min(arr)

def divide(x):
    max_x=min_x=arr[0]
    cnt=1
    for i in range(1,n):
        max_x=max(max_x,arr[i])
        min_x=min(min_x,arr[i])
        # 구간의 점수가 x를 초과한다면 조건에 만족하지 않는다.
        # 따라서 구간을 나누게 될 것이다.
        if max_x - min_x > x:
            cnt+=1
            max_x=arr[i]
            min_x=arr[i]
    return cnt


while start<=end:
    mid=(start+end)//2
    # 1. 구간의 수가 같을 때 -> 더 작은 값이 존재할 수 있기 때문에 탐색 시도
    # 2. 구간의 수가 문제의 조건보다 작을 때 -> 구간을 임의로 조정해 조건을 만족시킬 수 있음.
    # 2-1. 그렇다면 구간의 수가 같을 때와 같은 고민을 할 수 있을 것이다.
    if divide(mid)<=m:
        end = mid-1
    else:
        start = mid+1

print(start)
