import sys
input = sys.stdin.readline
a, b = map(int, input().split())
m = int(input())
li = list(map(int, input().split()))
li.reverse()
num = 0
for i in range(m):
    num += li[i] * (a**i)
result = []
while num//b:
    result.append(num%b)
    num = num//b
result.append(num)

result.reverse()
print(' '.join(map(str,result)))