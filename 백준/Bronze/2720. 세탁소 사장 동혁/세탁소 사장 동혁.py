k = int(input())

for i in range(k):
  n = int(input())
  a, n = divmod(n, 25)
  b, n = divmod(n, 10)
  c, n = divmod(n, 5)
  d = n
  print(a, b, c, d)