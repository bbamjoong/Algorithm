n = int(input())

for i in range(1,n+1):
    if i + sum(map(int, str(i))) == n:
        print(i)
        break
    if i == n:
        print(0)