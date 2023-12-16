import sys
input = sys.stdin.readline

def count(h):
    max = -1
    count = 0
    for i in h:
        if max < i:
            max = i
            count += 1
    return count


N = int(input())
h = []

for i in range(N):
    h.append(int(input()))

print(count(h))
print(count(reversed(h)))