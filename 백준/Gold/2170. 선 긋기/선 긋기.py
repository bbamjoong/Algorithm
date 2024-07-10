import sys
input = sys.stdin.readline

n = int(input())
lines = []
ans = 0

for _ in range(n):
    # 겹치는 경우
    lines.append(list(map(int, input().split())))

# 정렬을 하지 않아주면 line들의 start, end 그룹이 바뀔 경우
# 별도로 관리를 해 주어야 하기 때문에 귀찮아짐 +
lines.sort()

# 시작점, 끝점 정의
start, end = lines[0]

for i in range(1, n):
    x, y = lines[i]

    # 기존 그룹에 속하는 점 이라면
    if x <= end:
        end = max(y, end)

    # 새로운 그룹에 속할 점 이라면
    else:
        # 기존에 있던 마지막 그룹의 값을 더해준다.
        ans += (end - start)

        # 새롭게 start, end를 정의한다.
        start, end = x, y

# 최종적으로 마지막 그룹의 값을 더해준다.
ans += (end - start)

print(ans)