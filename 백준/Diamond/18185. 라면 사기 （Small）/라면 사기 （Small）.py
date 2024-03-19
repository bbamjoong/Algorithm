import sys
input = sys.stdin.readline


def ramen_special(n):  # 2번을 최소화 하는 전략
    global ans
    value = min(li[n], li[n + 1] - li[n + 2])
    li[n] -= value
    li[n + 1] -= value
    ans += 5 * value


def ramen(n, num): # 라면 사기
    global ans
    value = int(1e9)
    for i in range(num):
        value = min(value, li[n + i])

    for i in range(num):
        li[n + i] -= value

    ans += (2 * num + 1) * value


n = int(input())
# 마지막 부분에 if문 더 달아서 성능이 안나오느니 배열 뒤에 [0, 0]을 더 붙여주겠다.
li = list(map(int, input().split())) + [0, 0]

# 1개, 2개 사는 것을 순서대로 최소화 해야한다.
# 2개 사는 것을 최소화하는 것이 전략.
# i, i+1, i+2를 비교했을 때  i+2가 존재해야 3개 사는 것 최대화 가능 -> 2개 사는 것 최소화 가능
# 따라서 i+1이 i+2보다 많을 때 min(li[i], li[i+1] - li[i+2])만큼 i번째에서 2개 사기를 진행해준다.
# 이후 가능한 수 만큼 3개 사기를 진행한다.

ans = 0
for i in range(len(li) - 2):
    if li[i + 1] > li[i + 2]:
        ramen_special(i) # 2번 특이케이스
        ramen(i, 3)  # 3번 최대화
        ramen(i, 1)
    else:
        for num in range(3, 0, -1):
            ramen(i, num)

print(ans)
