import sys
input = sys.stdin.readline

nums = input().split()
nums = list(map(int, nums))
 
a = [int(input()) for i in range(0, nums[0])]
b = set()
 
for i in range(0, nums[0]):
    for j in range(a[i], nums[1]+1, a[i]):
        b.add(j)
print(len(b))
