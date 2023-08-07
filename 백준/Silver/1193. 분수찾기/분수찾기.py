import sys
input = sys.stdin.readline
n = int(input())

a = 1

while True:
  x = a*(a+1)/2
  y = a*(a+1)/2 -(a-1)
  
  if n > x or n < y:
    a+=1
  else:
    break
    
k = a*(a-1)/2

if a % 2 == 0:
  ja = n-k
  mo = a+1 - ja
else:
  mo = n-k
  ja = a+1 - mo

print(int(ja), end='')
print('/', end='')
print(int(mo))
