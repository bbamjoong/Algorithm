import sys
input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n = int(input())
    dict1 = {}
    for i in range(n):
        name, type = input().rstrip().split()

        if type in dict1.keys():
            dict1[type] += 1
        else:
            dict1[type] = 1

    ans = 1
    for i in dict1.items():
        ans *= (i[1] + 1)

    print(ans-1)