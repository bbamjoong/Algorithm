n, m = map(int, input().split())
n_list = []
for a in range(n):
  n_list.append(0)

for b in range(m):
  i, j, k = map(int, input().split())
  for c in range(i, j+1):
    n_list[c-1] = k

for d in range(len(n_list)):
  print(n_list[d], end = ' ')