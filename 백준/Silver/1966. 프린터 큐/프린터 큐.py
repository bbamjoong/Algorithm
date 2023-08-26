import sys
input = sys.stdin.readline


k = int(input())
for _ in range(k):
    n, m = map(int, input().split())
    q = []
    arr = list(map(int, input().split()))
    for i in range(len(arr)):
        q.append([i,arr[i]])
    cnt = 0

    while True:
        length = len(q)
        index, weight_num = q.pop(0)
        for idx, important in q:
            if weight_num < important:
                q.append([index, weight_num])
                break
        
        if length == len(q):
            continue

        cnt += 1

        if index == m:
            print(cnt)
            break