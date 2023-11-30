import sys
input = sys.stdin.readline

# 막대길이 더하기
# -> 합이 X보다 크다면?
# ----> 가장 짧은 것을 절반으로 자른다.
# 남아있는 막대의 길이가 X 이상일 때
# -> 자른 것 중에 하나를 버려도 X이상이라면, 자른것 중에 절반을 버린다.

########################### example #################################
# 32 32
## -> 32 버리기

# 16 16
# 16 8 8
## -> 8 버리기

# 16 4 4
# 16 4 2 2
# 16 4 2 1 1
## -> 1 버리기

# 16 4 2 1 -> 총 4개

arr = [64]
x = int(input())

def cutStick():
    if sum(arr) == x:
        print(len(arr))
        exit()
    else:
        a = arr.pop()
        arr.extend([a//2, a//2])


def removeStick():
    sumArr = sum(arr[:-1])
    if sumArr >= x:
        arr.pop()

while True:

    cutStick()
    removeStick()

    if sum(arr) == x:
        print(len(arr))
        break