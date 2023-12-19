import sys
input = sys.stdin.readline

n, m, l = map(int, input().split())
arr = [0]*n
cnt = i = 0
    
while arr[i] < m-1:
    arr[i] += 1
    cnt += 1
    i = (i+l)%n if arr[i]%2 == 1 else (i-l)%n
    
print(cnt)