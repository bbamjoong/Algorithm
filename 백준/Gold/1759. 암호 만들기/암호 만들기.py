import sys
input = sys.stdin.readline

vowel = ['a','e','i','o','u']
n, m = map(int, input().split())
arr = list(map(str, input().split()))
arr.sort()
res=[]

# 모음, 자음 개수를 반환하는 함수
def count_vc(li):
    count = 0
    for i in li:
        if i in vowel:
            count += 1
    return count, len(li) - count

def dfs():
    if len(res) == n:
        a, b = count_vc(res)
        # 모음1개이상, 자음2개이상일 시 출력
        if a>=1 and b>=2:
            print(''.join(map(str,res)))
        return

    for i in arr:
        if len(res)==0 or res[-1] < i:
            res.append(i)
            dfs()
            res.pop()

dfs()