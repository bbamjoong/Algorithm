n = int(input())

for i in range(n,3,-1):
    if all([(s == '4' or s == '7') for s in str(i)]):
        print(i)
        break