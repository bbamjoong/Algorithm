import sys
input = sys.stdin.readline

n = int(input())
# 1을 제외한 양수, 음수의 배열
positive, negative = [], []

# 0이 있는지?
zero = False

# 숫자들의 최대 합
sm = 0

for i in range(n):
    a = int(input())

    # 1은 아무것도 취하지 않고 그대로 더하는 것이 정답
    if a == 1:
        sm += 1
    elif a > 1:
        positive.append(a)
    elif a == 0:
        zero = True
    elif a < 0:
        negative.append(a)

#양수, 음수의 절댓값에 대하여 내림차순
positive.sort(reverse = True)
negative.sort()
len_pos = len(positive)
len_neg = len(negative)

# 양수가 짝수개라면, 가장 큰 수 부터 2개씩 묶은 것의 곱을 더한다
if len_pos % 2 == 0:
    for i in range(0, len_pos, 2):
        sm += positive[i] * positive[i+1]

#양수가 홀수개라면, 남은 수 하나를 추가로 더해준다.
else:
    for i in range(0, len_pos-2, 2):
        sm += positive[i] * positive[i+1]
    sm += positive[-1]

# 음수가 짝수개라면, 가장 큰 수부터 2개씩 묶은 것의 곱을 더한다.
if len_neg % 2 == 0:
    for i in range(0, len_neg, 2):
        sm += negative[i] * negative[i+1]

#음수가 홀수개일 때 가장 큰 수부터 2개씩 묶은 것의 곱을 더해주고
else:
    for i in range(0, len_neg-1, 2):
        sm += negative[i] * negative[i+1]

    # 마지막 하나의 음수가 남았을 때 / 0이 있다면
    # 곱해주면 0이 되므로 pass
    if zero:
        pass
    # 0이 없다면 남은 음수를 더해준다.
    else:
        sm += negative[-1]

print(sm)