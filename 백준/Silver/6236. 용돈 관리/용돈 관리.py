import sys
input = sys.stdin.readline

n, m = map(int, input().split())
li = list(int(input()) for _ in range(n))

start = min(li)
end = sum(li)

# 현재 가진돈을 기준으로 매개변수 탐색
while start <= end:
    mid = (start + end) // 2

    charge = 0
    cnt = 0

    for i in range(n):
        if charge < li[i]:
            charge = mid
            cnt += 1
        charge -= li[i] # 쓴 돈 빼기

    # m 번보다 더 많이 인출했으면 금액 올려야됨.
    # 혹은 max값보다 mid가 작으면 금액 올려야됨.
    if cnt > m or mid < max(li):
        start = mid + 1
    
    # 최소 금액이 목적이니 UNDER BOUND
    else:
        end = mid - 1

print(start)