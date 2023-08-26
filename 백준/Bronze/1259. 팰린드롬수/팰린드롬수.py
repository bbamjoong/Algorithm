import sys
input = sys.stdin.readline

while True:
    n = int(input())

    if n == 0:
        break

    length = len(str(n)) - 1
    ans = "yes"

    if length % 2 != 1:
        for i in range(length // 2):
            if str(n)[i] != str(n)[length-i]:
                ans = "no"

    else:
        for i in range(length // 2 + 1):
            if str(n)[i] != str(n)[length-i]:
                ans = 'no'
    print(ans)