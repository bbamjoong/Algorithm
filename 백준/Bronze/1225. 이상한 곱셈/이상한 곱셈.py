import sys
input = sys.stdin.readline

a, b = input().split()
 
a = list(map(int, a))
b = list(map(int, b))
print(sum(a) * sum(b))