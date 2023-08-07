a, b = input().split()
a1 = a[::-1]
b1 = b[::-1]
if int(a1)>int(b1):
  print(int(a1))
else:
  print(int(b1))