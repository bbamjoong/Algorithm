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
left = {}

# 딕셔너리형태로 저장.
# 비트마스킹을 이용해 합을 구하자.
for i in range(1<<l):
    sm = 0
    for k in range(l):
        if (i&(1<<k)):
            sm += arr[k]

    # 합이 딕셔너리에 처음 들어갈 때, 아닐 때를 구분    
    if sm in left.keys():
        left[sm] += 1
    else:
        left[sm] = 1

# 오른쪽 부분집합
right = {}

for i in range(1<<r):
    sm = 0
    for k in range(r):
        if (i & (1<<k)):
            sm += arr[l+k]

    if sm in right.keys():
        right[sm] += 1
    else:
        right[sm] = 1

# (left딕셔너리의 key) + (right 딕셔터리의 key) = s 일 경우 두 key의 value를 곱해준다.
# But, (left 딕셔너리의 key) & (right 딕셔너리의 key) 둘 다 0일 경우는 ans에서 1을 빼준다.
# 아무 원소도 선택하지 않는다는 선택지가 포함되어있기 때문이다.
ans = 0
for i in left.keys():
    if s - i in right.keys():
        ans += left[i] * right[s-i]
        if i == 0 and (s - i) == 0:
            ans -= 1

print(ans)
