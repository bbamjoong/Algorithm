import sys

input = sys.stdin.readline

n, k = map(int, input().split())

def count_bit(x):
    count = 0
    while x > 0:
        count += x & 1
        x >>= 1
    return count

ans = 0
while True:
    cnt = count_bit(n)

    if cnt > k:
        # 가장 낮은 1의 비트를 찾는다.
        lowest_bit = n & -n
        # 이 비트를 더하여 1의 개수를 줄인다.
        n += lowest_bit
        ans += lowest_bit
    else:
        break

print(ans)
