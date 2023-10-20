import sys
input = sys.stdin.readline

n, k = map(int, input().split())
arr = list(map(int, input().split()))
ans = []

def merge_sort(arr):
    if len(arr) == 1:
        return arr
    
    # 문제에서 항상 전반부의 원소개수를 더 많이 두고 있다.
    mid = (len(arr)+1)//2

    left_arr = merge_sort(arr[:mid])
    right_arr = merge_sort(arr[mid:])

    p = 0
    q = 0
    tmp = []

    while p < len(left_arr) and q < len(right_arr):
        #전반부 포인터의 원소가 더 작다면
        if left_arr[p] < right_arr[q]:
            tmp.append(left_arr[p])
            ans.append(left_arr[p])
            p+=1

        else:
            tmp.append(right_arr[q])
            ans.append(right_arr[q])
            q+=1

    # 전반부 원소가 남았을 경우
    while p < len(left_arr):
        tmp.append(left_arr[p])
        ans.append(left_arr[p])
        p+=1
    
    # 후반부 원소가 남았을 경우
    while q < len(right_arr):
        tmp.append(right_arr[q])
        ans.append(right_arr[q])
        q+=1

    return tmp

merge_sort(arr)

if len(ans) < k:
    print(-1)
else:
    print(ans[k-1])