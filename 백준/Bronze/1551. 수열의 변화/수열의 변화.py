n, k = map(int, input().split())
arr = list(map(int, input().split(',')))
for _ in range(k):
    t = [arr[i+1]-arr[i] for i in range(len(arr)-1)]
    arr = t
print(*arr, sep=',')