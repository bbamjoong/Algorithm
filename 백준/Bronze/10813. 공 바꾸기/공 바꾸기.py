n, m = map(int, input().split())
n_list = list(range(1, n+1))
for b in range(m):
  i, j = map(int, input().split())
  n_list[i-1], n_list[j-1] = n_list[j-1], n_list[i-1]
for c in range(len(n_list)):
  print(n_list[c], end = ' ')