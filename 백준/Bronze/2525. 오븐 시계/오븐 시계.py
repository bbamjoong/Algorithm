a, b = map(int, input().split())
c = int(input())

total = a * 60 + b + c
a1 = total//60
b1 = total%60

print(a1 % 24, b1)