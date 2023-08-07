import sys
input = sys.stdin.readline
n = int(input())

for i in range(n):
    str = input().strip()
    str1 = list(str.split())
    str_r = []
    
    for j in str1:
        str_r.append(j[::-1])

    res = " ".join(str_r)
    print(res)