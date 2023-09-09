import sys
input = sys.stdin.readline

n, m = map(int, input().split())

know = set(input().split()[1:])
parties = []

for _ in range(m):
    parties.append(set(input().split()[1:]))

# 교집합이 있을 경우 -> 알고있는 사람 최신화
# 이 과정을 m번 반복한다. 알고있는 사람이 최신화 된 이후에 거짓말을 해도 되는지 다시 판단
for _ in range(m):
    for party in parties:
        if party & know:
            know = know | party

ans = 0

for party in parties:
    if party & know:
        continue
    ans += 1

print(ans)