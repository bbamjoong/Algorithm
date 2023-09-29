import sys
input = sys.stdin.readline

n = int(input())

for i in range(n):
    t = int(input())
    arr = list(map(int, input().split()))
    sm = 0
    for i in range(t):
        sm += arr[i]
        
    print(sm)