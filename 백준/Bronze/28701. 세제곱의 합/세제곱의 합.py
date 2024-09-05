import sys
input = sys.stdin.readline

n = int(input())

sum = 0
cube_sum = 0

for i in range(1, n + 1):
    sum += i
    cube_sum += i ** 3

print(sum)
print(sum ** 2)
print(cube_sum)