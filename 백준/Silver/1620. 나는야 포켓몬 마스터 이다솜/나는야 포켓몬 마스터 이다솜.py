import sys
input = sys.stdin.readline

n, m = map(int, input().split())
dict = {}

for i in range(1, n + 1):
    poketmon = input().rstrip()
    dict[i] = poketmon
    dict[poketmon] = i

for _ in range(m):
    prob = input().rstrip()

    if prob.isdigit():
        print(dict[int(prob)])

    else:
        print(dict[prob])