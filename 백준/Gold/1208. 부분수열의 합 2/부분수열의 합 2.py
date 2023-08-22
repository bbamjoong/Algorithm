import sys
input = sys.stdin.readline

# 정수의 개수는 최대 40개.
# 백트래킹으로 문제 해결시 시간복잡도는 O(2^40) = 1,099,511,627,776 -> 문제의 시간제한은 1초이므로 시간초과!

# 수열의 왼쪽 / 오른쪽으로 나누어 문제 해결시 시간복잡도는 O(2 * 2^20) = 2,097,152 이다.

n,s = map(int, sys.stdin.readline().split())
arr = list(map(int, sys.stdin.readline().split()))

# 이분 분할
l = n//2
r = n - l

# 왼쪽 부분집합
left = [0] * (1<<l)

# 비트마스킹을 이용해 합을 구하자.
for i in range(1<<l):
    for k in range(l):
        if (i&(1<<k)):
            left[i] += arr[k]


# 오른쪽 부분집합
right = [0] * (1<<r)

for i in range(1<<r):
    for k in range(r):
        if (i & (1<<k)):
            right[i] += arr[l+k]

left.sort()
right.sort(reverse=True)

start_l, start_r = 0, 0
ans = 0

# 투포인터 이용
while start_l < len(left) and start_r < len(right):
    tmp = left[start_l]+right[start_r]
    if tmp == s:
        count_l = 1
        count_r = 1

        while start_l < len(left)-1 and left[start_l] == left[start_l+1]:
            count_l += 1
            start_l += 1
        
        while start_r < len(right)-1 and right[start_r] == right[start_r+1]:
            count_r += 1
            start_r += 1
        
        ans += count_l * count_r
        
        start_l += 1
        start_r += 1
    
    elif tmp < s:
        start_l += 1
    
    else:
        start_r += 1

if s == 0 and ans > 0:
    print(ans-1)
else:
    print(ans)