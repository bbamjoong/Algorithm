while True:
  n = int(input())
  if n == -1:
    break
  list1 = []
  for i in range(1, n):
    if n%i == 0:
      list1.append(i)
  if sum(list1) == n:
    print(n, " = ", " + ".join(map(str, list1)), sep="")
  else:
    print(n, 'is NOT perfect.')