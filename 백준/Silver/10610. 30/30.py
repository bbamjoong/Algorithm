import sys
input = sys.stdin.readline

str = input().strip()

if not '0' in str:
    print(-1)
else:
    arr = [int(i) for i in str]
    if sum(arr) % 3 == 0:
        for i in sorted(arr, reverse = True):
            print(i,end='')
    else:
        print(-1)