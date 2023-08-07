a = input()
li = [i for i in a]
for i in sorted(li,reverse=True):
    print(int(i), end='')