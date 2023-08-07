m = int(input())
n = int(input())
list1 = []

for i in range(m, n+1):
  for j in range(2, i+1):
    if i == j:
      list1.append(i)
    if i % j == 0:
      break

if len(list1) >=1:
  print(sum(list1))
  print(min(list1))
else:
  print(-1)