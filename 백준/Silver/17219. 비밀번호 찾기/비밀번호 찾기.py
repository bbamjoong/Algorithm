import sys
input = sys.stdin.readline

n, m = map(int, input().split())

dict = {}
for _ in range(n):
    address, password = map(str, input().rstrip().split())
    dict[address] = password

for _ in range(m):
    t = input().rstrip()
    print(dict[t])