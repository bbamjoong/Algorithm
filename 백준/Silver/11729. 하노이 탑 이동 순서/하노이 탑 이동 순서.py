import sys
input = sys.stdin.readline

n = int(input())

def hanoi(start, end, n):
    #옮겨야할 판이 하나라면 출력
    if n == 1:
        print(start, end)
        return
    #먼저 n-1개의 원판을 목표지점이 아닌 다른 곳에 옮기고
    hanoi(start, 6-start-end, n-1)
    #가장 큰 원판을 시작점에서 목표지점에 옮기고
    print(start, end)
    #다른 곳에 있는 n-1개의 원판을 목표지점에 옮긴다
    hanoi(6-start-end, end, n-1)

print(2**n-1)
hanoi(1,3,n)