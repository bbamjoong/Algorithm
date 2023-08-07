import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
tmp = [0] * n

cnt = 0

def sort(s,e):
    global cnt

    # end와 start가 1보다 작으면, 즉 0이되면 return
    if e-s<1:
        return
    
    # 세그먼트 트리 이용
    m = int(s + (e-s)/2)
    sort(s,m)
    sort(m+1,e)

    # 범위에 해당하는 세그먼트 트리의 root를 tmp에 저장
    for i in range(s,e+1):
        tmp[i] = arr[i]

    # k : 오름차순대로 배열을 저장할 때의 index
    k = s
    # 2314를 정렬할 때 세그먼트 트리를 이용하기 때문에
    # index1 : 앞쪽 root들을 탐색할 index, index2 : 뒤쪽 root들을 탐색할 index
    index1 = s
    index2 = m+1

    # 앞쪽 or 뒤쪽 root들을 다 탐색할 때 까지
    while index1 <=m and index2 <=e:

        if tmp[index1] > tmp[index2]:
            # arr을 오름차순으로 바꿀 것
            arr[k] = tmp[index2]
            # swap을 하기위해서는 index - k번 swap해야함
            cnt += index2 - k
            # 원소를 배열 하고, 그 다음 index로 넘어가야하기 때문에 k+=1
            k+=1
            # 뒤쪽 root 하나를 정렬했기 때문에 다음 root를 탐색해야하므로 index2+=1
            index2+=1
        else:
            # arr을 오름차순으로 바꿀 것
            arr[k] = tmp[index1]
            # 원소를 배열 하고, 그 다음 index로 넘어가야하기 때문에 k+=1
            k+=1
            # 앞쪽 root 하나를 정렬했기 때문에 다음 root를 탐색해야하므로 index2+=1
            index1+=1
    # 앞쪽 root를 다 탐색하지 못했을 때
    while index1 <= m:
        # 남은 root를 k번째 index에 넣어준다.
        arr[k] = tmp[index1]
        k+=1
        index1+=1

    # 뒤쪽 root를 다 탐색하지 못했을 때
    while index2 <= e:
        # 남은root를 k번째 index에 넣어준다.
        arr[k] = tmp[index2]
        k+=1
        index2+=1


sort(0,n-1)
print(cnt)