import sys
input = sys.stdin.readline

n = int(input())

for _ in range(n):
    k, word = map(str, input().split())
    print(word[:int(k) - 1] + word[int(k):])