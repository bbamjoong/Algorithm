n, m = map(int, input().split())
deck = list(map(int, input().split()))

res = 0 

for i in range(n-2):
  for j in range(i+1,n-1):
    for k in range(j+1,n):
      if deck[i] + deck[j] + deck[k] >m:
        continue
      else:
        res = max(res, (deck[i] + deck[j] + deck[k]))
print(res)