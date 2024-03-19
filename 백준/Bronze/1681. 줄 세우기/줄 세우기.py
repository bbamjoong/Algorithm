import sys
input = sys.stdin.readline

n, l = list(map(int, input().split()))

num = 1 
cnt = 0	
while True:
    if str(l) not in str(num):
        cnt += 1
        if cnt == n:
            print(num)
            break
        num += 1

    else:
        num += 1