import sys
input = sys.stdin.readline
max_num = 1000001
nums = [False, False] + [True] * max_num
for i in range(2, int(max_num**.5) + 1):
    nums[2*i::i] = [False] * len(nums[2*i::i])

n = int(input())
for i in range(n):
    cnt = 0
    a = int(input())
    for j in range(3, a//2+1 ,2):
        if nums[j] and nums[a-j]:
            cnt += 1
    print(max(cnt,1))                
