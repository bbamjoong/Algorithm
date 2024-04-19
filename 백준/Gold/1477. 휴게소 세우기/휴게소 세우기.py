import sys
input = sys.stdin.readline

n, m, l = map(int, input().split())

# O(n logn)
li = [0] + list(map(int, input().split())) + [l]
li.sort()

size = len(li)
ans = 0
breakloop = False
for mid in range(1, l):
    if breakloop:
        break
    cnt = 0
    # O(n)
    for i in range(1, size):
        if li[i] - li[i - 1] > mid:
            # 200 ~ 500이고 간격이 150이라면
            # 350만 설치하면 됨.
            # 딱맞게 나누어 떨어진다고 하면 cnt를 1 감소시키는 것
            cnt += (li[i] - li[i-1]) // mid
            if (li[i] - li[i-1]) % mid == 0:
                cnt -= 1

    if cnt <= m:
        ans = mid
        breakloop = True
        break

print(mid)