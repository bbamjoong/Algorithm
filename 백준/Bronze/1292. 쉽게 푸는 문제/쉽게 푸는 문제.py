import sys
input = sys.stdin.readline

# 숫자 45까지 나열하면 2000이 넘는다
a,b = map(int,input().split())
 
arr = []
for i in range(46):
    for j in range(i):
        arr.append(i)

print(sum(arr[a-1:b]))
