import sys
input = sys.stdin.readline

s = 0
for _ in range(int(input())):
    l = input().split()

    if l[0] == "add":
        s |= (1 << int(l[1]))

    elif l[0] == "remove":
        s &= ~(1 << int(l[1]))

    elif l[0] == "check":
        print(1 if s & (1 << int(l[1])) != 0 else 0)

    elif l[0] == "toggle":
        s ^= (1 << int(l[1]))

    elif l[0] == "all":
        s = (1 << 21)-1

    elif l[0] == "empty":
        s = 0