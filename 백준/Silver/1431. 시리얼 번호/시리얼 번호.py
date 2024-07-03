import sys

input = sys.stdin.readline


def getSum(word):
    sm = 0
    for i in word:
        if 48 <= ord(i) <= 57:
            sm += ord(i) - 48

    return sm


n = int(input())
li = [input().rstrip() for i in range(n)]

li.sort(key = lambda x:(len(x), getSum(x), x))

for i in li:
    print(i)
