import sys
input = sys.stdin.readline

# 전수조사를 한다. n이 50일 때 50 * 49번 연산하므로
# 시간복잡도 : O(n**2)
n = int(input())
arr = []

for i in range(n):
    weight, height = map(int, input().split())
    arr.append([weight, height])

for i in arr:
    num = 1
    for j in arr:
        if i[0] < j[0] and i[1] < j[1]:
            num += 1
    print(num, end=" ")