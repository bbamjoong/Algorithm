import sys
input = sys.stdin.readline
    
n = int(input())

if n == 0:
    print(0)
    exit(0)

arr = [int(input()) for _ in range(n)]
arr.sort()

# 파이썬의 round는 부동소수점을 이용하기 때문에,
# 0.5 초과가 되어야 반올림을 시작한다.
# 따라서 함수 정의 or 0.000001등의 작은 값을 더해야한다.
def round2(a):
    return int(a) + (1 if a - int(a) >= 0.5 else 0
                     )
n1 = round2((n) * 0.15)

arr1 = arr[n1:n-n1]

print(round2((sum(arr1))/len(arr1)))
