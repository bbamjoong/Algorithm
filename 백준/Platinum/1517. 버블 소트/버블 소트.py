import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
tmp = [0] * n

cnt = 0

def sort(s,e):
    global cnt

    if e-s<1:return
    m = int(s + (e-s)/2)
    sort(s,m)
    sort(m+1,e)

    for i in range(s,e+1):
        tmp[i] = arr[i]

    k = s
    index1 = s
    index2 = m+1

    while index1 <=m and index2 <=e:

        if tmp[index1] > tmp[index2]:
            arr[k] = tmp[index2]
            cnt += index2 - k
            k+=1
            index2+=1
        else:
            arr[k] = tmp[index1]
            k+=1
            index1+=1

    while index1 <= m:
        arr[k] = tmp[index1]
        k+=1
        index1+=1

    while index2 <= e:
        arr[k] = tmp[index2]
        k+=1
        index2+=1


sort(0,n-1)
print(cnt)