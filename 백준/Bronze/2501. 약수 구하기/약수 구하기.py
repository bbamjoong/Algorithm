list1 = []
a, k = map(int, input().split())
for i in range(1,a+1):
  if a % i == 0:
    list1.append(i)
if k > len(list1):
  print(0)
else:
  print(list1[k-1])