import sys
input = sys.stdin.readline

n = int(input())
sign = list(map(str, input().split()))
nums = [i for i in range(10)]
res = []
max_num = '0'
min_num = str(sys.maxsize)

def check(idx):
    if sign[idx] == '>':
        if res[idx] < res[idx+1]: return False       
    if sign[idx] == '<':
        if res[idx] > res[idx+1]: return False
    return True
        
def dfs():
    global max_num, min_num

    if len(res) >= 2:
        j = len(res)-2
        if check(j) == False:
            return
    
    if len(res) == n+1:
        a = ''.join(map(str, res))
        max_num = max(max_num, a)
        min_num = min(min_num, a)
        return

    for i in range(10):
        if nums[i] not in res:
            res.append(nums[i])
            dfs()
            res.pop()
dfs()
print(max_num)
print(min_num)
