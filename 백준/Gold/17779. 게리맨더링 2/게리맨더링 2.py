import sys
input = sys.stdin.readline

def solve(x,y,d1,d2):
    global total
    people = [0] * 5

    # 1구역 : 0 ~ x + d1 -1 행까지 탐색
    standard = y
    for r in range(x + d1):
        # 탐색하는 구역이 x와 같거나 아래쪽이면 standard를 줄여나간다.
        if r >= x:
            standard -= 1
        people[0] += sum(graph[r][:standard+1])

    # 2구역 : 0 ~ x + d2 행까지 탐색
    standard = y + 1
    for r in range(x + d2 + 1):
        # 탐색하는 구역이 x보다 아래쪽이면 standard를 늘려나간다.
        if r > x:
            standard += 1
        people[1] += sum(graph[r][standard:])

    # 3구역 : x + d1 ~ n-1 행까지 탐색
    standard = y - d1 - 1
    for r in range(x+d1, n):
        if r <= x + d1 + d2:
            standard += 1
        people[2] += sum(graph[r][:standard])

    # 4구역 : x + d2 + 1 ~ n-1행까지 탐색
    standard = y + d2
    for r in range(x + d2 + 1, n):
        people[3] += sum(graph[r][standard:])
        if r <= x + d1 + d2:
            standard -= 1

    people[4] = total - sum(people)
    return max(people) - min(people)




n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
total = 0
for i in graph:
    total += sum(i)
ans = int(1e10)

# x는 문제의 조건에 의해 0 ~ n-3 의 값을 가질 수 있다.
# y는 문제의 조건에 의해 1 ~ n-2 의 값을 가질 수 있다.
for x in range(n-2):
    for y in range(1,n-1):
        # d1, d2는 문제의 조건을 이용해 조건이 맞지 않다면 continue처리
        for d1 in range(1,n-1):
            for d2 in range(1,n-1):
                if x + d1 + d2 > n:
                    continue
                if y - d1 < 1:
                    continue
                if y+2 > n:
                    continue
                ans = min(ans, solve(x,y,d1,d2))

print(ans)