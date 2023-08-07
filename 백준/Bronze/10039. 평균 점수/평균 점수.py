import sys
input = sys.stdin.readline
arr = []
for i in range(5):
    a = int(input())
    if a < 40:
        a = 40
    arr.append(a)
print(int(sum(arr)/5))