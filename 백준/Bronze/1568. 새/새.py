import sys
input = sys.stdin.readline

n = int(input())
cnt,i = 0,1

while n>0:
    if i > n:
        i = 1
        continue
        
    cnt += 1
    n -= i
    i+=1
    
print(cnt)