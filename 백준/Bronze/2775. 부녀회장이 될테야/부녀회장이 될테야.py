import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    floor = int(input()) #층
    n = int(input()) #호

    floor_0 = [x for x in range(1,n+1)]

    for k in range(floor):
        for i in range(1, n):
            floor_0[i] += floor_0[i-1]
    
    print(floor_0[-1])