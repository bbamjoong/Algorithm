a = int(input())
for i in range(a):
  b, c = input().split()
  for j in c:
    print(int(b) * j, end='')
  print()