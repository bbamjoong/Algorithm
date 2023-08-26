import sys
input = sys.stdin.readline

n = int(input())
arr = []
count = [0] * 8001

for i in range(n):
    num = int(input())
    arr.append(num)
    count[4000 + num] += 1

avg = round(sum(arr)/n)
median = sorted(arr)[n//2]

res = []
mx_cnt = max(count)
for i in range(len(count)):
    if count[i] == mx_cnt:
        res.append(i-4000)
    
    if len(res) == 2:
        break

mode = res[-1]
range_ = max(arr) - min(arr)


print(avg)
print(median)
print(mode)
print(range_)